package com.mcb.abdulbasit.valuation.repository;

import com.mcb.abdulbasit.valuation.model.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Integer> {
}
