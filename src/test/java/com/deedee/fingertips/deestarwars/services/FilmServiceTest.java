package com.deedee.fingertips.deestarwars.services;

import com.deedee.fingertips.deestarwars.models.Film;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmServiceTest {

    @Autowired
    FilmService filmService;

    @Test
    public void getFilmTest() throws Exception
    {
        Film testFilm = filmService.getFilmById(2);
        assertNotNull(testFilm);
        assertEquals(testFilm.getTitle(), "The Empire Strikes Back");
    }
}
