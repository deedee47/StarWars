package com.deedee.fingertips.deestarwars.services;

import com.deedee.fingertips.deestarwars.models.Movie;
import com.deedee.fingertips.deestarwars.models.ParamEnums;
import com.deedee.fingertips.deestarwars.models.PeopleQueryParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceTest {

    @Autowired
    MovieService movieService;

    @Test
    public void findMovieTest() throws Exception
    {
        PeopleQueryParams peopleQueryParams = new PeopleQueryParams();
        peopleQueryParams.genderFilter = ParamEnums.GenderFilter.MALE;
        peopleQueryParams.sortParameters = ParamEnums.SortParams.NAME;
        peopleQueryParams.sortType = ParamEnums.SortType.ASC;
        Movie testMovie = movieService.getMovieWithPeople(2, peopleQueryParams);
        assertNotNull(testMovie);
        assertEquals(testMovie.getTitle(), "The Empire Strikes Back");
    }
}
