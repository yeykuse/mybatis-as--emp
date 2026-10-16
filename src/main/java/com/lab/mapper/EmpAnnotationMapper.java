package com.lab.mapper;

import com.lab.entity.Emp;
import com.lab.entity.EmpSalgradeVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EmpAnnotationMapper {

    @Select("SELECT emp_id, emp_name, gender, dept, post, salary, hire_date, status FROM emp")
    @Results(id = "empResultMap", value = {
            @Result(property = "empId", column = "emp_id", id = true),
            @Result(property = "empName", column = "emp_name"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "dept", column = "dept"),
            @Result(property = "post", column = "post"),
            @Result(property = "salary", column = "salary"),
            @Result(property = "hireDate", column = "hire_date"),
            @Result(property = "status", column = "status")
    })
    List<Emp> selectAll();

    @Select("SELECT emp_id, emp_name, gender, dept, post, salary, hire_date, status FROM emp WHERE emp_id = #{id}")
    @ResultMap("empResultMap")
    Emp selectById(@Param("id") Integer empId);

    @Insert("INSERT INTO emp (emp_name, gender, dept, post, salary, hire_date, status) " +
            "VALUES (#{empName}, #{gender}, #{dept}, #{post}, #{salary}, #{hireDate}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "empId")
    int insert(Emp emp);

    @Update("UPDATE emp SET emp_name=#{empName}, gender=#{gender}, dept=#{dept}, " +
            "post=#{post}, salary=#{salary}, status=#{status} WHERE emp_id=#{empId}")
    int update(Emp emp);

    @Delete("DELETE FROM emp WHERE emp_id = #{id}")
    int deleteById(@Param("id") Integer empId);

    @Select("SELECT e.emp_id, e.emp_name, e.gender, e.dept, e.post, e.salary, " +
            "e.hire_date, e.status, s.grade, s.losal, s.hisal " +
            "FROM emp e INNER JOIN salgrade s ON e.salary BETWEEN s.losal AND s.hisal " +
            "WHERE e.status = 1 ORDER BY s.grade, e.salary DESC")
    @Results(id = "empSalgradeResultMap", value = {
            @Result(property = "empId", column = "emp_id", id = true),
            @Result(property = "empName", column = "emp_name"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "dept", column = "dept"),
            @Result(property = "post", column = "post"),
            @Result(property = "salary", column = "salary"),
            @Result(property = "hireDate", column = "hire_date"),
            @Result(property = "status", column = "status"),
            @Result(property = "grade", column = "grade"),
            @Result(property = "losal", column = "losal"),
            @Result(property = "hisal", column = "hisal")
    })
    List<EmpSalgradeVO> selectEmpWithSalgrade();
}