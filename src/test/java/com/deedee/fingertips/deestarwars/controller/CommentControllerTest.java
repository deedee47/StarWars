package com.deedee.fingertips.deestarwars.controller;

import com.deedee.fingertips.deestarwars.controllers.CommentController;
import com.deedee.fingertips.deestarwars.repositories.CommentRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaRepositories("com.deedee.fingerprints.deestarwars.repositories")
@ComponentScan("om.deedee.fingerprints.deestarwars")
@EntityScan(basePackages = {"om.deedee.fingerprints.deestarwars.models"})
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
