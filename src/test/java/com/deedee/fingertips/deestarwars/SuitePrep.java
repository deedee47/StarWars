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
    public int movieId = 0;

    @Before
    public void setUp() throws Exception
    {
        movieId = 1;
        Comment testComment = new Comment(movieId , "Great Movie");
        assertNull(testComment.getId());

        commentService.save(testComment);
        testCommentId = testComment.getId();
        assertNotNull(testCommentId);
    }

    @After
    public void tearDown()
    {
        commentService.deleteById(testCommentId);
        boolean commentAvailable = commentService.existsById(testCommentId);
        assertFalse(commentAvailable);
    }
}
