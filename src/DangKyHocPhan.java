import java.util.Scanner;

class Student {
    private final String fullName;
    private final String studentID;

    public Student(String fullName, String studentID) {
        this.fullName = fullName;
        this.studentID = studentID;
    }

    public void displayInfo() {
        System.out.println("Full name: " + fullName + ".");
        System.out.println("Student ID: " + studentID + ".");
    }
}

class Class {
    private final String classCode;
    private final String className;
    private final int credit;
    private final String startTime;
    private final String endTime;
    private final String dayOfWeek;
    private final String startDate;
    private final String endDate;

    public Class(String classCode, String className, int credit, String startTime, String endTime, String dayOfWeek, String startDate, String endDate) {
        this.classCode = classCode;
        this.className = className;
        this.credit = credit;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void displayInfo() {
        System.out.println("Class Code: " + classCode + ".");
        System.out.println("Class Name: " + className + ".");
        System.out.println("Credit: " + credit + ".");
        System.out.println("Start Time: " + startTime + ".");
        System.out.println("End Time: " + endTime + ".");
        System.out.println("Day of Week: " + dayOfWeek + ".");
        System.out.println("Start Date: " + startDate + ".");
        System.out.println("End Date: " + endDate + ".");
    }
}

public class DangKyHocPhan {
    public static void main(String[] args) {
        System.out.println("Course registration - To Vinh Tien - 22521474.");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student information:");
        String fullName = "";
        while (fullName.trim().isEmpty()) {
            System.out.print("Full name: ");
            fullName = scanner.nextLine().trim();
            if (fullName.trim().isEmpty()) {
                System.out.println("Full name cannot be empty. Please enter again.");
            }
        }
        String studentID = "";
        while (studentID.trim().isEmpty()) {
            System.out.print("Student ID: ");
            studentID = scanner.nextLine().trim();
            if (studentID.trim().isEmpty()) {
                System.out.println("Student ID cannot be empty. Please enter again.");
            }
        }

        Student student = new Student(fullName, studentID);

        System.out.println("\nEnter class information:");
        String classCode = "";
        while (classCode.trim().isEmpty()) {
            System.out.print("Class code: ");
            classCode = scanner.nextLine().trim();
            if (classCode.trim().isEmpty()) {
                System.out.println("Class code cannot be empty. Please enter again.");
            }
        }
        String className = "";
        while (className.trim().isEmpty()) {
            System.out.print("Class name: ");
            className = scanner.nextLine().trim();
            if (className.trim().isEmpty()) {
                System.out.println("Class name cannot be empty. Please enter again.");
            }
        }

        int credit = -1;
        while (credit < 0) {
            System.out.print("Credit: ");
            credit = scanner.nextInt();
            scanner.nextLine();

            if (credit < 0) {
                System.out.println("Credit cannot be negative. Please enter again.");
            }
        }
        System.out.println("Enter start time:");
        String startTime = getTime();
        System.out.println("Enter end time:");
        String endTime;
        do {
            endTime = getTime();
            if (isEndTimeValid(startTime, endTime)) {
                System.out.println("End time must be after start time. Please enter again.");
                System.out.println("Enter end time:");
            }
        } while (isEndTimeValid(startTime, endTime));
        String dayOfWeek = getDayOfWeek();
        System.out.println("Enter start date: ");
        String startDate = getDate();
        String endDate = "";
        while (endDate.trim().isEmpty()) {
            System.out.println("Enter end date: ");
            endDate = getDate();
            if (!isDateValid(startDate, endDate)) {
                    System.out.println("End date must be later than start date. Please enter again.");
                    endDate = "";
            }
        }
        Class classInfo = new Class(classCode, className, credit, startTime, endTime, dayOfWeek, startDate, endDate);

        System.out.println("\nStudent Information:");
        student.displayInfo();
        System.out.println("\nClass Information:");
        classInfo.displayInfo();

        scanner.close();
    }

