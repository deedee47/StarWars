package com.deedee.fingertips.deestarwars.repositories;

import com.deedee.fingertips.deestarwars.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepo extends CrudRepository<Comment, Integer>
{

}
