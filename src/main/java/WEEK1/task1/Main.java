package WEEK1.task1;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static int hike=0;
    public static void main(String[] args) {
      Employee emp=new Employee(111,"Sai","Tech","Dev",5,
              20000.0,5.0);
        Predicate<Employee> rating=a-> emp.getPerformanceRating()>=4.5;
        Predicate<Employee> exp=a-> emp.getExperience()>=5;
        Predicate<Employee> developer=a-> emp.getRole().equalsIgnoreCase("Dev");
        Predicate<Employee> tester=a->emp.getRole().equalsIgnoreCase("Tester");


        Function<Employee,Double> processor= employee->{
            if(employee.getPerformanceRating()<3)return employee.getSalary();

            if(rating.test(emp))hike+=20;
            if(exp.test(emp))hike+=15;
            if(developer.test(emp))hike+=10;
            if(tester.test(emp))hike+=8;
            return employee.getSalary()+(employee.getSalary()*hike/100);
        };

        double newSalary = processor.apply(emp);

        Consumer<Employee> consume= e->{
            System.out.println("Employee Name : " + e.getEmployeeName());
            System.out.println("Role : " + e.getRole());
            System.out.println("Old Salary : " + e.getSalary());
            System.out.println("Hike Applied : " +hike+"%");
            System.out.println("New Salary : " + newSalary);
        };
        consume.accept(emp);
        }
}
