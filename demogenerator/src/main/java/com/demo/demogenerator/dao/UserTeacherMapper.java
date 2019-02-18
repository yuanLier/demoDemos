package com.demo.demogenerator.dao;

import com.demo.demogenerator.model.UserTeacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserTeacherMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserTeacher record);

    int insertSelective(UserTeacher record);

    UserTeacher selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserTeacher record);

    int updateByPrimaryKey(UserTeacher record);
}