package com.deedee.fingertips.deestarwars.services;

import com.deedee.fingertips.deestarwars.models.Movie;
import com.deedee.fingertips.deestarwars.models.ParamEnums;
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
        Movie testMovie = movieService.getMovieWithPeople(2, ParamEnums.Filter.MALE,  ParamEnums.Sort.NAME, ParamEnums.Order.ASC);
        assertNotNull(testMovie);
        assertEquals(testMovie.getTitle(), "The Empire Strikes Back");
    }
}
