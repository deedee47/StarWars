package com.deedee.fingertips.deestarwars.controller;

import com.deedee.fingertips.deestarwars.controllers.CommentController;
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
public class CommentControllerTest {

    private MockMvc mockMvc;

    @Autowired
    CommentController commentController;

    @Before
    public void start() {
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }

//    @Test
//    public void testSaveController() throws Exception
//    {
//
//    }
    @Test
    public void testGetController() throws Exception
    {
        String url = "/api/v1/comment/get/2";
        MvcResult mvcResult = mockMvc
                                     .perform(MockMvcRequestBuilders.get(url))
                                     .andExpect(MockMvcResultMatchers.status().isOk())
                                     .andReturn();
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }
//    @Test
//    public void testFindController()
//    {
//
//    }

}
