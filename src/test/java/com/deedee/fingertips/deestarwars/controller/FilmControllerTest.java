package com.deedee.fingertips.deestarwars.controller;

import com.deedee.fingertips.deestarwars.controllers.FilmController;
import com.deedee.fingertips.deestarwars.models.PeopleQueryParams;
import com.deedee.fingertips.deestarwars.services.FilmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmControllerTest
{
    private MockMvc mockMvc;

    @Autowired
    FilmController filmController;

    @Before
    public void start() {
        mockMvc = MockMvcBuilders.standaloneSetup(filmController).build();
    }

    @Test
    public void testGetFilmController() throws Exception
    {
        String url = "/api/v1/film/get/2";
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

//    @Test
//    public void testFindFilmWithCharacters() throws Exception
//    {
//        String url = "/api/v1/film/find/2";
//        Map peopleQueryParams = new Object();
//        peopleQueryParams.name("VALUE");
//        MvcResult mvcResult = mockMvc
//                .perform(MockMvcRequestBuilders.get(url)
//                        .contentType("application/json")
//                        .content()
//                        .param("sort_type", "ASC")
//                        .param("gender_filter", "FEMALE")
//                        .param("sort_parameters", "HEIGHT"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
//
//    }

}
