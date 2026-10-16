package com.lab.entity;

import java.math.BigDecimal;
import java.util.Date;

public class EmpSalgradeVO {
    private Integer empId;
    private String empName;
    private String gender;
    private String dept;
    private String post;
    private BigDecimal salary;
    private Date hireDate;
    private Integer status;
    private Integer grade;
    private BigDecimal losal;
    private BigDecimal hisal;

    public Integer getEmpId() { return empId; }
    public void setEmpId(Integer empId) { this.empId = empId; }
    public String getEmpName() { return empName; }
    public void setEmpName(String empName) { this.empName = empName; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getDept() { return dept; }
    public void setDept(String dept) { this.dept = dept; }
    public String getPost() { return post; }
    public void setPost(String post) { this.post = post; }
    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }
    public Date getHireDate() { return hireDate; }
    public void setHireDate(Date hireDate) { this.hireDate = hireDate; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public Integer getGrade() { return grade; }
    public void setGrade(Integer grade) { this.grade = grade; }
    public BigDecimal getLosal() { return losal; }
    public void setLosal(BigDecimal losal) { this.losal = losal; }
    public BigDecimal getHisal() { return hisal; }
    public void setHisal(BigDecimal hisal) { this.hisal = hisal; }

    @Override
    public String toString() {
        return "EmpSalgradeVO{empId=" + empId + ", empName='" + empName + "', gender='" + gender
                + "', dept='" + dept + "', post='" + post + "', salary=" + salary
                + ", hireDate=" + hireDate + ", status=" + status
                + ", grade=" + grade + ", losal=" + losal + ", hisal=" + hisal + "}";
    }
}