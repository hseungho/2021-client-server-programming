import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DataImpl extends UnicastRemoteObject implements Data {

    public static void main(String[] args) {
        try {

            Data data = new DataImpl();
            Naming.rebind("Data", data);
            System.out.println("Data is ready!!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isStudentChanged, isCourseChanged, isRegisterChanged;

    private StudentList studentList;
    private CourseList courseList;
    private RegisterList registerList;

    public DataImpl() throws RemoteException {
        super();
        isStudentChanged = false;
        isCourseChanged = false;
        isRegisterChanged = false;

        try {
            studentList= new StudentList("resource/Students.txt");
            courseList= new CourseList("resource/Courses.txt");
            registerList= new RegisterList("resource/Register.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    if (isStudentChanged || isCourseChanged || isRegisterChanged) {
                        try {
                            saveData();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })
        );
    }

    @Override
    public List<Student> getAllStudentData() throws RemoteException, MyException.NullDataException {
        return studentList.getAllStudentRecords();
    }

    @Override
    public List<Course> getAllCourseData() throws RemoteException, MyException.NullDataException {
        return courseList.getAllCourseRecords();
    }

    @Override
    public List<Register> getRegisterData() throws RemoteException {
        return registerList.getAllRegisterRecords();
    }

    @Override
    public boolean addStudent(String studentInfo) throws RemoteException, MyException.DuplicationDataException {
        if (studentList.addStudentRecord(studentInfo)) {
            this.isStudentChanged = true;
            return true;
        }
        else return false;
    }

    @Override
    public boolean deleteStudent(String studentId) throws RemoteException {
        if (studentList.deleteStudentRecord(studentId)) {
            this.isStudentChanged = true;
            return true;
        }
        else return false;
    }

    @Override
    public boolean addCourse(String courseInfo) throws RemoteException, MyException.DuplicationDataException {
        if (courseList.addCourseRecord(courseInfo)) {
            this.isCourseChanged=true;
            return true;
        }
        else return false;
    }

    @Override
    public boolean deleteCourse(String courseId) throws RemoteException {
        if (courseList.deleteCourseRecord(courseId)) {
            this.isCourseChanged=true;
            return true;
        }
        else return false;
    }

    @Override
    public boolean registerCourse(String studentId, String courseId) throws RemoteException {
        if (registerList.addRegister(studentId, courseId)) {
            this.isRegisterChanged=true;
            return true;
        }
        else return false;
    }

    @Override
    public void saveData() throws IOException {
        if(this.isStudentChanged) {
            studentList.saveData();
            System.out.println("Save Data - SUCCESS Student Data saved !!!");
        }
        if(this.isCourseChanged) {
            courseList.saveData();
            System.out.println("Save Data - SUCCESS Course Data saved !!!");
        }
        if(this.isRegisterChanged) {
            registerList.saveData();
            System.out.println("Save Data - SUCCESS Register Data saved !!!");
        }
    }
}
