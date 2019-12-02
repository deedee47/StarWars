package com.deedee.fingertips.deestarwars;

import com.deedee.fingertips.deestarwars.models.Comment;
import com.deedee.fingertips.deestarwars.repositories.CommentRepo;
import com.deedee.fingertips.deestarwars.repositories.CommentRepoTest;
import com.deedee.fingertips.deestarwars.services.CommentServiceTest;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({CommentRepoTest.class, CommentServiceTest.class})
public class SuitePrep {

    @Autowired
    public CommentRepo commentRepo;
    public int testCommentId = 0;
    public String testIp = "";
    public int movieId = 0;

    @Before
    public void setUp() throws Exception
    {
        testIp = "172.22.22.22";
        movieId = 1;
        Comment testComment = new Comment(movieId , "Great Movie", testIp);
        assertNull(testComment.getId());

        testComment.setCreatedDateUtc(Timestamp.valueOf(LocalDateTime.now()));
        commentRepo.save(testComment);
        testCommentId = testComment.getId();
        assertNotNull(testCommentId);
    }

    @After
    public void tearDown()
    {
        commentRepo.deleteById(testCommentId);
        Optional<Comment> comment = commentRepo.findById(testCommentId);
        assertEquals(Optional.empty(), comment);
    }
}
