package com.chapter01;

import com.entity.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * XML方式 - 模糊查询
 */
public interface EmpFindByEnameXmlMapper {

    List<Emp> findByEnameLikeXml(@Param("value") String ename);
}