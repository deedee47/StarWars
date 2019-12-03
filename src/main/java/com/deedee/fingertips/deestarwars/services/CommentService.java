package com.deedee.fingertips.deestarwars.services;

import com.deedee.fingertips.deestarwars.models.Comment;
import com.deedee.fingertips.deestarwars.models.Comment_;
import com.deedee.fingertips.deestarwars.repositories.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    public EntityManager entityManager;

    @Autowired
    public CommentRepo commentRepo;

    public Iterable<Comment> find(int movie_id, String ip_address)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Comment> criteriaQuery = criteriaBuilder.createQuery(Comment.class);
        Root<Comment> allComments = criteriaQuery.from(Comment.class);
        criteriaQuery = criteriaQuery.select(allComments);

        Predicate joinedPredicates = criteriaBuilder.conjunction();
        if(movie_id > 0)
        {
            joinedPredicates = criteriaBuilder.and(joinedPredicates, criteriaBuilder.equal(allComments.get(Comment_.MOVIE_ID), movie_id));
        }
        if(!ip_address.isEmpty())
        {
           joinedPredicates = criteriaBuilder.and(joinedPredicates, criteriaBuilder.equal(allComments.get(Comment_.IP_ADDRESS), ip_address.trim()));
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

    public Comment save(Comment comment)
    {
        comment.setCreatedDateUtc(Timestamp.valueOf(LocalDateTime.now()));
        commentRepo.save(comment);
        return comment;
    }

    public Iterable<Comment> saveAll(Iterable<Comment> allComments) {
        Timestamp currentTimestamp =Timestamp.valueOf(LocalDateTime.now());
        allComments.forEach(comment -> comment.setCreatedDateUtc(currentTimestamp));
        commentRepo.saveAll(allComments);
        return allComments;
    }

    public Optional<Comment> findById(Integer commentId) {
        Optional<Comment> comment = commentRepo.findById(commentId);
        return comment;
    }

    public boolean existsById(Integer commentId) {
        return commentRepo.existsById(commentId);
    }

    public Iterable<Comment> findAll() {
        return commentRepo.findAll();
    }

    public Iterable<Comment> findAllById(Iterable<Integer> iterable) {
        return commentRepo.findAllById(iterable);
    }

    public long count() {
        return commentRepo.count();
    }

    public void deleteById(Integer commentIdToDelete) {
        commentRepo.deleteById(commentIdToDelete);
    }

    public void delete(Comment commentToDelete) {
        commentRepo.delete(commentToDelete);
    }

    public void deleteAll(Iterable<? extends Comment> commentsToDelete) {
        commentRepo.deleteAll(commentsToDelete);
    }

    public void deleteAll() {

    }
}
