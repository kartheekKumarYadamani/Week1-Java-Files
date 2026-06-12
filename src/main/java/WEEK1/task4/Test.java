package WEEK1.task4;

import WEEK1.task4.Entity.Customer;
import WEEK1.task4.Service.LoanService;

public class Test {
    public static void main(String[] args) {
        Customer customer=new Customer(101,"Sai",30,80000.0,780,15000.0,"SALARIED",1000000.0);
        LoanService loanService=new LoanService();
        loanService.checkLoan(customer);
    }
}