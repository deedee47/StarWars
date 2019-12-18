package com.deedee.fingertips.deestarwars.controllers;

import com.deedee.fingertips.deestarwars.models.Movie;
import com.deedee.fingertips.deestarwars.models.ParamEnums;
import com.deedee.fingertips.deestarwars.interfaces.IMovieService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class MovieController
{
    @Autowired
    public IMovieService movieService;

    @ApiOperation(value = "Find a Movie showing list of characters")
    @RequestMapping(method = RequestMethod.GET, value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Movie getMovieWithCharacters(@RequestParam("movieId") int movieId,
                                        @RequestParam("filter") ParamEnums.Filter filter,
                                        @RequestParam("sort") ParamEnums.Sort sort,
                                        @RequestParam("order") ParamEnums.Order order   )
    {
        Movie result = movieService.getMovieWithPeople(movieId, filter, sort, order);
        return result;
    }
}
