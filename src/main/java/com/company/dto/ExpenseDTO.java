package com.company.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ExpenseDTO {

	    private Long id;
	    
	    
	    private LocalDate expenseDate;


	    @NotBlank(message = "Title cannot be empty")
	    private String title;

	    @Positive(message = "Amount must be greater than zero")
	    private double amount;

	    @NotBlank(message = "Category is required")
	    private String category;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public LocalDate getExpenseDate() {
			return expenseDate;
		}

		public void setExpenseDate(LocalDate expenseDate) {
			this.expenseDate = expenseDate;
		}

		

	    
	}


