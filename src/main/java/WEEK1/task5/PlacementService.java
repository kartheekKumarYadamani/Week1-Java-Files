package WEEK1.task5;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PlacementService {

    Predicate<Student> graduationRule=s->s.getGraduationYear()>=2022;
    Predicate<Student> percentageRule=s->s.getPercentage()>=60;
    Predicate<Student> backlogRule=s->s.getBacklogs()==0;
    Predicate<Student> mockRule=s->s.getMockRating()>=4;
    Predicate<Student> resumeRule=Student::getResumeAvailable;
    Predicate<Student> feeRule=Student::getFeePaid;
    Predicate<Student> skillRule=s->s.getSkills().contains("Java")||s.getSkills().contains("Python");

    Predicate<Student> eligibleStudent=graduationRule.and(percentageRule).and(backlogRule).and(mockRule).and(resumeRule).and(feeRule).and(skillRule);

    Function<Student,String> rejectionReason=s->{
        StringBuilder reason=new StringBuilder();

        if(!graduationRule.test(s))reason.append("Graduation year less than 2022, ");
        if(!percentageRule.test(s))reason.append("Percentage below 60, ");
        if(!backlogRule.test(s))reason.append("Backlogs available, ");
        if(!mockRule.test(s))reason.append("Mock rating below 4, ");
        if(!resumeRule.test(s))reason.append("Resume missing, ");
        if(!feeRule.test(s))reason.append("Fee not paid, ");
        if(!skillRule.test(s))reason.append("Java/Python skill missing, ");

        return reason.toString();
    };

    Consumer<Student> printEligible=s->System.out.println(s.getStudentName()+" - "+s.getCourseName()+" - Rating : "+s.getMockRating());

    public void generateReport(List<Student> students){

        System.out.println("Eligible Students :");

        List<Student> eligible=students.stream()
                .filter(eligibleStudent)
                .sorted(Comparator.comparing(Student::getMockRating).reversed())
                .collect(Collectors.toList());

        int count=1;

        for(Student s:eligible){
            System.out.print(count++ + ". ");
            printEligible.accept(s);
        }

        System.out.println("\nRejected Students :");

        students.stream()
                .filter(eligibleStudent.negate())
                .forEach(s->System.out.println(s.getStudentName()+" - Reason : "+rejectionReason.apply(s)));

//        System.out.println("\nStudents Grouped By Course :");

//        students.stream()
//                .collect(Collectors.groupingBy(Student::getCourseName))
//                .forEach((course,list)->{
//                    System.out.println(course);
//                    list.forEach(s->System.out.println("  "+s.getStudentName()));
//                });
    }
}