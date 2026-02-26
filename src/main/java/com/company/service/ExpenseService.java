package com.company.service;

import com.company.dto.ExpenseDTO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpenseService {

    ExpenseDTO addExpense(ExpenseDTO dto);

    Page<ExpenseDTO> getAllExpenses(Pageable pageable);

    ExpenseDTO getExpenseById(Long id);

    ExpenseDTO updateExpense(Long id, ExpenseDTO dto);

    void deleteExpense(Long id);

    List<ExpenseDTO> getExpensesByCategoryAndDate(String category,
                                                  LocalDate start,
                                                  LocalDate end);

    Page<ExpenseDTO> searchByTitle(String keyword, Pageable pageable);

    Page<ExpenseDTO> filterByCategory(String category, Pageable pageable);

    Page<ExpenseDTO> filterByAmount(Double min,
                                    Double max,
                                    Pageable pageable);

	Page<ExpenseDTO> getAllExpenses(int page, int size, String sortBy);
}