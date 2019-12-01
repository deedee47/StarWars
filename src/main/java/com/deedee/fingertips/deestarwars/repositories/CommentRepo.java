package com.deedee.fingertips.deestarwars.repositories;

import com.deedee.fingertips.deestarwars.models.Comment;
import org.joda.time.DateTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Integer>
{

}
