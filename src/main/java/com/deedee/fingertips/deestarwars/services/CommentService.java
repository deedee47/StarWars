package com.deedee.fingertips.deestarwars.services;

import com.deedee.fingertips.deestarwars.models.Comment;
import com.deedee.fingertips.deestarwars.models.Comment_;
import com.deedee.fingertips.deestarwars.repositories.CommentRepo;
import com.deedee.fingertips.deestarwars.interfaces.ICommentService;
import com.deedee.fingertips.deestarwars.utilities.IpAddressResolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements Serializable, ICommentService {
    @Autowired
    EntityManager entityManager;

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    IpAddressResolution ipAddressResolution;

    public CommentService()
    {}

    @Override
    @Cacheable(value = "comments", key = "#movieId")
    public Iterable<Comment> getCommentsByMovieId(int movieId)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Comment> criteriaQuery = criteriaBuilder.createQuery(Comment.class);
        Root<Comment> allComments = criteriaQuery.from(Comment.class);
        criteriaQuery = criteriaQuery.select(allComments);

        Predicate joinedPredicates = criteriaBuilder.conjunction();
        if(movieId > 0)
        {
            joinedPredicates = criteriaBuilder.and(joinedPredicates, criteriaBuilder.equal(allComments.get(Comment_.MOVIE_ID), movieId));
        }

        if(joinedPredicates.getExpressions().size() > 0)
        {
            criteriaQuery = criteriaQuery
                                        .where(joinedPredicates)
                                        .orderBy(criteriaBuilder.desc(allComments.get(Comment_.CREATED_DATE_UTC)));
            TypedQuery<Comment> query = entityManager.createQuery(criteriaQuery);
            List<Comment> result = query.getResultList();
            return result;
        }
        else
        {
            return new ArrayList<>();
        }

    }

    @Override
    @CachePut(value = "comments")
    public Comment save(Comment comment)
    {
        comment.setCreatedDateUtc(Timestamp.valueOf(LocalDateTime.now()));
        comment.setIpAddress(ipAddressResolution.getIpAddress());
        commentRepo.save(comment);
        return comment;
    }

    @Override
    @CachePut(value = "comments")
    public  Iterable<Comment> saveAll(Iterable<Comment> entities) {
        Timestamp currentTimestamp =Timestamp.valueOf(LocalDateTime.now());
        entities.forEach(comment ->
                                    {
                                        comment.setCreatedDateUtc(currentTimestamp);
                                        comment.setIpAddress(ipAddressResolution.getIpAddress());
                                    });
        commentRepo.saveAll(entities);
        return entities;
    }

    @Override
    @Cacheable(value = "oneComment", key = "#id")
    public Comment findById(Long id) {
        Optional<Comment> comment = commentRepo.findById(id);
        if(comment.isPresent())
        {
            return  comment.get();
        }
        return new Comment();
    }

    public boolean existsById(Long commentId) {
        return commentRepo.existsById(commentId);
    }

    @Cacheable(value = "oneComment")
    public Iterable<Comment> findAll() {
        return commentRepo.findAll();
    }

    public Iterable<Comment> findAllById(Iterable<Long> commentIds) {
        return commentRepo.findAllById(commentIds);
    }

    public long count() {
        return commentRepo.count();
    }

    public long countById(int movieId)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Comment> allComments = criteriaQuery.from(Comment.class);
        criteriaQuery.select(criteriaBuilder.count(allComments));
        criteriaQuery.where(criteriaBuilder.equal(allComments.get(Comment_.MOVIE_ID), movieId));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @CacheEvict(value = "oneComment", key = "#commentIdToDelete")
    public void deleteById(Long commentIdToDelete) {
        if(commentRepo.existsById(commentIdToDelete))
        commentRepo.deleteById(commentIdToDelete);
    }

    public void delete(Comment commentToDelete) {
        commentRepo.delete(commentToDelete);
    }

    @CacheEvict(value = "oneComment", key = "#a0")
    public void deleteAll(Iterable<Comment> commentsToDelete) {
        commentRepo.deleteAll(commentsToDelete);
    }

    @Override
    public void deleteAll() {

    }
}
