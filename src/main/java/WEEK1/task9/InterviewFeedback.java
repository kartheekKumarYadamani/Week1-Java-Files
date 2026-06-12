package WEEK1.task9;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

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
  Function<InterviewFeedback,Double>  overallrating=interviewfeedback->(interviewfeedback.getCodingRating()+interviewfeedback.getCommunicationRating()+interviewfeedback.getConfidenceRating()+
          interviewfeedback.getProblemSolvingRating())/5;
  Predicate<InterviewFeedback> isPlacementEligible=placementeligible->
          placementeligible.getCodingRating()>=6 && overallrating.apply(placementeligible)>=6;
  Function<Double,String> performanceStatus=performance->{
          if(performance>=8)return "Excellent";
          else if(performance>=6)return "Good";
          else if(performance>=4)return "Average";
          else return "Needs Improvement";
  };

}
class Test {
    public static void main(String[] args) {
        InterviewFeedback interviewFeedback = new InterviewFeedback(101, "Sai", 8.6, 8.0, 8.6, 9.0, 9.7,
                List.of("What is java", "OOPS", "Spring"), List.of("Consistent", "Good communication", "Nice coding"), List.of("BoilerplateCodeUsing", "NoStandards"));
        InterviewFeedbackService interviewFeedbackService = new InterviewFeedbackService();
        double overallrating = interviewFeedbackService.overallrating.apply(interviewFeedback);
        String performance = interviewFeedbackService.performanceStatus.apply(overallrating);
        Map<String,List<String>> suggestions=new HashMap<>();
        suggestions.put("Excellent",List.of("Improve Communication","Improve real world exposure"));
        suggestions.put("Good",List.of("Improve Coding","Improve System Design"));
        suggestions.put("Average",List.of("Improve Communication","Improve real world exposure","Improve Coding"));
        suggestions.put("Needs Improvement",List.of("Improve Communication","Improve Coding","Practice More"));
        Consumer< Map<String,List<String>>> finalResult=s->{s.get(performance).forEach(st->System.out.print(st+","));
        };

        Consumer<InterviewFeedback> finalresult = result -> {
            System.out.println("Student : " + result.getStudentName());
            System.out.printf("Overall Rating : %.2f\n" ,overallrating);
            System.out.println("Performance : "+performance);
            if (interviewFeedbackService.isPlacementEligible.test(interviewFeedback)) {
                System.out.println("Placement Eligible : YES");
            } else System.out.println("Placement Eligible : NO");
            System.out.print("Suggestions : ");
            finalResult.accept(suggestions);
        };

        finalresult.accept(interviewFeedback);
    }
}
