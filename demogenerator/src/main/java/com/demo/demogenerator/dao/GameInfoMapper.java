package com.demo.demogenerator.dao;

import com.demo.demogenerator.model.GameInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GameInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GameInfo record);

    int insertSelective(GameInfo record);

    GameInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GameInfo record);

    int updateByPrimaryKey(GameInfo record);
}