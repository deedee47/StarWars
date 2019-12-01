package com.deedee.fingertips.deestarwars.repositories;


import com.deedee.fingertips.deestarwars.SuitePrep;
import com.deedee.fingertips.deestarwars.models.Comment;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRepoTest extends SuitePrep {

    private List<Comment> testComments = new ArrayList<>();

    @Test
    public void testSaveAll()
    {
        Timestamp timeCreated = Timestamp.valueOf(LocalDateTime.now());
        Comment testComment = new Comment(movieId , "Great Movie", testIp);
        Comment testComment1 = new Comment(movieId+1 , "Great Movie", testIp);

        testComment.setCreatedDateUtc(timeCreated);
        testComment1.setCreatedDateUtc(timeCreated);

        testComments.add(testComment);
        testComments.add(testComment1);
        commentRepo.saveAll(testComments);

        assertNotNull(testComment.getId());
        assertNotNull(testComment1.getId());
    }

    @Test
    public void testFindById() throws Exception
    {
        Optional<Comment> comment = commentRepo.findById(testCommentId);
        assertNotNull(comment);
        assertEquals(comment.get().getMovieId(), 1);
    }
    @Test
    public void testFindAll()
    {
        Iterable<Comment> allComments = commentRepo.findAll();
        assertNotNull(allComments);
    }

    @Test
    public void testDelete

}
