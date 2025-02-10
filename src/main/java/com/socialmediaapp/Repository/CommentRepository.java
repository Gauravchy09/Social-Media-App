package com.socialmediaapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialmediaapp.Entity.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
