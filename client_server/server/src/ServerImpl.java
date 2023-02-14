import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ServerImpl extends UnicastRemoteObject implements Server {

    public static void main(String[] args) {
        try {
            Server server = new ServerImpl();
            Naming.rebind("Server", server);
            System.out.println("Server is ready!!!");

            data = (Data) Naming.lookup("Data");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Data data;
    private boolean isChanged;

    public ServerImpl() throws RemoteException {
        super();

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    if(isChanged) {
                        try {
                            data.saveData();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })
        );
    }

    @Override
    public List<Student> getAllStudentData() throws RemoteException, MyException.NullDataException {
        return data.getAllStudentData();
    }

    @Override
    public List<Course> getAllCourseData() throws RemoteException, MyException.NullDataException {
        return data.getAllCourseData();
    }

    @Override
    public String getRegisterData(String studentId) throws RemoteException, MyException {
        return getRegister(studentId);
    }

    @Override
    public boolean addStudent(String studentInfo) throws RemoteException, MyException.DuplicationDataException {
        if (data.addStudent(studentInfo)) {
            this.isChanged = true;
            return true;
        }
        else return false;
    }

    @Override
    public boolean deleteStudent(String studentId) throws RemoteException {
        if (data.deleteStudent(studentId)) {
            this.isChanged = true;
            return true;
        }
        else return false;
    }

    @Override
    public boolean addCourse(String courseInfo) throws RemoteException, MyException.DuplicationDataException {
        if (data.addCourse(courseInfo)) {
            this.isChanged = true;
            return true;
        }
        else return false;
    }

    @Override
    public boolean deleteCourse(String courseId) throws RemoteException {
        if (data.deleteCourse(courseId)) {
            this.isChanged = true;
            return true;
        }
        else return false;
    }

    @Override
    public boolean registerCourse(String registerInfo) throws RemoteException, MyException {
        if (checkRegisterInfo(registerInfo)) {
            this.isChanged = true;
            return true;
        }
        else return false;
    }

    private boolean checkRegisterInfo(String registerInfo) throws RemoteException, MyException {
        String[] registerInfoArr = registerInfo.split(" ");
        String studentId = registerInfoArr[0];
        String courseId = registerInfoArr[1];

        List<Student> students = data.getAllStudentData();
        List<Course> courses = data.getAllCourseData();

        if(!checkId(0, studentId, students)) throw new MyException.InvalidedDataException("~~~~~Student ID "+studentId+" is Invalided Student ID !!!~~~~~");
        if(!checkId(0, courseId, courses)) throw new MyException.InvalidedDataException("~~~~~Course ID "+courseId+" is Invalided Course ID !!!~~~~~");
        String needTaken = checkCourse(studentId, courseId, students, courses);
        if(!needTaken.equals("")) throw new MyException.InvalidedDataException("The student didn't take "+ needTaken +" course !!!");
        
        return registerCourse(studentId, courseId);
    }

    private boolean checkId(int count, String id, List<?> dataList) {
        if(count == dataList.size()) return false;
        Object o = dataList.get(count);
        if(o instanceof Student) {
            if(((Student) o).match(id)) return true;
        } else if(o instanceof Course) {
            if(((Course) o).match(id)) return true;
        }
        return checkId(count+1, id, dataList);
    }
    
    private String checkCourse(String studentId, String courseId, List<Student> students, List<Course> courses) throws MyException, RemoteException {
        List<String> completedCourseIds = checkCourseOfStudent(studentId, courseId, students);
        
        List<String> prerequisites = null;
        for(Course course : courses) {
            if (course.match(courseId)) {
                prerequisites = course.getPrerequisites();
                break;
            }
        }
        if(prerequisites == null) return "";
        
        List<String> needTakens = new ArrayList<>(prerequisites);
        needTakens.removeAll(completedCourseIds);
        if(needTakens.size()==0) return "";
        else {
            return Arrays.toString(needTakens.toArray());
        }
    }

    private List<String> checkCourseOfStudent(String studentId, String courseId, List<Student> students) throws MyException, RemoteException {
        List<String> completedCourseIds = null;
        for(Student student : students) {
            if (student.match(studentId)) {
               completedCourseIds = student.getCompletedCourses();
               break;
            }
        }
        if(completedCourseIds == null) throw new MyException.NullDataException("The student didn't complete any course.");

        for(String strCourse : completedCourseIds) {
            if (Objects.equals(courseId, strCourse)) {
                throw new MyException.DuplicationDataException("The student has already taken this course !!!");
            }
        }
        List<Register> registers = data.getRegisterData();
        if(registers != null) {
            for(Register register : registers) {
                if(register.match(studentId) && register.checkAlreadyRegister(courseId)) {
                    throw new MyException.DuplicationDataException("The student has already registered this course !!!");
                }
            }
        }
        return completedCourseIds;
    }

    private boolean registerCourse(String studentId, String courseId) throws RemoteException {
        return data.registerCourse(studentId, courseId);
    }
    private String getRegister(String studentId) throws RemoteException, MyException {
        List<Student> students = data.getAllStudentData();
        List<Register> registers = data.getRegisterData();
        StringBuilder builder = new StringBuilder();
        builder.append("Student ");

        Student student = null;
        for(Student s : students) {
            if(s.match(studentId)) {
                student = s;
                break;
            }
        }
        if(student == null) {
            throw new MyException.InvalidedDataException("~~~~~Student ID "+studentId+" is Invalided Student ID !!!~~~~~");
        }
        builder.append(student.getName());

        Register register = null;
        List<String> registerCourseIds = null;
        for(Register r : registers) {
            if(r.match(studentId)) {
                register = r;
                registerCourseIds = register.getCourseIds();
                break;
            }
        }
        if(register == null) {
            throw new MyException.NullDataException(studentId+" "+ builder +" didn't register course !!!");
        }
        builder.append(" registered ");
        registerCourseIds.forEach(id -> builder.append(id).append(" "));
        builder.append("course(s)");
        return builder.toString();
    }
}
