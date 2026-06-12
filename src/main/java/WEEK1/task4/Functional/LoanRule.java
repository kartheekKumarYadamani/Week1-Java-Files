package WEEK1.task4.Functional;

import WEEK1.task4.Entity.Customer;

@FunctionalInterface
public interface LoanRule {

    boolean validate(Customer customer);

    default LoanRule and(LoanRule other){
        return customer->this.validate(customer)&&other.validate(customer);
    }

    default LoanRule or(LoanRule other){
        return customer->this.validate(customer)||other.validate(customer);
    }

    default LoanRule negate(){
        return customer->!this.validate(customer);
    }
}