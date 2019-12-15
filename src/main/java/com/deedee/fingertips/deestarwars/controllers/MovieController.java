package com.deedee.fingertips.deestarwars.controllers;

import com.deedee.fingertips.deestarwars.models.Movie;
import com.deedee.fingertips.deestarwars.models.PeopleQueryParams;
import com.deedee.fingertips.deestarwars.interfaces.IMovieService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController
{
    @Autowired
    public IMovieService movieService;

    @ApiOperation(value = "Find a Movie with list of characters using Movie ID")
    @RequestMapping(method = RequestMethod.POST, value = "/find", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Movie findFilmWithCharacters(@RequestParam("movieId") int movieId, @RequestBody PeopleQueryParams peopleQueryParams) throws Exception
    {
        Movie result = movieService.getMovieWithPeople(movieId, peopleQueryParams);
        return result;
    }
}
