package WEEK1.task5;

import java.util.List;

public class Student {

    private Integer studentId;
    private String studentName;
    private String courseName;
    private Integer graduationYear;
    private Double percentage;
    private Integer backlogs;
    private Double mockRating;
    private List<String> skills;
    private Boolean resumeAvailable;
    private Boolean feePaid;

    public Student(Integer studentId,String studentName,String courseName,Integer graduationYear, Double percentage,Integer backlogs,
                   Double mockRating,List<String> skills,Boolean resumeAvailable,Boolean feePaid){
        this.studentId=studentId;
        this.studentName=studentName;
        this.courseName=courseName;
        this.graduationYear=graduationYear;
        this.percentage=percentage;
        this.backlogs=backlogs;
        this.mockRating=mockRating;
        this.skills=skills;
        this.resumeAvailable=resumeAvailable;
        this.feePaid=feePaid;
    }

    public Integer getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public String getCourseName() { return courseName; }
    public Integer getGraduationYear() { return graduationYear; }
    public Double getPercentage() { return percentage; }
    public Integer getBacklogs() { return backlogs; }
    public Double getMockRating() { return mockRating; }
    public List<String> getSkills() { return skills; }
    public Boolean getResumeAvailable() { return resumeAvailable; }
    public Boolean getFeePaid() { return feePaid; }
}