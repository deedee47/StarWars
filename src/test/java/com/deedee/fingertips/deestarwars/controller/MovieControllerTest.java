package com.deedee.fingertips.deestarwars.controller;

import com.deedee.fingertips.deestarwars.controllers.MovieController;
import com.deedee.fingertips.deestarwars.models.ParamEnums;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
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
        String url = "/movies";

        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .param("movieId", "2")
                        .param("filter", ParamEnums.Filter.MALE.toString())
                        .param("sort", ParamEnums.Sort.NAME.toString())
                        .param("order", ParamEnums.Order.ASC.toString())
                        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

}
