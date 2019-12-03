package com.deedee.fingertips.deestarwars.services;

import com.deedee.fingertips.deestarwars.SuitePrep;
import com.deedee.fingertips.deestarwars.models.Comment;
import org.assertj.core.util.IterableUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest extends SuitePrep {

    @Autowired
    public CommentService commentService;

    @Test
    public void testFindByMovieId()
    {
        Iterable<Comment> commentResult = commentService.find(movieId, "");
        assertTrue(IterableUtil.sizeOf(commentResult) > 0);
    }

    @Test
    public void testFindNone()
    {
        Iterable<Comment> noCommentResult = commentService.find(0, "");
        assertTrue(IterableUtil.sizeOf(noCommentResult) == 0);
    }

    @Test
    public void testFindByIpAddress()
    {
        Iterable<Comment> commentResult = commentService.find(0, testIp);
        assertTrue(IterableUtil.sizeOf(commentResult) > 0);
    }


}
