package com.deedee.fingertips.deestarwars.services;

import com.deedee.fingertips.deestarwars.SuitePrep;
import com.deedee.fingertips.deestarwars.models.Comment;
import org.assertj.core.util.IterableUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest extends SuitePrep {
    private List<Comment> testComments = new ArrayList<>();

    @Test
    public void testFindById() throws Exception
    {
        Comment comment = commentService.findById(testCommentId);
        assertNotNull(comment);
        assertEquals(comment.getMovieId(), 1);
    }
    @Test
    public void testFindAll()
    {
        Iterable<Comment> allComments = commentService.findAll();
        assertNotNull(allComments);
    }

    @Test
    public void testExistsById()
    {
        assertTrue(commentService.existsById(testCommentId));
    }

    @Test
    public void testCount()
    {
        assertTrue(commentService.count() > 0);
    }

    @Test
    public void testCountByMovieId()
    {
        assertTrue(commentService.countById(movieId) > 0);
    }

    @Test
    public void testSaveAll()
    {
        Timestamp timeCreated = Timestamp.valueOf(LocalDateTime.now());
        Comment testComment = new Comment(movieId , "Great Movie");
        Comment testComment1 = new Comment(movieId+1 , "Great Movie");

        testComment.setCreatedDateUtc(timeCreated);
        testComment1.setCreatedDateUtc(timeCreated);

        testComments.add(testComment);
        testComments.add(testComment1);
        commentService.saveAll(testComments);

        assertNotNull(testComment.getId());
        assertNotNull(testComment1.getId());
    }

    @Test
    public void testDeleteAll() throws Exception
    {
        commentService.deleteAll(testComments);
        Iterable<Comment> comment = commentService.findAllById(testComments.stream().map(Comment::getId).collect(Collectors.toList()));
        assertTrue(IterableUtil.sizeOf(comment) == 0);
    }

    @Test
    public void testFindByMovieId()
    {
        Iterable<Comment> commentResult = commentService.getCommentsByMovieId(movieId);
        assertTrue(IterableUtil.sizeOf(commentResult) > 0);
    }

    @Test
    public void testFindNone()
    {
        Iterable<Comment> noCommentResult = commentService.getCommentsByMovieId(0);
        assertTrue(IterableUtil.sizeOf(noCommentResult) == 0);
    }
}
