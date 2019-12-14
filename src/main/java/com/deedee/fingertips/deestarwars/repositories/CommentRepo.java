package com.deedee.fingertips.deestarwars.repositories;

import com.deedee.fingertips.deestarwars.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepo extends JpaRepository<Comment, Long>
{
}
