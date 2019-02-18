package com.demo.demogenerator.model;

public class UserStudent {
    private Long id;

    private String studentNumber;

    private String studentName;

    private String studentPassword;

    private String studentMajor;

    private String studentClass;

    private String studentEmail;

    private String studentPhone;

    private Byte studentCountEnable;

    private Long studentCurrentGame;

    private Long studentTeacherId;

    public UserStudent(Long id, String studentNumber, String studentName, String studentPassword, String studentMajor, String studentClass, String studentEmail, String studentPhone, Byte studentCountEnable, Long studentCurrentGame, Long studentTeacherId) {
        this.id = id;
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.studentPassword = studentPassword;
        this.studentMajor = studentMajor;
        this.studentClass = studentClass;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.studentCountEnable = studentCountEnable;
        this.studentCurrentGame = studentCurrentGame;
        this.studentTeacherId = studentTeacherId;
    }

    public UserStudent() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber == null ? null : studentNumber.trim();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword == null ? null : studentPassword.trim();
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor == null ? null : studentMajor.trim();
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass == null ? null : studentClass.trim();
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail == null ? null : studentEmail.trim();
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone == null ? null : studentPhone.trim();
    }

    public Byte getStudentCountEnable() {
        return studentCountEnable;
    }

    public void setStudentCountEnable(Byte studentCountEnable) {
        this.studentCountEnable = studentCountEnable;
    }

    public Long getStudentCurrentGame() {
        return studentCurrentGame;
    }

    public void setStudentCurrentGame(Long studentCurrentGame) {
        this.studentCurrentGame = studentCurrentGame;
    }

    public Long getStudentTeacherId() {
        return studentTeacherId;
    }

    public void setStudentTeacherId(Long studentTeacherId) {
        this.studentTeacherId = studentTeacherId;
    }
}