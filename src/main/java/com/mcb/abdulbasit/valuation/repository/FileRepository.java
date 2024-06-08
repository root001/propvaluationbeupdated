package com.mcb.abdulbasit.valuation.repository;

import com.mcb.abdulbasit.valuation.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
}
