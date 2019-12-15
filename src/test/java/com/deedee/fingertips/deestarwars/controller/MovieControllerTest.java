package com.deedee.fingertips.deestarwars.controller;

import com.deedee.fingertips.deestarwars.controllers.MovieController;
import com.deedee.fingertips.deestarwars.models.ParamEnums;
import com.deedee.fingertips.deestarwars.models.PeopleQueryParams;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieControllerTest
{
    private MockMvc mockMvc;

    @Autowired
    MovieController movieController;

    @Before
    public void start() {
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }


    @Test
    public void testFindMovieWithCharacters() throws Exception
    {
        String url = "/api/v1/movie/find/";

        PeopleQueryParams peopleQueryParams = new PeopleQueryParams();
        peopleQueryParams.genderFilter = ParamEnums.GenderFilter.MALE;
        peopleQueryParams.sortParameters = ParamEnums.SortParams.NAME;
        peopleQueryParams.sortType = ParamEnums.SortType.ASC;

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonQueryParams = objectMapper.writeValueAsString(peopleQueryParams);

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .param("movieId", "2")
                        .content(jsonQueryParams))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

}
