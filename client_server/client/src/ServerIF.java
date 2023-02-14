
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerIF extends Remote {
	ArrayList<Student> getAllStudentData() throws RemoteException, MyException.NullDataException;
	ArrayList<Course> getAllCourseData() throws RemoteException, MyException.NullDataException;
	String getRegisterData(String studentId) throws RemoteException, MyException.NullDataException, MyException.InvalidedDataException;
	boolean addStudent(String studentInfo) throws RemoteException, MyException.DuplicationDataException;
	boolean deleteStudent(String studentId) throws RemoteException;
	boolean addCourse(String courseInfo) throws RemoteException, MyException.DuplicationDataException;
	boolean deleteCourse(String courseId) throws RemoteException;
	boolean registerCourse(String registerInfo) throws RemoteException, MyException.NullDataException, MyException.InvalidedDataException, MyException.DuplicationDataException;
}
