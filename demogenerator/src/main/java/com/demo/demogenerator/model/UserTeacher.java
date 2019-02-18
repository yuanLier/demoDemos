package com.demo.demogenerator.model;

public class UserTeacher {
    private Long id;

    private String teacherNumber;

    private String teacherName;

    private String teacherPassword;

    private String teacherEmail;

    private String teacherPhone;

    private String teacherPosition;

    public UserTeacher(Long id, String teacherNumber, String teacherName, String teacherPassword, String teacherEmail, String teacherPhone, String teacherPosition) {
        this.id = id;
        this.teacherNumber = teacherNumber;
        this.teacherName = teacherName;
        this.teacherPassword = teacherPassword;
        this.teacherEmail = teacherEmail;
        this.teacherPhone = teacherPhone;
        this.teacherPosition = teacherPosition;
    }

    public UserTeacher() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber == null ? null : teacherNumber.trim();
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword == null ? null : teacherPassword.trim();
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail == null ? null : teacherEmail.trim();
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone == null ? null : teacherPhone.trim();
    }

    public String getTeacherPosition() {
        return teacherPosition;
    }

    public void setTeacherPosition(String teacherPosition) {
        this.teacherPosition = teacherPosition == null ? null : teacherPosition.trim();
    }
}