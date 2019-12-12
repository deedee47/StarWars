package com.deedee.fingertips.deestarwars.services;

import com.deedee.fingertips.deestarwars.config.PropsConfig;
import com.deedee.fingertips.deestarwars.models.Film;
import com.deedee.fingertips.deestarwars.models.ParamEnums;
import com.deedee.fingertips.deestarwars.models.People;
import com.deedee.fingertips.deestarwars.models.PeopleQueryParams;
import com.deedee.fingertips.deestarwars.interfaces.ICommentService;
import com.deedee.fingertips.deestarwars.interfaces.IFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService implements IFilmService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PropsConfig propsConfig;

    @Autowired
    ICommentService commentService;

    public FilmService()  {   }

    @Override
    public Film getFilmById(int filmId) throws Exception
    {
        String filmPartUrl = String.format("%s%s",propsConfig.swapiBaseUrl ,"films/");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", propsConfig.headerValue);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        Film film = restTemplate.exchange(filmPartUrl+filmId, HttpMethod.GET,  entity, Film.class).getBody();

        //add comment count;
        if(film != null)
        {
            long commentcount = commentService.countById(filmId);
            film.setCommentCount(commentcount);
            //System.out.println("count="+commentcount);
        }

        return film;
    }

    @Override
    public Film getCharacterListForFilm(int filmId, PeopleQueryParams peopleQueryParams)
    {
        String filmPartUrl = String.format("%s%s",propsConfig.swapiBaseUrl ,"films/");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", propsConfig.headerValue);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        Film film = restTemplate.exchange(filmPartUrl+filmId, HttpMethod.GET,  entity, Film.class).getBody();

        if(film != null)
        {
            //add comment count;
            long commentcount = commentService.countById(filmId);
            film.setCommentCount(commentcount);
            //System.out.println("count="+commentcount);

            //get people details
            List<String> peopleUrlList = film.getCharacters();

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
                film.setPeople(people);

                Long height = people.stream().mapToLong(item ->  Long.parseLong(item.getHeight())).sum();

                film.setTotalHeightInCm(height+"cm");
                film.setTotalHeightInFeet(cmToFeet(height));
                film.setPeopleCount(people.size());
            }
        }

        return film;
    }

    private People getPeopleDetails (String peopleUrl)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", propsConfig.headerValue);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        People people = restTemplate.exchange(peopleUrl, HttpMethod.GET,  entity, People.class).getBody();

        return people;
    }

    private String cmToFeet(Long heightInCm)
    {
        double feet = heightInCm/30.48;
        double inches = (heightInCm/2.54) - ((int)feet * 12);
        return String.format("%s ft %.2f inches" ,(int)feet , inches);

    }

}
