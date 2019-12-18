package com.deedee.fingertips.deestarwars.controllers;

import com.deedee.fingertips.deestarwars.models.Comment;
import com.deedee.fingertips.deestarwars.interfaces.ICommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.assertj.core.util.*;

import java.util.List;

@RestController
@RequestMapping
public class CommentController {

    @Autowired
    public ICommentService commentService;

    @ApiOperation(value = "Add a new comment for a movie")
    @RequestMapping(method = RequestMethod.POST, value = "/comments", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Comment saveComment(@ApiParam (value = "Comment Model") @RequestBody Comment comment)
    {
        Comment result = new Comment();
        if (comment != null)
        {
            result = commentService.save(comment);
        }
        return result;
    }

    @ApiOperation(value = "Get Comments for a Movie using Movie ID")
    @RequestMapping(method = RequestMethod.GET, value = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Comment> getCommentsByMovieId(@RequestParam (value="movieId", defaultValue="0") int movieId)
    {
        Iterable<Comment> result = commentService.getCommentsByMovieId(movieId);
        return (List<Comment>) IterableUtil.toCollection(result);
    }

    @ApiOperation(value = "Get a Comment using Comment ID")
    @RequestMapping(method = RequestMethod.GET, value = "/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Comment getCommentByCommentId(@PathVariable("commentId") Long commentId)
    {
        Comment result = commentService.findById(commentId);
        return result;
    }

}
