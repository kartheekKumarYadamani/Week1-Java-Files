package WEEK1.task2.Service;

import WEEK1.task2.Entity.Course;
import WEEK1.task2.Entity.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RecommendationService {

    private List<Course> courses;

    public RecommendationService(List<Course> courses) {
        this.courses = courses;
    }

    Predicate<Course> affordableCourse =
            course -> course.getFee() < 20000;

    public Function<Student, List<Course>> recommendCourses =
            student -> {
                List<Course> recommended = new ArrayList<>();
                if(student.getSkills().contains("Java")) {
                    recommended.addAll(
                            courses.stream()
                                    .filter(course ->
                                            course.getTechnology()
                                                    .equalsIgnoreCase("Java"))
                                    .collect(Collectors.toList())
                    );
                }
                if(student.getSkills().contains("Python")) {
                    recommended.addAll(
                            courses.stream()
                                    .filter(course ->
                                            course.getTechnology()
                                                    .equalsIgnoreCase("Python"))
                                    .collect(Collectors.toList())
                    );
                }
                if(student.getBudget() < 20000) {
                    recommended = recommended.stream()
                            .filter(affordableCourse)
                            .collect(Collectors.toList());
                }
                if(student.getExperience() == 0) {
                    recommended = recommended.stream()
                            .filter(course ->
                                    course.getLevel()
                                            .equalsIgnoreCase("Beginner"))
                            .collect(Collectors.toList());
                }
                else if(student.getExperience() >= 3) {
                    recommended = recommended.stream()
                            .filter(course ->
                                    course.getLevel()
                                            .equalsIgnoreCase("Advanced"))
                            .collect(Collectors.toList());
                }
                recommended.sort(
                        Comparator.comparing(
                                Course::getRating
                        ).reversed()
                );
                return recommended;
            };
}