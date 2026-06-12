package WEEK1.task10;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

class Student{
    private Integer studentId;
    private String studentName;

    public Student(Integer studentId,String studentName){
        this.studentId=studentId;
        this.studentName=studentName;
    }

    public Integer getStudentId(){return studentId;}
    public String getStudentName(){return studentName;}
}

class Course{
    private Integer courseId;
    private String courseName;
    private Double fee;
    private String trainerName;

    public Course(Integer courseId,String courseName,Double fee,String trainerName){
        this.courseId=courseId;
        this.courseName=courseName;
        this.fee=fee;
        this.trainerName=trainerName;
    }

    public Integer getCourseId(){return courseId;}
    public String getCourseName(){return courseName;}
    public Double getFee(){return fee;}
    public String getTrainerName(){return trainerName;}
}

class Payment{
    private Integer paymentId;
    private String studentName;
    private Double amount;
    private String status;

    public Payment(Integer paymentId,String studentName,Double amount,String status){
        this.paymentId=paymentId;
        this.studentName=studentName;
        this.amount=amount;
        this.status=status;
    }

    public Integer getPaymentId(){return paymentId;}
    public String getStudentName(){return studentName;}
    public Double getAmount(){return amount;}
    public String getStatus(){return status;}
}

class Enrollment{
    private Integer enrollmentId;
    private String studentName;
    private String courseName;
    private String admissionMonth;

    public Enrollment(Integer enrollmentId,String studentName,String courseName,String admissionMonth){
        this.enrollmentId=enrollmentId;
        this.studentName=studentName;
        this.courseName=courseName;
        this.admissionMonth=admissionMonth;
    }

    public Integer getEnrollmentId(){return enrollmentId;}
    public String getStudentName(){return studentName;}
    public String getCourseName(){return courseName;}
    public String getAdmissionMonth(){return admissionMonth;}
}

@FunctionalInterface
interface ReportGenerator<T,R>{
    R generate(List<T> data);
}

class Test {

    public static void main(String[] args) {

        List<Course> courses=List.of(
                new Course(1,"Java Fullstack",30000.0,"Ramesh"),
                new Course(2,"Python Fullstack",28000.0,"Suresh"),
                new Course(3,"DevOps",35000.0,"Mahesh"),
                new Course(4,"Data Science",40000.0,"Ramesh"),
                new Course(5,"AWS",25000.0,"Mahesh"),
                new Course(6,"Spring Boot",32000.0,"Suresh")
        );

        List<Payment> payments=List.of(
                new Payment(1,"Sai",30000.0,"PAID"),
                new Payment(2,"Ravi",28000.0,"PAID"),
                new Payment(3,"Kiran",35000.0,"PENDING"),
                new Payment(4,"Sneha",40000.0,"PAID"),
                new Payment(5,"Teja",25000.0,"PENDING")
        );

        List<Enrollment> enrollments=List.of(
                new Enrollment(1,"Sai","Java Fullstack","JAN"),
                new Enrollment(2,"Ravi","Java Fullstack","JAN"),
                new Enrollment(3,"Kiran","Python Fullstack","FEB"),
                new Enrollment(4,"Sneha","DevOps","FEB"),
                new Enrollment(5,"Teja","Data Science","MAR"),
                new Enrollment(6,"Arun","Java Fullstack","MAR")
        );

        Predicate<Payment> paid=p->p.getStatus().equalsIgnoreCase("PAID");

        Predicate<Payment> pending=p->p.getStatus().equalsIgnoreCase("PENDING");

        Function<Course,String> trainer=Course::getTrainerName;

        Supplier<String> reportHeader=()->"========== REPORT ==========";

        Consumer<String> print=System.out::println;

        ReportGenerator<Payment,Double> totalRevenue=
                list->list.stream()
                        .filter(paid)
                        .mapToDouble(Payment::getAmount)
                        .sum();

        ReportGenerator<Payment,Double> pendingRevenue=
                list->list.stream()
                        .filter(pending)
                        .mapToDouble(Payment::getAmount)
                        .sum();

        ReportGenerator<Enrollment,Map<String,Long>> courseWiseEnrollment=
                list->list.stream()
                        .collect(Collectors.groupingBy(Enrollment::getCourseName,Collectors.counting()));

        ReportGenerator<Course,Map<String,List<String>>> trainerWiseCourses=
                list->list.stream()
                        .collect(Collectors.groupingBy(Course::getTrainerName,Collectors.mapping(Course::getCourseName,Collectors.toList())));

        ReportGenerator<Enrollment,Map<String,Long>> monthlyAdmissions=
                list->list.stream()
                        .collect(Collectors.groupingBy(Enrollment::getAdmissionMonth,Collectors.counting()));

        print.accept(reportHeader.get());

        System.out.println("\nCourse Wise Enrollment Report\n");

        courseWiseEnrollment.generate(enrollments)
                .forEach((course,count)->System.out.println(course+" : "+count+" Students"));

        System.out.println("\nTotal Revenue : ₹"+totalRevenue.generate(payments));

        System.out.println("\nPending Payments : ₹"+pendingRevenue.generate(payments));

        System.out.println("\nTrainer Wise Courses\n");

        trainerWiseCourses.generate(courses)
                .forEach((trainerName,courseList)->System.out.println(trainerName+" -> "+courseList));

        System.out.println("\nMonthly Admissions\n");

        monthlyAdmissions.generate(enrollments)
                .forEach((month,count)->System.out.println(month+" -> "+count));

        System.out.println("\nTop 5 High Fee Courses\n");

        courses.stream()
                .sorted(Comparator.comparing(Course::getFee).reversed())
                .limit(5)
                .forEach(course->System.out.println(course.getCourseName()+" -> ₹"+course.getFee()));

    }

}