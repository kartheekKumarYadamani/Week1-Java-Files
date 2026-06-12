package WEEK1.task4;

public class Customer {
    private Integer customerId;
    private String customerName;
    private Integer age;
    private Double monthlySalary;
    private Integer creditScore;
    private Double existingEmi;
    private String employmentType;
    private Double requestedLoanAmount;

    public Customer(Integer customerId,String customerName,Integer age,Double monthlySalary,
                    Integer creditScore,Double existingEmi,String employmentType,Double requestedLoanAmount){
        this.customerId=customerId;
        this.customerName=customerName;
        this.age=age;
        this.monthlySalary=monthlySalary;
        this.creditScore=creditScore;
        this.existingEmi=existingEmi;
        this.employmentType=employmentType;
        this.requestedLoanAmount=requestedLoanAmount;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Integer getAge() {
        return age;
    }

    public Double getMonthlySalary() {
        return monthlySalary;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public Double getExistingEmi() {
        return existingEmi;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public Double getRequestedLoanAmount() {
        return requestedLoanAmount;
    }
}