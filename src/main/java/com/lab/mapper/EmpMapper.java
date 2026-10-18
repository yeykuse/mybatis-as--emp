package com.lab.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lab.entity.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper extends BaseMapper<Emp> {
    List<Emp> selectAll();
    Emp selectById(Integer empId);
    int insert(Emp emp);
    int update(Emp emp);
    int deleteById(Integer empId);

    List<Emp> selectByCondition(Emp emp);

    int updateDynamic(Emp emp);

    int deleteBatch(@Param("ids") List<Integer> ids);

    int insertBatch(@Param("list") List<Emp> emps);

    List<Emp> selectByChoose(Emp emp);
}