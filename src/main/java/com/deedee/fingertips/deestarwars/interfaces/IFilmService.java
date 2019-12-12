package com.deedee.fingertips.deestarwars.interfaces;

import com.deedee.fingertips.deestarwars.models.Film;
import com.deedee.fingertips.deestarwars.models.PeopleQueryParams;
import org.springframework.stereotype.Service;

@Service
public interface IFilmService
{
    Film getFilmById(int filmId) throws Exception;
    Film getCharacterListForFilm(int filmId, PeopleQueryParams peopleQueryParams);
}
