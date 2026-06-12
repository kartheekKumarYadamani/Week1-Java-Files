package WEEK1.task9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class InterviewFeedback {
    private Integer studentId;
    private String studentName;
    private Double technicalRating;
    private Double communicationRating;
    private Double codingRating;
    private Double confidenceRating;
    private Double problemSolvingRating;
    private List<String> questionsAsked;
    private List<String> strengths;
    private List<String> weaknesses;

    public InterviewFeedback(Integer studentId, String studentName, Double technicalRating, Double communicationRating, Double codingRating,
                             Double confidenceRating, Double problemSolvingRating, List<String> questionsAsked, List<String> strengths, List<String> weaknesses) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.technicalRating = technicalRating;
        this.communicationRating = communicationRating;
        this.codingRating = codingRating;
        this.confidenceRating = confidenceRating;
        this.problemSolvingRating = problemSolvingRating;
        this.questionsAsked = questionsAsked;
        this.strengths = strengths;
        this.weaknesses = weaknesses;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Double getTechnicalRating() {
        return technicalRating;
    }

    public void setTechnicalRating(Double technicalRating) {
        this.technicalRating = technicalRating;
    }

    public Double getCommunicationRating() {
        return communicationRating;
    }

    public void setCommunicationRating(Double communicationRating) {
        this.communicationRating = communicationRating;
    }

    public Double getCodingRating() {
        return codingRating;
    }

    public void setCodingRating(Double codingRating) {
        this.codingRating = codingRating;
    }

    public Double getConfidenceRating() {
        return confidenceRating;
    }

    public void setConfidenceRating(Double confidenceRating) {
        this.confidenceRating = confidenceRating;
    }

    public Double getProblemSolvingRating() {
        return problemSolvingRating;
    }

    public void setProblemSolvingRating(Double problemSolvingRating) {
        this.problemSolvingRating = problemSolvingRating;
    }

    public List<String> getQuestionsAsked() {
        return questionsAsked;
    }

    public void setQuestionsAsked(List<String> questionsAsked) {
        this.questionsAsked = questionsAsked;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }
}

class InterviewFeedbackService{
    Function<InterviewFeedback,Double> overallRating=feedback->(feedback.getTechnicalRating()+feedback.getCommunicationRating()+feedback.getCodingRating()+feedback.getConfidenceRating()+feedback.getProblemSolvingRating())/5;

    Function<Double,String> performanceStatus=rating->{
        if(rating>=8)return "Excellent";
        else if(rating>=6)return "Good";
        else if(rating>=4)return "Average";
        else return "Needs Improvement";
    };

    Predicate<InterviewFeedback> placementEligible=feedback->overallRating.apply(feedback)>=6&&feedback.getCodingRating()>=6;

    Function<InterviewFeedback,List<String>> suggestions=feedback->{

        List<String> suggestionList=new ArrayList<>();

        if(feedback.getTechnicalRating()<6)suggestionList.add("Improve Technical Skills");
        if(feedback.getCommunicationRating()<6)suggestionList.add("Improve Communication");
        if(feedback.getCodingRating()<6)suggestionList.add("Improve Coding");
        if(feedback.getConfidenceRating()<6)suggestionList.add("Improve Confidence");
        if(feedback.getProblemSolvingRating()<6)suggestionList.add("Improve System Design Basics");

        return suggestionList;

    };
}
class Test {
    public static void main(String[] args) {
        List<InterviewFeedback> students=List.of(
                new InterviewFeedback(101,"Ravi",8.0,7.0,8.0,8.0,8.0,
                        List.of("Java"),List.of("Coding"),List.of("Communication")),
                new InterviewFeedback(102,"Kiran",5.0,4.0,5.0,4.0,4.0,
                        List.of("Spring"),List.of("Coding"),List.of("Confidence")),
                new InterviewFeedback(103,"Sai",9.0,9.0,9.0,9.0,9.0,
                        List.of("Streams"),List.of("Good"),List.of())
        );
        InterviewFeedbackService service=new InterviewFeedbackService();

        students.stream()
                .sorted(Comparator.comparing(service.overallRating).reversed())
                .forEach(student->{

                    Double overall=service.overallRating.apply(student);

                    System.out.println("\nStudent : "+student.getStudentName());
                    System.out.printf("Overall Rating : %.2f\n",overall);
                    System.out.println("Performance : "+service.performanceStatus.apply(overall));
                    System.out.println("Placement Eligible : "+(service.placementEligible.test(student)?"YES":"NO"));

                    List<String> suggestion=service.suggestions.apply(student);

                    System.out.println("Suggestions :");

                    if(suggestion.isEmpty())System.out.println("Ready For Placement");
                    else suggestion.forEach(System.out::println);

                });

        System.out.println("\nGrouped By Performance");

        Map<String,List<InterviewFeedback>> grouped=
                students.stream()
                        .collect(Collectors.groupingBy(student->service.performanceStatus.apply(service.overallRating.apply(student))));

        grouped.forEach((status,list)->System.out.println(status+" -> "+list.size()));

        System.out.println("\nNon Eligible Students");

        students.stream()
                .filter(service.placementEligible.negate())
                .forEach(student->System.out.println(student.getStudentName()+" -> "+service.suggestions.apply(student)));

    }
}
