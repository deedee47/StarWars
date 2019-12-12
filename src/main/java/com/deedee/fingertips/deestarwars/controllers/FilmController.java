package com.deedee.fingertips.deestarwars.controllers;

import com.deedee.fingertips.deestarwars.models.Film;
import com.deedee.fingertips.deestarwars.models.PeopleQueryParams;
import com.deedee.fingertips.deestarwars.interfaces.IFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/film")
public class FilmController
{
    @Autowired
    public IFilmService filmService;

    @RequestMapping(method = RequestMethod.GET, value = "/get/{filmId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Film getFilm(@PathVariable("filmId") int filmId) throws Exception
    {
        Film result = filmService.getFilmById(filmId);
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/find", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Film findFilmWithCharacters(@RequestParam("filmId") int filmId, @RequestBody PeopleQueryParams peopleQueryParams) throws Exception
    {
        Film result = filmService.getCharacterListForFilm(filmId, peopleQueryParams);
        return result;
    }
}
