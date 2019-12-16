package com.deedee.fingertips.deestarwars.services;

import com.deedee.fingertips.deestarwars.config.PropsConfig;
import com.deedee.fingertips.deestarwars.interfaces.IHttpService;
import com.deedee.fingertips.deestarwars.models.Movie;
import com.deedee.fingertips.deestarwars.models.ParamEnums;
import com.deedee.fingertips.deestarwars.models.People;
import com.deedee.fingertips.deestarwars.models.PeopleQueryParams;
import com.deedee.fingertips.deestarwars.interfaces.ICommentService;
import com.deedee.fingertips.deestarwars.interfaces.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService implements Serializable, IMovieService {

    @Autowired
    IHttpService httpService;

    @Autowired
    ICommentService commentService;

    @Autowired
    PropsConfig propsConfig;

    public MovieService()  {   }

    @Override
    @Cacheable(value = "movie", key = "#movieId")
    public Movie getMovieWithPeople(int movieId, PeopleQueryParams peopleQueryParams)
    {
        String moviePartUrl = String.format("%s%s/%s",propsConfig.swapiBaseUrl ,"films", movieId);
        Movie movie = (Movie)httpService.get(moviePartUrl, Movie.class).getBody();

        if(movie != null)
        {
            //add comment count;
            long commentCount = commentService.countById(movieId);
            movie.setCommentCount(commentCount);

            //get people details
            List<String> peopleUrlList = movie.getCharacters();

            if(peopleUrlList.size() > 0)
            {
                List<People> people = peopleUrlList.stream()
                                                    .map(this::getPeopleDetails)
                                                    .filter(person -> person.getGender().toLowerCase().equals(peopleQueryParams.genderFilter.toString().toLowerCase()))
                                                    .sorted((itemA, itemB) ->
                                                    {
                                                            if(peopleQueryParams.sortParameters.toString().toLowerCase().equals(ParamEnums.SortParams.GENDER.toString().toLowerCase()))
                                                            {
                                                               return itemA.getGender().compareTo(itemB.getGender());
                                                            }
                                                            else if(peopleQueryParams.sortParameters.toString().toLowerCase().equals(ParamEnums.SortParams.HEIGHT.toString().toLowerCase()))
                                                            {
                                                                return itemA.getHeight().compareTo(itemB.getHeight());
                                                            }
                                                            else if(peopleQueryParams.sortParameters.toString().toLowerCase().equals(ParamEnums.SortParams.NAME.toString().toLowerCase()))
                                                            {
                                                                return itemA.getName().compareTo(itemB.getName());
                                                            }
                                                        return 0;
                                                    }
                                                    )
                                                    .collect(Collectors.toList());
                movie.setPeople(people);

                Long height = people.stream().mapToLong(item ->  Long.parseLong(item.getHeight())).sum();

                movie.setTotalHeightInCm(height+"cm");
                movie.setTotalHeightInFeet(cmToFeet(height));
                movie.setPeopleCount(people.size());
            }
        }

        return movie;
    }

    @Cacheable(value = "people", key = "#peopleUrl")
    private People getPeopleDetails (String peopleUrl)
    {
        People people = (People) httpService.get(peopleUrl, People.class).getBody();
        return people;
    }

    private String cmToFeet(Long heightInCm)
    {
        double feet = heightInCm/30.48;
        double inches = (heightInCm/2.54) - ((int)feet * 12);
        return String.format("%s ft %.2f inches" ,(int)feet , inches);

    }

}
