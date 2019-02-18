package com.demo.demogenerator.dao;

import com.demo.demogenerator.model.UserStudent;
import com.demo.demogenerator.model.UserStudentExample;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserStudentMapperTest {

    @Resource
    UserStudentMapper userStudentMapper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void countByExample() {

    }

    @Test
    public void deleteByExample() {
    }

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByExample() {

        UserStudentExample example = new UserStudentExample();

        // 相当于 select * from user_student where student_name = 'aaa' and student_major = '信管';
        example.createCriteria().andStudentNameEqualTo("aaa").andStudentMajorEqualTo("信管");
        List<UserStudent> studentList = userStudentMapper.selectByExample(example);

        Assert.assertEquals(2, studentList.size());
        Assert.assertEquals("03011702", studentList.get(1).getStudentClass());
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByExampleSelective() {
    }

    @Test
    public void updateByExample() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}