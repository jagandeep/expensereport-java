package com.nelkinda.training;

import java.util.Date;
import java.util.List;

enum ExpenseType {
    DINNER("Dinner",5000,true),
    BREAKFAST("Breakfast",1000,true),
    CAR_RENTAL("Car Rental",Integer.MAX_VALUE,false),
    LUNCH("Lunch",2000,true);

    String expenseName;
    Integer overExpense;
    Boolean isMeal;

    ExpenseType(String expenseName,Integer overExpense, Boolean isMeal) {
        this.expenseName = expenseName;
        this.overExpense = overExpense;
        this.isMeal = isMeal;
    }
    boolean isMeal() {
        return this.isMeal;
    }
}

class Expense {
    ExpenseType type;
    int amount;

    public int getMealExpenses() {
        if (type.isMeal()) {
            return amount;
        }
        return 0;
    }

    public String getOverExpensesMarker() {
        return isOverExpensive() ? "X" : " ";
    }

    private boolean isOverExpensive() {
        return amount > type.overExpense;
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
                .map(expense -> expense.type.expenseName
                                + "\t" + expense.amount + "\t"
                                + expense.getOverExpensesMarker())
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
