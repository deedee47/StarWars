package com.deedee.fingertips.deestarwars.interfaces;

import com.deedee.fingertips.deestarwars.models.Comment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ICommentService {
    Iterable<Comment> getCommentsByMovieId(int movieId);
    Comment save(Comment comment);
    Iterable<Comment> saveAll(Iterable<Comment> entities);
    Comment findById(Long id) ;
    boolean existsById(Long commentId);
    Iterable<Comment> findAll();
    Iterable<Comment> findAllById(Iterable<Long> iterable);
    long count();
    long countById(int movieId);
    void deleteById(Long commentIdToDelete);
    void delete(Comment commentToDelete);
    void deleteAll(Iterable<Comment> commentsToDelete);
    void deleteAll();
}
