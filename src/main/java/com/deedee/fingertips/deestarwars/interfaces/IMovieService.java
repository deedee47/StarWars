package com.deedee.fingertips.deestarwars.interfaces;

import com.deedee.fingertips.deestarwars.models.Movie;
import com.deedee.fingertips.deestarwars.models.PeopleQueryParams;
import org.springframework.stereotype.Service;

@Service
public interface IMovieService
{
   Movie getMovieWithPeople(int filmId, PeopleQueryParams peopleQueryParams);
}
