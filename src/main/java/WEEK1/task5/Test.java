package WEEK1.task5;

import WEEK1.task5.Entity.Student;
import WEEK1.task5.Service.PlacementService;

import java.util.List;

public class Test {
    public static void main(String[] args) {

        List<Student> students=List.of(
                new Student(101,"Sai","Java Fullstack",2023,75.0,0,4.8,List.of("Java","SQL"),true,true),
                new Student(102,"Kartheek","Python Fullstack",2024,80.0,0,4.6,List.of("Python","Django"),true,true),
                new Student(103,"Bhargav","Java Fullstack",2021,65.0,2,3.5,List.of("Java"),false,true),
                new Student(104,"Waseem","Python Fullstack",2023,58.0,0,4.2,List.of("Python"),true,false)
        );

        PlacementService service=new PlacementService();
        service.generateReport(students);
    }
}