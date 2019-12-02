package com.deedee.fingertips.deestarwars.controller;

import com.deedee.fingertips.deestarwars.controllers.CommentController;
import com.deedee.fingertips.deestarwars.models.Comment;
import com.deedee.fingertips.deestarwars.services.CommentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentControllerTest {

    private MockMvc mockMvc;

    @Before
    public void start() {
        mockMvc = MockMvcBuilders.standaloneSetup(CommentController.class).build();
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
