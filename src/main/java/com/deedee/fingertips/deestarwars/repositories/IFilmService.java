package com.deedee.fingertips.deestarwars.repositories;

import com.deedee.fingertips.deestarwars.models.Film;
import org.springframework.stereotype.Service;

@Service
public interface IFilmService
{
    Film getFilmById(int filmId) throws Exception;
}
