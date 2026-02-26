package com.company.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

import com.company.entity.Expense;
import com.company.entity.User;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT e FROM Expense e WHERE LOWER(e.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Expense> searchByTitle(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Expense e WHERE e.category = :category AND e.expenseDate BETWEEN :start AND :end")
    List<Expense> findByCategoryAndDateRange(
            @Param("category") String category,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );

    Page<Expense> findByCategoryIgnoreCase(String category, Pageable pageable);

    @Query("SELECT e FROM Expense e WHERE e.amount BETWEEN :min AND :max")
    Page<Expense> findByAmountRange(@Param("min") Double min,
                                    @Param("max") Double max,
                                    Pageable pageable);

    // Corrected: find expenses by user with pagination
    Page<Expense> findByUser(User user, Pageable pageable);
}