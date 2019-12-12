package com.deedee.fingertips.deestarwars.services;

import com.deedee.fingertips.deestarwars.models.Comment;
import com.deedee.fingertips.deestarwars.models.Comment_;
import com.deedee.fingertips.deestarwars.repositories.CommentRepo;
import com.deedee.fingertips.deestarwars.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public EntityManager entityManager;

    @Autowired
    public CommentRepo commentRepo;

    public CommentService()
    {}

    @Override
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

    @Override
    public Comment save(Comment comment)
    {
        comment.setCreatedDateUtc(Timestamp.valueOf(LocalDateTime.now()));
        commentRepo.save(comment);
        return comment;
    }

    @Override
    public  Iterable<Comment> saveAll(Iterable<Comment> entities) {
        Timestamp currentTimestamp =Timestamp.valueOf(LocalDateTime.now());
        entities.forEach(comment -> comment.setCreatedDateUtc(currentTimestamp));
        commentRepo.saveAll(entities);
        return entities;
    }

    @Override
    public Optional<Comment> findById(Long integer) {
        Optional<Comment> comment = commentRepo.findById(integer);
        return comment;
    }

    public boolean existsById(Long commentId) {
        return commentRepo.existsById(commentId);
    }

    public Iterable<Comment> findAll() {
        return commentRepo.findAll();
    }

    public Iterable<Comment> findAllById(Iterable<Long> iterable) {
        return commentRepo.findAllById(iterable);
    }

    public long count() {
        return commentRepo.count();
    }

    public long countById(int movie_id)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Comment> allComments = criteriaQuery.from(Comment.class);
        criteriaQuery.select(criteriaBuilder.count(allComments));
        criteriaQuery.where(criteriaBuilder.equal(allComments.get(Comment_.MOVIE_ID), movie_id));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    public void deleteById(Long commentIdToDelete) {
        if(commentRepo.existsById(commentIdToDelete))
        commentRepo.deleteById(commentIdToDelete);
    }

    public void delete(Comment commentToDelete) {
        commentRepo.delete(commentToDelete);
    }

    public void deleteAll(Iterable<Comment> commentsToDelete) {
        commentRepo.deleteAll(commentsToDelete);
    }

    @Override
    public void deleteAll() {

    }
}
