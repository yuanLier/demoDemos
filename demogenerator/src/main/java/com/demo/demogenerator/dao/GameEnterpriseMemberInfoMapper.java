package com.demo.demogenerator.dao;

import com.demo.demogenerator.model.GameEnterpriseMemberInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GameEnterpriseMemberInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GameEnterpriseMemberInfo record);

    int insertSelective(GameEnterpriseMemberInfo record);

    GameEnterpriseMemberInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GameEnterpriseMemberInfo record);

    int updateByPrimaryKeyWithBLOBs(GameEnterpriseMemberInfo record);

    int updateByPrimaryKey(GameEnterpriseMemberInfo record);
}