package com.mcb.abdulbasit.valuation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcb.abdulbasit.valuation.constant.AppConstants;
import com.mcb.abdulbasit.valuation.model.Users;
import com.mcb.abdulbasit.valuation.model.dto.UserResponse;
import com.mcb.abdulbasit.valuation.service.UserService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstants.USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * findAllUsers
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Users>> findAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * findUser
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<UserResponse> findUser(@PathVariable Integer id) {
        if(id < 0)
            throw new IllegalArgumentException("Illegal id provided.");
        Users user = userService.getUser(id);
        return ResponseEntity.ok(new UserResponse(user.getId(), user.getUsername(), user.getFullname(),
                user.getRole().name(), user.getBusinessUnit(), user.getContactNumber()) );
    }

    @GetMapping("/save")
    public ResponseEntity<Users> create() throws Exception {
        return ResponseEntity.ok(userService.create() );
    }

}
