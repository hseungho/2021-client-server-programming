import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public interface Data {
    List<Student> getAllStudentData() throws RemoteException, MyException.NullDataException;
    List<Course> getAllCourseData() throws RemoteException, MyException.NullDataException;
    List<Register> getRegisterData() throws RemoteException;
    boolean addStudent(String studentInfo) throws RemoteException, MyException.DuplicationDataException;
    boolean deleteStudent(String studentId) throws RemoteException;
    boolean addCourse(String courseInfo) throws RemoteException, MyException.DuplicationDataException;
    boolean deleteCourse(String courseId) throws RemoteException;
    boolean registerCourse(String studentId, String courseId) throws RemoteException;
    void saveData() throws RemoteException, IOException;
}
