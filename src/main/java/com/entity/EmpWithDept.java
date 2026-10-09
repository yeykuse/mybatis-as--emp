package com.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 员工+部门联合查询结果DTO
 * 
 * 用于接收 emp LEFT JOIN dept 的查询结果，
 * 包含 emp 表的所有字段 + dept 表的 dname 和 loc。
 * 配合 <resultMap> 中的 <association> 标签使用。
 */
public class EmpWithDept {
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Date hiredate;
    private BigDecimal sal;
    private BigDecimal comm;
    private Integer deptno;
    
    // dept 表的字段（通过 JOIN 查询获得）
    private String dname;
    private String loc;

    public Integer getEmpno() { return empno; }
    public void setEmpno(Integer empno) { this.empno = empno; }

    public String getEname() { return ename; }
    public void setEname(String ename) { this.ename = ename; }

    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }

    public Integer getMgr() { return mgr; }
    public void setMgr(Integer mgr) { this.mgr = mgr; }

    public Date getHiredate() { return hiredate; }
    public void setHiredate(Date hiredate) { this.hiredate = hiredate; }

    public BigDecimal getSal() { return sal; }
    public void setSal(BigDecimal sal) { this.sal = sal; }

    public BigDecimal getComm() { return comm; }
    public void setComm(BigDecimal comm) { this.comm = comm; }

    public Integer getDeptno() { return deptno; }
    public void setDeptno(Integer deptno) { this.deptno = deptno; }

    public String getDname() { return dname; }
    public void setDname(String dname) { this.dname = dname; }

    public String getLoc() { return loc; }
    public void setLoc(String loc) { this.loc = loc; }

    @Override
    public String toString() {
        return "EmpWithDept{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}