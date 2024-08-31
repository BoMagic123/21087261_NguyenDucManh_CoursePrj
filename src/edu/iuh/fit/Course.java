package edu.iuh.fit;

/**
 * @description
 * @author: Nguyen Duc Manh
 * date: Aug 29, 2024
 * @version 1.0
 * @created
 */


class Course {
    private String id;
    private String title;
    private int credit;
    private String department;

    // Constructor
    public Course(String id, String title, int credit, String department) {
        this.id = id;
        this.title = title;
        this.credit = credit;
        this.department = department;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCredit() {
        return credit;
    }

    public String getDepartment() {
        return department;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Course ID: " + id + ", Title: " + title + ", Credit: " + credit + ", Department: " + department;
    }
}

