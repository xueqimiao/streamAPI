package com.xueqimiao.stream;

public class Student {

    private Integer stuId;
    private String stuName;
    private Integer gradeId;


    public Student(Integer stuId, String stuName, Integer gradeId) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.gradeId = gradeId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }
}
