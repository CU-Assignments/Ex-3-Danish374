import java.util.ArrayList;
import java.util.List;

class Student {
    private String name; 
    private List<String> completedCourses; 

    
    public Student(String name) {
        this.name = name;
        this.completedCourses = new ArrayList<>(); 
    }

    
    public String getName() {
        return name;
    }

   
    public List<String> getCompletedCourses() {
        return completedCourses;
    }

    
    public void completeCourse(String course) {
        completedCourses.add(course); 
    }
}


class Course {
    private String courseName; 
    private int maxEnrollment; 
    private List<Student> enrolledStudents; 
    private List<String> prerequisites; 

   
    public Course(String courseName, int maxEnrollment, List<String> prerequisites) {
        this.courseName = courseName;
        this.maxEnrollment = maxEnrollment; 
        this.enrolledStudents = new ArrayList<>(); 
        this.prerequisites = prerequisites; 
    }

    
    public String getCourseName() {
        return courseName;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }


    public int getMaxEnrollment() {
        return maxEnrollment;
    }


    public void enrollStudent(Student student) throws CourseFullException, PrerequisiteNotMetException {
        if (enrolledStudents.size() >= maxEnrollment) {
            throw new CourseFullException("Course " + courseName + " is full."); 
        }

    
        for (String prerequisite : prerequisites) {
            if (!student.getCompletedCourses().contains(prerequisite)) {
                throw new PrerequisiteNotMetException("Student " + student.getName() + " has not completed the prerequisite " + prerequisite + " for " + courseName); // Throw exception if prerequisite not met
            }
        }

        enrolledStudents.add(student); 
        System.out.println("Student " + student.getName() + " successfully enrolled in " + courseName); // Notify successful enrollment
    }
}


class CourseFullException extends Exception {
  
    public CourseFullException(String message) {
        super(message);
    }
}


class PrerequisiteNotMetException extends Exception {
   
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}


public class UniversityEnrollmentSystem {
    public static void main(String[] args) {
        
        List<String> prerequisitesForCS102 = new ArrayList<>();
        prerequisitesForCS102.add("CS101"); 
       
        Course cs102 = new Course("CS102", 2, prerequisitesForCS102);

       
        List<String> prerequisitesForCS101 = new ArrayList<>();
       
        Course cs101 = new Course("CS101", 3, prerequisitesForCS101);

      
        Student student1 = new Student("Danish");
        Student student2 = new Student("Hanish");
        Student student3 = new Student("Sarthak");

       
        student1.completeCourse("CS101");

        try {
            
            cs101.enrollStudent(student1); 
            cs102.enrollStudent(student1); 

            cs101.enrollStudent(student2); 
            cs102.enrollStudent(student2); 
            cs102.enrollStudent(student3); 
        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println(e.getMessage()); 
        }

        try {
           
            cs102.enrollStudent(student3);
        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println(e.getMessage()); 
        }
    }
}
