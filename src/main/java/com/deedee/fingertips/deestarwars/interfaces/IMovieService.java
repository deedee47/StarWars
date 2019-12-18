package com.deedee.fingertips.deestarwars.interfaces;

import com.deedee.fingertips.deestarwars.models.Movie;
import com.deedee.fingertips.deestarwars.models.ParamEnums;
import org.springframework.stereotype.Service;

@Service
public interface IMovieService
{
   Movie getMovieWithPeople(int filmId, ParamEnums.Filter filter, ParamEnums.Sort sort, ParamEnums.Order order);
}
