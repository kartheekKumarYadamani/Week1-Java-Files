package PracticalSession;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Student {
    int id;
    String name;
    String course;
    double feePaid;
    boolean active;

    Student(int id, String name, String course, double feePaid, boolean active) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.feePaid = feePaid;
        this.active = active;
    }

    public String toString() {
        return id + " " + name + " " + course + " " + feePaid + " " + active;
    }
}
class Test{
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student(101, "Raju", "Java", 15000, true),
                new Student(102, "Rani", "Python", 0, false),
                new Student(103, "Kiran", "Java", 10000, true),
                new Student(104, "Anil", "DevOps", 0, false),
                new Student(105, "Suresh", "Python", 20000, true),
                new Student(106, "Mahesh", "Java", 0, true)
        );

        System.out.println("Active Students :");
        students.stream().filter(student->student.active).forEach(student->System.out.println(student));
        System.out.println("=======================================");

        System.out.println("fee paid Students :");
        students.stream().filter(student->student.feePaid>0.0).forEach(student->System.out.println(student));
        System.out.println("===========================================");

        System.out.println("Java Students :");
        students.stream().filter(student->student.course.equalsIgnoreCase("Java")).forEach(student->System.out.println(student));
        System.out.println("===========================================");

        System.out.println("Students Names:");
        students.stream().forEach(student->System.out.println(student.name));
        System.out.println("===========================================");

        System.out.println("Total Fee Collected:");
        double feeCollected = students.stream()
                .mapToDouble(std -> std.feePaid).sum();
        System.out.println(feeCollected);

        System.out.println("Unpaid Students :");
        long count= students.stream()
                        .filter(student->student.feePaid<=0.0).count();
        System.out.println(count);

        System.out.println("Each Course :");
        Map<String,List<Student>> result=students.stream().
                                     collect(Collectors.groupingBy(std-> std.course));
        System.out.println(result);

        System.out.println("Highest Paid Student:");
        Student s=students.stream().sorted(Comparator.comparing((Student std)->std.feePaid).reversed()).findFirst().orElse(null);
        System.out.println(s);

        System.out.println("Top 3 Paid Student:");
        List<Student> list=students.stream().sorted(Comparator.comparing((Student std)->std.feePaid).reversed()).limit(3).collect(Collectors.toList());
        System.out.println(list);


    }
}
