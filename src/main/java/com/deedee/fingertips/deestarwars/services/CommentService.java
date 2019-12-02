package com.deedee.fingertips.deestarwars.services;

import com.deedee.fingertips.deestarwars.models.Comment;
import com.deedee.fingertips.deestarwars.models.Comment_;
import com.deedee.fingertips.deestarwars.repositories.CommentExtraRepo;
import com.deedee.fingertips.deestarwars.repositories.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

@Component
public class CommentService implements CommentRepo, CommentExtraRepo {

    @Autowired
    private EntityManager entityManager;

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
    public <S extends Comment> S save(S comment)
    {
        comment.setCreatedDateUtc(Timestamp.valueOf(LocalDateTime.now()));
        save(comment);
        return comment;
    }

    @Override
    public <S extends Comment> Iterable<S> saveAll(Iterable<S> allComments) {
        Timestamp currentTimestamp =Timestamp.valueOf(LocalDateTime.now());
        allComments.forEach(comment -> comment.setCreatedDateUtc(currentTimestamp));
        saveAll(allComments);
        return allComments;
    }

    @Override
    public Optional<Comment> findById(Integer commentId) {
        Optional<Comment> comment = findById(commentId);
        return comment;
    }

    @Override
    public boolean existsById(Integer commentId) {
        return existsById(commentId);
    }

    @Override
    public Iterable<Comment> findAll() {
        return findAll();
    }

    @Override
    public Iterable<Comment> findAllById(Iterable<Integer> iterable) {
        return findAllById(iterable);
    }

    @Override
    public long count() {
        return count();
    }

    @Override
    public void deleteById(Integer commentIdToDelete) {
        deleteById(commentIdToDelete);
    }

    @Override
    public void delete(Comment commentToDelete) {
        delete(commentToDelete);
    }

    @Override
    public void deleteAll(Iterable<? extends Comment> commentsToDelete) {
        deleteAll(commentsToDelete);
    }

    @Override
    public void deleteAll() {

    }
}
