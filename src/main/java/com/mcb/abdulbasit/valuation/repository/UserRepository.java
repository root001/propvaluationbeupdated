package com.mcb.abdulbasit.valuation.repository;

import com.mcb.abdulbasit.valuation.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByUsername(String username);
}
