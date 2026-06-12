package WEEK1.task4.Service;

import WEEK1.task4.Entity.Customer;
import WEEK1.task4.Functional.LoanRule;

import java.util.ArrayList;
import java.util.List;

public class LoanService {

    public void checkLoan(Customer customer){

        List<String> reasons=new ArrayList<>();

        LoanRule ageRule=c->c.getAge()>=21&&c.getAge()<=60;
        LoanRule salaryRule=c->c.getMonthlySalary()>=50000;
        LoanRule creditRule=c->c.getCreditScore()>=750;
        LoanRule emiRule=c->c.getExistingEmi()<c.getMonthlySalary()*0.40;
        LoanRule loanAmountRule=c->c.getRequestedLoanAmount()<=c.getMonthlySalary()*20;
        LoanRule employmentRule=c->c.getEmploymentType().equalsIgnoreCase("SALARIED")||c.getEmploymentType().equalsIgnoreCase("BUSINESS");

        if(!ageRule.validate(customer))reasons.add("Age should be between 21 and 60");
        if(!salaryRule.validate(customer))reasons.add("Salary should be greater than or equal to 50000");
        if(!creditRule.validate(customer))reasons.add("Credit score is below 750");
        if(!emiRule.validate(customer))reasons.add("Existing EMI is more than allowed limit");
        if(!loanAmountRule.validate(customer))reasons.add("Requested loan amount exceeds limit");
        if(!employmentRule.validate(customer))reasons.add("Employment type is invalid");

        System.out.println("Customer : "+customer.getCustomerName());

        if(reasons.isEmpty()){
            System.out.println("Loan Status : APPROVED");
            System.out.println("Reason : All eligibility conditions satisfied");
        }
        else{
            System.out.println("Loan Status : REJECTED");
            System.out.println("Failed Rules : ");
            reasons.forEach(reason->System.out.println("- "+reason));
        }
    }
}