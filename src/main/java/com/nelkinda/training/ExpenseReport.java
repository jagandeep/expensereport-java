package com.nelkinda.training;

import java.util.Date;
import java.util.List;

enum ExpenseType {
    DINNER("Dinner"), BREAKFAST("Breakfast"), CAR_RENTAL("Car Rental");

    ExpenseType(String expenseType) {

    }
}

class Expense {
    ExpenseType type;
    int amount;

    public String getExpenseName() {
        String expenseName = "";
        switch (type) {
        case DINNER:
            return "Dinner";
        case BREAKFAST:
            return "Breakfast";
        case CAR_RENTAL:
            return "Car Rental";
        }
        return expenseName;
    }

    public int getMealExpenses() {
        if (type == ExpenseType.DINNER || type == ExpenseType.BREAKFAST) {
            return amount;
        }
        return 0;
    }

    String getOverExpensesMarker() {
        return type == ExpenseType.DINNER && amount > 5000
                || type == ExpenseType.BREAKFAST && amount > 1000 ? "X" : " ";
    }
}

public class ExpenseReport {
    public void printReport(List<Expense> expenses) {
        this.printReport(expenses, new Date());
    }
    public void printReport(List<Expense> expenses, Date date) {
        printHeader(date);
        printBody(expenses);
        printTail(expenses);
    }

    private void printTail(List<Expense> expenses) {
        System.out.println("Meal expenses: " + sumMealExpense(expenses));
        System.out.println("Total expenses: " + sumTotalExpense(expenses));
    }

    private void printBody(List<Expense> expenses) {
        expenses.stream()
                .map(expense -> expense.getExpenseName() + "\t" + expense.amount + "\t" + expense.getOverExpensesMarker())
                .forEach(System.out::println);
    }

    private void printHeader(Date date) {
        System.out.println("Expenses " + date);
    }

    private int sumMealExpense(List<Expense> expenses) {
        return expenses.stream()
                .mapToInt(Expense::getMealExpenses)
                .sum();
    }

    private int sumTotalExpense(List<Expense> expenses) {
        return expenses.stream()
                .mapToInt(t -> t.amount)
                .sum();
    }

}
