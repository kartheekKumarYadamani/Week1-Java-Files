package WEEK1.task1.Service;

import WEEK1.task1.Entity.Employee;

@FunctionalInterface
public interface SalaryProcessor {
    double process(Employee employee);
}
