package com.deedee.fingertips.deestarwars;

import com.deedee.fingertips.deestarwars.models.Comment;
import com.deedee.fingertips.deestarwars.services.CommentService;
import com.deedee.fingertips.deestarwars.services.CommentServiceTest;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({CommentServiceTest.class})
public class SuitePrep {

    @Autowired
    public CommentService commentService;
    public Long testCommentId = 0L;
    public String testIp = "";
    public int movieId = 0;

    @Before
    public void setUp() throws Exception
    {
        testIp = "172.22.22.22";
        movieId = 1;
        Comment testComment = new Comment(movieId , "Great Movie", testIp);
        assertNull(testComment.getId());

       // testComment.setCreatedDateUtc(Timestamp.valueOf(LocalDateTime.now()));
        commentService.save(testComment);
        testCommentId = testComment.getId();
        assertNotNull(testCommentId);
    }

    @After
    public void tearDown()
    {
        commentService.deleteById(testCommentId);
        Optional<Comment> comment = commentService.findById(testCommentId);
        assertEquals(Optional.empty(),comment);
    }
}
