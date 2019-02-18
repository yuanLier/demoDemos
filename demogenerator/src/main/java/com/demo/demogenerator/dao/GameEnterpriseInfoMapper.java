package com.demo.demogenerator.dao;

import com.demo.demogenerator.model.GameEnterpriseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GameEnterpriseInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GameEnterpriseInfo record);

    int insertSelective(GameEnterpriseInfo record);

    GameEnterpriseInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GameEnterpriseInfo record);

    int updateByPrimaryKey(GameEnterpriseInfo record);
}