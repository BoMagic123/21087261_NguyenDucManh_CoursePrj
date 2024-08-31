package edu.iuh.fit;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class CourseList {
    private List<Course> courses;

    public CourseList() {
        this.courses = new ArrayList<>();
    }

    public boolean addCourse(Course course) {
        if (exists(course)) {
            System.out.println("Khóa học với mã " + course.getId() + " đã tồn tại.");
            return false;
        }
        courses.add(course);
        return true;
    }

    public boolean exists(Course course) {
        return courses.stream().anyMatch(c -> c.getId().equals(course.getId()));
    }


    public boolean removeCourse(String id) {
        Course course = searchCourseById(id);
        if (course == null) {
            System.out.println("Khóa học với mã " + id + " không tồn tại.");
            return false;
        }
        courses.remove(course);
        return true;
    }

    public Course searchCourseById(String id) {
        return courses.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Course> searchCourse(String title) {
        List<Course> foundCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getTitle().toLowerCase().contains(title.toLowerCase())) {
                foundCourses.add(course);
            }
        }
        return foundCourses;
    }

    public List<Course> searchCourseByDepartment(String department) {
        List<Course> foundCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getDepartment().equalsIgnoreCase(department)) {
                foundCourses.add(course);
            }
        }
        return foundCourses;
    }

    public List<Course> sortCourses() {
        List<Course> sortedCourses = new ArrayList<>(courses);
        sortedCourses.sort((c1, c2) -> c1.getTitle().compareToIgnoreCase(c2.getTitle()));
        return sortedCourses;
    }

    public List<Course> findMaxCreditCourses() {
        int maxCredit = courses.stream().mapToInt(Course::getCredit).max().orElse(0);
        List<Course> maxCreditCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getCredit() == maxCredit) {
                maxCreditCourses.add(course);
            }
        }
        return maxCreditCourses;
    }

    public String findDepartmentWithMostCourses() {
        return courses.stream()
                .collect(Collectors.groupingBy(Course::getDepartment, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public List<Course> getCourses() {
        return courses;
    }
}
