package com.demo.demogenerator.dao;

import com.demo.demogenerator.model.UserStudent;
import com.demo.demogenerator.model.UserStudentExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserStudentMapper {
    int countByExample(UserStudentExample example);

    int deleteByExample(UserStudentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserStudent record);

    int insertSelective(UserStudent record);

    List<UserStudent> selectByExample(UserStudentExample example);

    UserStudent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserStudent record, @Param("example") UserStudentExample example);

    int updateByExample(@Param("record") UserStudent record, @Param("example") UserStudentExample example);

    int updateByPrimaryKeySelective(UserStudent record);

    int updateByPrimaryKey(UserStudent record);
}