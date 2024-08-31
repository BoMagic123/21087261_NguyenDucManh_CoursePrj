package edu.iuh.fit;

import java.util.List;
import java.util.Scanner;

public class TestCourse {
    public static void initData(CourseList courseList) {
        courseList.addCourse(new Course("CS101", "Giới thiệu lập trình", 3, "Khoa học máy tính"));
        courseList.addCourse(new Course("MAT201", "Giải tích II", 4, "Toán học"));
        courseList.addCourse(new Course("PHY101", "Vật lý I", 4, "Vật lý"));

    }

    public static void printCoursesTable(List<Course> courses) {
        System.out.printf("%-10s %-30s %-6s %-20s%n", "Mã khóa học", "Tên khóa học", "Tín chỉ", "Khoa");
        System.out.println("-------------------------------------------------------------");
        for (Course course : courses) {
            System.out.printf("%-10s %-30s %-6d %-20s%n",
                    course.getId(),
                    course.getTitle(),
                    course.getCredit(),
                    course.getDepartment());
        }
    }

    public static void main(String[] args) {
        CourseList courseList = new CourseList();
        initData(courseList);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Thêm khóa học");
            System.out.println("2. Xóa khóa học");
            System.out.println("3. Tìm kiếm khóa học theo mã");
            System.out.println("4. Tìm kiếm khóa học theo tên");
            System.out.println("5. Tìm kiếm khóa học theo khoa");
            System.out.println("6. Sắp xếp khóa học theo tên");
            System.out.println("7. Tìm khóa học có số tín chỉ lớn nhất");
            System.out.println("8. Tìm khoa có nhiều khóa học nhất");
            System.out.println("9. Hiển thị tất cả khóa học");
            System.out.println("0. Thoát");
            System.out.print("Chọn một tùy chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Nhập mã khóa học: ");
                    String id = scanner.nextLine();
                    System.out.print("Nhập tên khóa học: ");
                    String title = scanner.nextLine();
                    System.out.print("Nhập số tín chỉ: ");
                    int credit = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Nhập khoa: ");
                    String department = scanner.nextLine();
                    Course course = new Course(id, title, credit, department);
                    if (courseList.addCourse(course)) {
                        System.out.println("Thêm khóa học thành công.");
                    }
                    break;
                case 2:
                    System.out.print("Nhập mã khóa học để xóa: ");
                    String removeId = scanner.nextLine();
                    if (courseList.removeCourse(removeId)) {
                        System.out.println("Xóa khóa học thành công.");
                    }
                    break;
                case 3:
                    System.out.print("Nhập mã khóa học để tìm: ");
                    String searchId = scanner.nextLine();
                    Course foundCourse = courseList.searchCourseById(searchId);
                    if (foundCourse != null) {
                        System.out.println("Tìm thấy:");
                        printCoursesTable(List.of(foundCourse));
                    } else {
                        System.out.println("Không tìm thấy khóa học.");
                    }
                    break;
                case 4:
                    System.out.print("Nhập tên khóa học để tìm: ");
                    String searchTitle = scanner.nextLine();
                    List<Course> coursesByTitle = courseList.searchCourse(searchTitle);
                    if (!coursesByTitle.isEmpty()) {
                        System.out.println("Các khóa học tìm thấy:");
                        printCoursesTable(coursesByTitle);
                    } else {
                        System.out.println("Không có khóa học nào tìm thấy.");
                    }
                    break;
                case 5:
                    System.out.print("Nhập khoa để tìm: ");
                    String searchDepartment = scanner.nextLine();
                    List<Course> coursesByDepartment = courseList.searchCourseByDepartment(searchDepartment);
                    if (!coursesByDepartment.isEmpty()) {
                        System.out.println("Các khóa học tìm thấy:");
                        printCoursesTable(coursesByDepartment);
                    } else {
                        System.out.println("Không có khóa học nào tìm thấy.");
                    }
                    break;
                case 6:
                    System.out.println("Danh sách khóa học đã sắp xếp theo tên:");
                    List<Course> sortedCourses = courseList.sortCourses();
                    printCoursesTable(sortedCourses);
                    break;
                case 7:
                    System.out.println("Các khóa học có số tín chỉ lớn nhất:");
                    List<Course> maxCreditCourses = courseList.findMaxCreditCourses();
                    printCoursesTable(maxCreditCourses);
                    break;
                case 8:
                    String departmentWithMostCourses = courseList.findDepartmentWithMostCourses();
                    System.out.println("Khoa có nhiều khóa học nhất: " + departmentWithMostCourses);
                    break;
                case 9:
                    System.out.println("Tất cả khóa học:");
                    printCoursesTable(courseList.getCourses());
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Tùy chọn không hợp lệ. Vui lòng thử lại.");
                    break;
            }
        }
        scanner.close();
    }
}
