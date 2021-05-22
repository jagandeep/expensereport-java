package com.nelkinda.training;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseReportTest {

    @Test
    public void testMealExpense() {
        Expense breakfact = new Expense();
        breakfact.type = ExpenseType.BREAKFAST;
        breakfact.amount = 1000;
        Expense dinner = new Expense();
        dinner.type = ExpenseType.DINNER;
        dinner.amount = 1000;
        ExpenseReport report = new ExpenseReport();
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        System.setOut(ps);
        Date date = new Date();
        report.printReport(List.of(breakfact,dinner),date);
        String actual = out.toString();
        String expected = "Expenses "+date+"\r\n" +
                "Breakfast\t1000\t \r\n" +
                "Dinner\t1000\t \r\n" +
                "Meal expenses: 2000\r\n" +
                "Total expenses: 2000\r\n";
        assertEquals(expected,actual);
    }

    @Test
    public void testOverExpenseMeal() {
        Expense breakfact1 = new Expense();
        breakfact1.type = ExpenseType.BREAKFAST;
        breakfact1.amount = 1000;
        Expense breakfact2 = new Expense();
        breakfact2.type = ExpenseType.BREAKFAST;
        breakfact2.amount = 1001;
        Expense dinner1 = new Expense();
        dinner1.type = ExpenseType.DINNER;
        dinner1.amount = 5000;
        Expense dinner2 = new Expense();
        dinner2.type = ExpenseType.DINNER;
        dinner2.amount = 5001;
        ExpenseReport report = new ExpenseReport();
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        System.setOut(ps);
        Date date = new Date();
        report.printReport(List.of(breakfact1,breakfact2,dinner1,dinner2),date);
        String actual = out.toString();
        String expected = "Expenses "+date+"\r\n" +
                "Breakfast\t1000\t \r\n" +
                "Breakfast\t1001\tX\r\n" +
                "Dinner\t5000\t \r\n" +
                "Dinner\t5001\tX\r\n" +
                "Meal expenses: 12002\r\n" +
                "Total expenses: 12002\r\n";
        assertEquals(expected,actual);
    }

    @Test
    public void testAllExpenseMeal() {
        Expense breakfact = new Expense();
        breakfact.type = ExpenseType.BREAKFAST;
        breakfact.amount = 1000;

        Expense dinner = new Expense();
        dinner.type = ExpenseType.DINNER;
        dinner.amount = 5001;

        Expense carRental = new Expense();
        carRental.type = ExpenseType.CAR_RENTAL;
        carRental.amount = 2000;

        ExpenseReport report = new ExpenseReport();
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        System.setOut(ps);
        Date date = new Date();
        report.printReport(List.of(breakfact,dinner,carRental),date);
        String actual = out.toString();
        String expected = "Expenses "+date+"\r\n" +
                "Breakfast\t1000\t \r\n" +
                "Dinner\t5001\tX\r\n" +
                "Car Rental\t2000\t \r\n" +
                "Meal expenses: 6001\r\n" +
                "Total expenses: 8001\r\n";
        assertEquals(expected,actual);
    }
}