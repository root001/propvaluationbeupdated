package com.mcb.abdulbasit.valuation.service;

import com.mcb.abdulbasit.valuation.model.Comment;
import com.mcb.abdulbasit.valuation.repository.BorrowerRepository;
import com.mcb.abdulbasit.valuation.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment createComment(Comment comment){
        if(ObjectUtils.isEmpty(comment) )
            throw new IllegalArgumentException("no comment");

        return  commentRepository.save(comment);

    }
}
