import java.rmi.RemoteException;
import java.util.List;

public interface Server {
    List<Student> getAllStudentData() throws RemoteException, MyException.NullDataException;
    List<Course> getAllCourseData() throws RemoteException, MyException.NullDataException;
    String getRegisterData(String studentId) throws RemoteException, MyException;
    boolean addStudent(String studentInfo) throws RemoteException, MyException.DuplicationDataException;
    boolean deleteStudent(String studentId) throws RemoteException;
    boolean addCourse(String courseInfo) throws RemoteException, MyException.DuplicationDataException;
    boolean deleteCourse(String courseId) throws RemoteException;
    boolean registerCourse(String registerInfo) throws RemoteException, MyException;
}
