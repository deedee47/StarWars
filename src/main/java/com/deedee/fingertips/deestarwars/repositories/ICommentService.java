package com.deedee.fingertips.deestarwars.repositories;

import com.deedee.fingertips.deestarwars.models.Comment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ICommentService {
    Iterable<Comment> find(int movie_id, String ip_address);
    Comment save(Comment comment);
    Iterable<Comment> saveAll(Iterable<Comment> entities);
    Optional<Comment> findById(Long integer) ;
    boolean existsById(Long commentId);
    Iterable<Comment> findAll();
    Iterable<Comment> findAllById(Iterable<Long> iterable);
    long count();
    long countById(int movie_id);
    void deleteById(Long commentIdToDelete);
    void delete(Comment commentToDelete);
    void deleteAll(Iterable<Comment> commentsToDelete);
    void deleteAll();
}
