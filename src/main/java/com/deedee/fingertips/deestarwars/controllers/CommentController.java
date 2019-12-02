package com.deedee.fingertips.deestarwars.controllers;

import com.deedee.fingertips.deestarwars.models.Comment;
import com.deedee.fingertips.deestarwars.repositories.CommentRepo;
import com.deedee.fingertips.deestarwars.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.assertj.core.util.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(method = RequestMethod.POST, value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Comment saveComment(@RequestBody Comment comment)
    {
        Comment result = new Comment();
        if (comment != null)
        {
            result = commentService.save(comment);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Comment> findComments(@RequestParam(value="movieId", defaultValue="0") String movieId , @RequestParam(value="ipAddress", defaultValue="") String ipAddress)
    {
        int movie_id = Integer.parseInt(movieId);
        Iterable<Comment> result = commentService.find(movie_id, ipAddress);
        return (List<Comment>) IterableUtil.toCollection(result);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Comment getComments(@PathVariable("commentId") int commentId) //throws Exception
    {
        Optional<Comment> result = commentService.findById(commentId);
        return result.get();

    }
}
