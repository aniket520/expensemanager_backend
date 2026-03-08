package com.company.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.company.dto.ExpenseDTO;
import com.company.service.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // ================= ADD EXPENSE =================
    @PostMapping
    public ExpenseDTO addExpense(@Valid @RequestBody ExpenseDTO dto) {
        return expenseService.addExpense(dto);
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "Expense deleted successfully";
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public ExpenseDTO getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public ExpenseDTO updateExpense(@PathVariable Long id, @Valid @RequestBody ExpenseDTO dto) {
        return expenseService.updateExpense(id, dto);
    }

    // ================= FILTER BY CATEGORY & DATE =================
    @GetMapping("/filter")
    public List<ExpenseDTO> filterExpenses(
            @RequestParam String category,
            @RequestParam String startDate,
            @RequestParam String endDate) {

        return expenseService.getExpensesByCategoryAndDate(
                category,
                LocalDate.parse(startDate),
                LocalDate.parse(endDate)
        );
    }

    // ================= PAGINATION WITH SORT =================
    @GetMapping("/page")
    public Page<ExpenseDTO> getExpenses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "expenseDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        return expenseService.getAllExpenses(pageable);
    }

    // ================= GET ALL WITH PAGINATION =================
    @GetMapping
    public Page<ExpenseDTO> getAllExpenses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("expenseDate").descending());
        return expenseService.getAllExpenses(pageable);
    }

    // ================= SEARCH BY TITLE =================
    @GetMapping("/search")
    public Page<ExpenseDTO> searchByTitle(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("expenseDate").descending());
        return expenseService.searchByTitle(keyword, pageable);
    }

    // ================= FILTER BY CATEGORY =================
    @GetMapping("/filter/category")
    public Page<ExpenseDTO> filterByCategory(
            @RequestParam String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("expenseDate").descending());
        return expenseService.filterByCategory(category, pageable);
    }

    // ================= FILTER BY AMOUNT =================
    @GetMapping("/filter/amount")
    public Page<ExpenseDTO> filterByAmount(
            @RequestParam Double min,
            @RequestParam Double max,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("expenseDate").descending());
        return expenseService.filterByAmount(min, max, pageable);
    }

}
