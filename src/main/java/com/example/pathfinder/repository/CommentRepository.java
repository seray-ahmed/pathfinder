package com.example.pathfinder.repository;

import com.example.pathfinder.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * FROM comments WHERE approved = 0", nativeQuery = true)
    List<Comment> findAllUnApprovedComments();

}
