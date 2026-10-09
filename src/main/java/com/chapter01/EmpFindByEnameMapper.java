package com.chapter01;

import com.entity.Emp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 注解方式 - 模糊查询
 */
public interface EmpFindByEnameMapper {

    @Select("SELECT * FROM emp WHERE ename LIKE CONCAT('%', #{value}, '%')")
    List<Emp> findByEnameLike(@Param("value") String ename);
}