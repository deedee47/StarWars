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
@RequestMapping("/api/v1/comment")
public class CommentController {

    @Autowired
    public ICommentService commentService;

    @ApiOperation(value = "Add a new comment for a movie")
    @RequestMapping(method = RequestMethod.POST, value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
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

    @ApiOperation(value = "Find Comments for a movie using Movie ID")
    @RequestMapping(method = RequestMethod.GET, value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Comment> getCommentsByMovieId(@RequestParam(value="movieId", defaultValue="0", required = false) String movieId)//, ServerHttpRequest request)
    {
        int movieIdInt = Integer.parseInt(movieId);
        Iterable<Comment> result = commentService.getCommentsByMovieId(movieIdInt);
        return (List<Comment>) IterableUtil.toCollection(result);
    }

    @ApiOperation(value = "Get a Comment for a movie using Comment ID")
    @RequestMapping(method = RequestMethod.GET, value = "/get/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Comment getComment(@PathVariable("commentId") Long commentId) //throws Exception
    {
        Comment result = commentService.findById(commentId);
        return result;
    }

}
