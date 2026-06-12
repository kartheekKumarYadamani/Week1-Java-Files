package WEEK1.task2;

import WEEK1.task2.Entity.Course;
import WEEK1.task2.Entity.Student;
import WEEK1.task2.Service.RecommendationService;

import java.util.List;
import java.util.function.Consumer;

public class Test {

    static int count = 1;

    public static void main(String[] args) {

        List<Course> courses = List.of(
                new Course(1, "Java Fullstack", "Java", "Advanced", 25000.0, "6 Months", 4.8),
                new Course(2, "Spring Boot Microservices", "Java", "Advanced", 18000.0, "3 Months", 4.7),
                new Course(3, "Python Fullstack", "Python", "Advanced", 22000.0, "5 Months", 4.6),
                new Course(4, "Data Science", "Python", "Advanced", 30000.0, "8 Months", 4.9));
        Student student = new Student(101, "Suresh", "B.Tech", List.of("Java"), 3, "Java", 25000.0);
        RecommendationService service = new RecommendationService(courses);
        List<Course> recommendedCourses = service.recommendCourses.apply(student);
        Consumer<Student> print = s -> System.out.println("Recommended Courses for " + s.getStudentName() + ":");
        print.accept(student);
        Consumer<List<Course>> result=course ->{
               course.forEach(recommended-> System.out.println(count++ + ". " + recommended.getCourseName() + " - ₹"
                       + recommended.getFee() + " - Rating: " + recommended.getRating()));
        };
        result.accept(recommendedCourses);
    }
}