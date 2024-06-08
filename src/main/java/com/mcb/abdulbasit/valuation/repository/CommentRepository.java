package com.mcb.abdulbasit.valuation.repository;

import com.mcb.abdulbasit.valuation.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