    private static String getTime() {
        Scanner scanner = new Scanner(System.in);
        String time = "";
        boolean isValid = false;

        while (!isValid) {
            int hour = -1;
            while (hour < 0 || hour > 23) {
                System.out.print("Hour (0-23): ");
                hour = scanner.nextInt();
                if (hour < 0 || hour > 23) {
                    System.out.println("Invalid hour! Please enter again.");
                }
            }

            int minute = -1;
            while (minute < 0 || minute > 59) {
                System.out.print("Minute (0-59): ");
                minute = scanner.nextInt();
                if (minute < 0 || minute > 59) {
                    System.out.println("Invalid minute! Please enter again.");
                }
            }

            int second = -1;
            while (second < 0 || second > 59) {
                System.out.print("Second (0-59): ");
                second = scanner.nextInt();
                if (second < 0 || second > 59) {
                    System.out.println("Invalid second! Please enter again.");
                }
            }

            time = String.format("%02d:%02d:%02d", hour, minute, second);
            isValid = true;
        }
        scanner.nextLine();
        return time;
    }

    private static String getDayOfWeek() {
        Scanner scanner = new Scanner(System.in);
        String dayOfWeek;

        while (true) {
            System.out.print("Day of Week (Mon, Tue, ...): ");
            dayOfWeek = scanner.nextLine().trim();
            if (dayOfWeek.matches("Mon|Tue|Wed|Thu|Fri|Sat|Sun|Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday")) {
                break;
            } else {
                System.out.println("Invalid input! Please enter day of week again.");
            }
        }

        if (dayOfWeek.length() == 3) {
            switch (dayOfWeek) {
                case "Mon":
                    dayOfWeek = "Monday";
                    break;
                case "Tue":
                    dayOfWeek = "Tuesday";
                    break;
                case "Wed":
                    dayOfWeek = "Wednesday";
                    break;
                case "Thu":
                    dayOfWeek = "Thursday";
                    break;
                case "Fri":
                    dayOfWeek = "Friday";
                    break;
                case "Sat":
                    dayOfWeek = "Saturday";
                    break;
                case "Sun":
                    dayOfWeek = "Sunday";
                    break;
                default:
                    break;
            }
        }
        return dayOfWeek;
    }

    private static String getDate() {
        Scanner scanner = new Scanner(System.in);
        String date = "";
        boolean isValid = false;

        while (!isValid) {
            int day;
            while (true) {
                System.out.print("Enter the day: ");
                day = scanner.nextInt();
                if (day < 1 || day > 31) {
                    System.out.println("Invalid day! Please enter again.");
                } else {
                    break;
                }
            }

            int month;
            while (true) {
                System.out.print("Enter the month: ");
                month = scanner.nextInt();
                if (month < 1 || month > 12) {
                    System.out.println("Invalid month! Please enter again.");
                } else {
                    break;
                }
            }

            int year;
            while (true) {
                System.out.print("Enter the year: ");
                year = scanner.nextInt();
                if (year < 0 || year > 9999) {
                    System.out.println("Invalid year! Please enter again.");
                } else {
                    break;
                }
            }

            if (isValidDate(day, month, year)) {
                isValid = true;
                date = String.format("%02d/%02d/%04d", day, month, year);
            } else {
                System.out.println("Invalid date! Please enter again.");
            }
        }
        return date;
    }

    private static boolean isValidDate(int day, int month, int year) {
        if (month == 2) {
            if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                return day >= 1 && day <= 29;
            } else {
                return day >= 1 && day <= 28;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return day >= 1 && day <= 30;
        } else {
            return day >= 1 && day <= 31;
        }
    }
    private static boolean isDateValid(String startDate, String endDate) {
        String[] startParts = startDate.split("/");
        String[] endParts = endDate.split("/");
        int startDay = Integer.parseInt(startParts[0]);
        int startMonth = Integer.parseInt(startParts[1]);
        int startYear = Integer.parseInt(startParts[2]);
        int endDay = Integer.parseInt(endParts[0]);
        int endMonth = Integer.parseInt(endParts[1]);
        int endYear = Integer.parseInt(endParts[2]);

        if (endYear > startYear) {
            return true;
        } else if (endYear == startYear && endMonth > startMonth) {
            return true;
        } else return endYear == startYear && endMonth == startMonth && endDay > startDay;
    }

    private static boolean isEndTimeValid(String startTime, String endTime) {
        long startTimeMillis = convertToMillis(startTime);
        long endTimeMillis = convertToMillis(endTime);

        return endTimeMillis <= startTimeMillis;
    }

    private static long convertToMillis(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        int second = Integer.parseInt(parts[2]);

        return (hour * 3600L + minute * 60L + second) * 1000;
    }
}