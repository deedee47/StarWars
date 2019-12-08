package com.deedee.fingertips.deestarwars.services;

import com.deedee.fingertips.deestarwars.config.ApplicationConfig;
import com.deedee.fingertips.deestarwars.config.PropsConfig;
import com.deedee.fingertips.deestarwars.models.Film;
import com.deedee.fingertips.deestarwars.repositories.ICommentService;
import com.deedee.fingertips.deestarwars.repositories.IFilmService;
import com.google.common.collect.Iterators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

@Service
public class FilmService implements IFilmService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PropsConfig propsConfig;

    @Autowired
    ICommentService commentService;

    public FilmService()  {   }

    @Override
    public Film getFilmById(int filmId) throws Exception
    {
        String filmPartUrl = String.format("%s%s",propsConfig.swapiBaseUrl ,"films/");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", propsConfig.headerValue);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        Film film = restTemplate.exchange(filmPartUrl+filmId, HttpMethod.GET,  entity, Film.class).getBody();

        //add comment count;
        if(film != null)
        {
            long commentcount = commentService.countById(filmId);
            film.setComment_count(commentcount);
            //System.out.println("count="+commentcount);
        }

        return film;
    }
}
