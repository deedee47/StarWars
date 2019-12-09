package com.deedee.fingertips.deestarwars.controller;

import com.deedee.fingertips.deestarwars.controllers.CommentController;
import com.deedee.fingertips.deestarwars.models.Comment;
import com.deedee.fingertips.deestarwars.repositories.CommentRepo;
import com.deedee.fingertips.deestarwars.services.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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

    @Test
    public void testSaveController() throws Exception
    {
        Comment firstComment = new Comment(4, "Loved it", "172.333.333.333");
        firstComment.setCreatedDateUtc(Timestamp.valueOf(LocalDateTime.now()));

        String url = "/api/v1/comment/add";
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post(url)
                    .content(new ObjectMapper().writeValueAsString(firstComment))
                    .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.ipAddress").value(firstComment.getIpAddress()))
                .andReturn();
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

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

    @Test
    public void testFindController() throws Exception
    {
        String url = "/api/v1/comment/find";
        ResultActions mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get(url)
                    .param("movieId", "2")
                    .param("ipAddress", "172.22.22.22"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
        Assert.assertNotNull(mvcResult.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testFindNoParameterController() throws Exception
    {
        String url = "/api/v1/comment/find";
        ResultActions mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
        Assert.assertTrue(mvcResult.andReturn().getResponse().getContentAsString().equals("[]"));
    }

    @Test
    public void testFindOneParameterController() throws Exception
    {
        String url = "/api/v1/comment/find";
        ResultActions mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get(url)
                        .param("movieId", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
        Assert.assertNotNull(mvcResult.andReturn().getResponse().getContentAsString());

        ResultActions mvcResult2 = mockMvc
                .perform(MockMvcRequestBuilders.get(url)
                        .param("ipAddress", "172.22.22.22"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
        Assert.assertNotNull(mvcResult2.andReturn().getResponse().getContentAsString());
    }
}
