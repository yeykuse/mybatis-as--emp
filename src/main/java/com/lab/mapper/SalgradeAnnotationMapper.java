package com.lab.mapper;

import com.lab.entity.Salgrade;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SalgradeAnnotationMapper {

    @Select("SELECT grade, losal, hisal FROM salgrade")
    @Results(id = "salgradeResultMap", value = {
            @Result(property = "grade", column = "grade", id = true),
            @Result(property = "losal", column = "losal"),
            @Result(property = "hisal", column = "hisal")
    })
    List<Salgrade> selectAll();

    @Select("SELECT grade, losal, hisal FROM salgrade WHERE grade = #{grade}")
    @ResultMap("salgradeResultMap")
    Salgrade selectById(@Param("grade") Integer grade);

    @Insert("INSERT INTO salgrade (losal, hisal) VALUES (#{losal}, #{hisal})")
    @Options(useGeneratedKeys = true, keyProperty = "grade")
    int insert(Salgrade salgrade);

    @Update("UPDATE salgrade SET losal = #{losal}, hisal = #{hisal} WHERE grade = #{grade}")
    int update(Salgrade salgrade);

    @Delete("DELETE FROM salgrade WHERE grade = #{grade}")
    int deleteById(@Param("grade") Integer grade);
}