package com.deedee.fingertips.deestarwars.repositories;

import com.deedee.fingertips.deestarwars.models.Comment;

public interface CommentExtraRepo {
    public Iterable<Comment> find(int movie_id, String ip_address);
}
