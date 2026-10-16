package com.lab.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lab.entity.Salgrade;

import java.util.List;

public interface SalgradeMapper extends BaseMapper<Salgrade> {
    List<Salgrade> selectAllXml();
    Salgrade selectByIdXml(Integer grade);
    int insertXml(Salgrade salgrade);
    int updateXml(Salgrade salgrade);
    int deleteByIdXml(Integer grade);
}