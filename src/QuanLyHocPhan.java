import java.util.*;

class Learner {
    private final String studentID;
    private final Set<String> registeredCourses;

    public Learner(String studentID) {
        this.studentID = studentID;
        this.registeredCourses = new HashSet<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public Set<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(String courseCode) {
        registeredCourses.add(courseCode);
    }

    public void unregisterCourse(String courseCode) {
        registeredCourses.remove(courseCode);
    }
}

class Course {
    private final String courseCode;
    private final int credit;
    private final String courseName;
    private final Map<String, String> schedule; // Map course code to day of week
    private final Set<String> enrolledStudents;

    public Course(String courseCode, int credit, String courseName) {
        this.courseCode = courseCode;
        this.credit = credit;
        this.courseName = courseName;
        this.schedule = new HashMap<>();
        this.enrolledStudents = new HashSet<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getCredit() {
        return credit;
    }

    public String getCourseName() {
        return courseName;
    }

    public Map<String, String> getSchedule() {
        return schedule;
    }

    public Set<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setSchedule(String dayOfWeek) {
        schedule.put(courseCode, dayOfWeek);
    }

    public void enrollStudent(String studentID) {
        enrolledStudents.add(studentID);
    }

    public void unrollStudent(String studentID) {
        enrolledStudents.remove(studentID);
    }
}

public class QuanLyHocPhan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the number of courses: ");
        int numCourses = scanner.nextInt();
        scanner.nextLine();

        Map<String, Learner> studentMap = new HashMap<>();
        Map<String, Course> courseMap = new HashMap<>();

        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter student ID for student " + (i + 1) + ": ");
            String studentID = scanner.nextLine();
            studentMap.put(studentID, new Learner(studentID));
        }

        // Input courses
        for (int i = 0; i < numCourses; i++) {
            System.out.print("Enter course code for course " + (i + 1) + ": ");
            String courseCode = scanner.nextLine();
            System.out.print("Enter course name for course " + (i + 1) + ": ");
            String courseName = scanner.nextLine();
            int credit;
            while (true) {
                System.out.print("Enter number of credits for course " + (i + 1) + ": ");
                credit = scanner.nextInt();
                scanner.nextLine();
                if (credit >= 1 && credit <= 6) {
                    break;
                } else {
                    System.out.println("Invalid number of credits. Please enter again (1-6).");
                }
            }
            System.out.print("Enter day of week for course " + (i + 1) + " (Mon, Tue, ...): ");
            String dayOfWeek = scanner.nextLine();
            courseMap.put(courseCode, new Course(courseCode, credit, courseName));
            courseMap.get(courseCode).setSchedule(dayOfWeek);
        }

        // Course registration
        for (String courseCode : courseMap.keySet()) {
            System.out.println("Enter student IDs registered for course " + courseCode + " (Enter 'exit' to stop):");
            for (int i = 0; i < numStudents; i++) {
                System.out.print("Student ID " + (i + 1) + ": ");
                String studentID = scanner.nextLine();
                if (studentID.equalsIgnoreCase("exit")) {
                    break;
                }
                if (studentMap.containsKey(studentID)) {
                    Learner student = studentMap.get(studentID);
                    if (!student.getRegisteredCourses().contains(courseCode)) {
                        if (calculateTotalCredits(student, courseMap.get(courseCode)) <= 24) {
                            student.registerCourse(courseCode);
                            courseMap.get(courseCode).enrollStudent(studentID);
                        } else {
                            System.out.println("Student " + studentID + " has reached the maximum number of credits.");
                            System.out.println("Skipping registration for course " + courseCode + " for this student.");
                        }
                    } else {
                        System.out.println("Student " + studentID + " has already registered for course " + courseCode + ".");
                    }
                } else {
                    System.out.println("Student with ID " + studentID + " does not exist.");
                }
            }
        }

        // Find the course with the most students
        String maxEnrolledCourse = "";
        int maxEnrolledCount = 0;
        for (Map.Entry<String, Course> entry : courseMap.entrySet()) {
            int enrolledCount = entry.getValue().getEnrolledStudents().size();
            if (enrolledCount > maxEnrolledCount) {
                maxEnrolledCount = enrolledCount;
                maxEnrolledCourse = entry.getKey();
            }
        }
        System.out.println("Course with the most students: " + maxEnrolledCourse + " (" + maxEnrolledCount + " students)");

        String maxRegisteredStudent = "";
        int maxRegisteredCount = 0;
        for (Map.Entry<String, Learner> entry : studentMap.entrySet()) {
            int registeredCount = entry.getValue().getRegisteredCourses().size();
            if (registeredCount > maxRegisteredCount) {
                maxRegisteredCount = registeredCount;
                maxRegisteredStudent = entry.getKey();
            }
        }
        System.out.println("Student with the most registered courses: " + maxRegisteredStudent + " (" + maxRegisteredCount + " courses)");

        // Find the student with the most total credits
        String maxCreditStudent = "";
        int maxCreditCount = 0;
        for (Map.Entry<String, Learner> entry : studentMap.entrySet()) {
            int creditCount = 0;
            for (String courseCode : entry.getValue().getRegisteredCourses()) {
                creditCount += courseMap.get(courseCode).getCredit();
            }
            if (creditCount > maxCreditCount) {
                maxCreditCount = creditCount;
                maxCreditStudent = entry.getKey();
            }
        }
        System.out.println("Student with the most total credits: " + maxCreditStudent + " (" + maxCreditCount + " credits)");

        // Input student ID to get schedule
        System.out.print("Enter student ID to get schedule: ");
        String studentID = scanner.nextLine();
        if (studentMap.containsKey(studentID)) {
            System.out.println("Schedule for student " + studentID + ":");
            for (String courseCode : studentMap.get(studentID).getRegisteredCourses()) {
                Course course = courseMap.get(courseCode);
                System.out.println("Course " + course.getCourseCode() + " - " + course.getCourseName() + " - " + course.getCredit() + " credits");
            }
        } else {
            System.out.println("Student with ID " + studentID + " does not exist.");
        }

        scanner.close();
    }

    private static int calculateTotalCredits(Learner student, Course course) {
        int totalCredits = 0;
        for (String courseCode : student.getRegisteredCourses()) {
            totalCredits += course.getCredit();
        }
        return totalCredits + course.getCredit();
    }
}