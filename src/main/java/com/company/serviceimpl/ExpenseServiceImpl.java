package com.company.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.company.Exception.ResourceNotFoundException;
import com.company.dto.ExpenseDTO;
import com.company.entity.Expense;
import com.company.entity.User;
import com.company.repository.ExpenseRepository;
import com.company.repository.UserRepository;
import com.company.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository,
                              UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    // ================= ADD EXPENSE =================
    @Override
    public ExpenseDTO addExpense(ExpenseDTO dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Expense expense = new Expense();
        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setCategory(dto.getCategory());
        expense.setExpenseDate(dto.getExpenseDate());
        expense.setUser(user);

        Expense saved = expenseRepository.save(expense);

        return convertToDTO(saved);
    }

    // ================= GET ALL (USER SPECIFIC) =================
    @Override
    public Page<ExpenseDTO> getAllExpenses(Pageable pageable) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return expenseRepository.findByUser(user, pageable)
                .map(this::convertToDTO);
    }

    // ================= GET BY ID =================
    @Override
    public ExpenseDTO getExpenseById(Long id) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));

        return convertToDTO(expense);
    }

    // ================= DELETE =================
    @Override
    public void deleteExpense(Long id) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));

        expenseRepository.delete(expense);
    }

    // ================= UPDATE =================
    @Override
    public ExpenseDTO updateExpense(Long id, ExpenseDTO dto) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));

        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setCategory(dto.getCategory());
        expense.setExpenseDate(dto.getExpenseDate());

        Expense updated = expenseRepository.save(expense);

        return convertToDTO(updated);
    }

    // ================= DTO CONVERTER =================
    private ExpenseDTO convertToDTO(Expense expense) {
        ExpenseDTO dto = new ExpenseDTO();
        dto.setId(expense.getId());
        dto.setTitle(expense.getTitle());
        dto.setAmount(expense.getAmount());
        dto.setCategory(expense.getCategory());
        dto.setExpenseDate(expense.getExpenseDate());
        return dto;
    }

    // ===== Leave other methods empty if not used =====
    @Override public Page<ExpenseDTO> getAllExpenses(int page, int size, String sortBy) { return null; }
    @Override public Page<ExpenseDTO> filterByCategory(String category, Pageable pageable) { return null; }
    @Override public Page<ExpenseDTO> searchByTitle(String keyword, Pageable pageable) { return null; }
    @Override public Page<ExpenseDTO> filterByAmount(Double min, Double max, Pageable pageable) { return null; }

	@Override
	public List<ExpenseDTO> getExpensesByCategoryAndDate(String category, LocalDate start, LocalDate end) {
		// TODO Auto-generated method stub
		return null;
	}
}