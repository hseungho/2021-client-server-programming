
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Client {
	private static ServerIF server;

	public static void main(String[] args) throws IOException {
		BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			try {
				server = (ServerIF)Naming.lookup("Server");
				printMenu();
				String readLine = objReader.readLine();
				if(readLine==null) return;
				String sChoice = readLine.trim();
				switch(sChoice) {
					case "1": // 학생 리스트 프린트
						showList(server.getAllStudentData());
						break;
					case "2": // 강의 리스트 프린트
						showList(server.getAllCourseData());
						break;
					case "3": // 학생 데이터 추가
						addStudent(objReader);
						break;
					case "4": // 학생 데이터 삭제
						deleteStudent(objReader);
						break;
					case "5": // 강의 데이터 추가
						addCourse(objReader);
						break;
					case "6": // 강의 데이터 삭제
						deleteCourse(objReader);
						break;
					case "7": // 수강신청
						registerCourse(objReader);
						break;
					case "8": // 학생별 수강신청 목록 리스트 프린트
						showRegister(objReader);
						break;
					case "x":
						return;
					default: 
						System.out.println("Please select the correct menu !!!");
						break;
				}
			}
			catch (MyException.InvalidedDataException e) {System.out.println(e.getMessage()); }
			catch (MyException.DuplicationDataException e) {System.out.println(e.getMessage());}
			catch (MyException.NullDataException e) {System.out.println(e.getMessage());}
			catch (MalformedURLException e) {e.printStackTrace();}
			catch (RemoteException e) {e.printStackTrace();}
			catch (NotBoundException e) {e.printStackTrace();}
		}
	}

	private static void showRegister(BufferedReader objReader) throws IOException, MyException.NullDataException, MyException.InvalidedDataException {
		System.out.println("<<<<<<<<<<<<<<   Show Register List   >>>>>>>>>>>>>>");
		System.out.print("Student ID: "); String studentId = objReader.readLine().trim();
		if(studentId.equals("")) throw new MyException.NullDataException("Please enter Student ID !!!!");
		if(studentId.equals("x")) return;
		System.out.println(server.getRegisterData(studentId));
	}
	
	private static void registerCourse(BufferedReader objReader) throws IOException, MyException.NullDataException, MyException.InvalidedDataException, MyException.DuplicationDataException {
		System.out.println("<<<<<<<<<<<<<<   Register Course   >>>>>>>>>>>>>>");
		System.out.print("Student ID: "); String studentId = objReader.readLine().trim();
		if(studentId.equals("")) throw new MyException.NullDataException("Please enter Student ID !!!!");
		if(studentId.equals("x")) return;
		System.out.print("Course ID: "); String courseId = objReader.readLine().trim();
		if(courseId.equals("")) throw new MyException.NullDataException("Please enter Course ID !!!!");
		
		if(server.registerCourse(studentId+" "+courseId))
			System.out.println("Register SUCCESS !!!");
		else System.out.println("Register FAIL !!!");
	}
	
	private static void addCourse(BufferedReader objReader) throws IOException, RemoteException, MyException.DuplicationDataException, MyException.NullDataException {
		System.out.println("<<<<<<<<<<<<<<   Add Course   >>>>>>>>>>>>>>");
		System.out.println("------Course Information------");
		System.out.print("Course ID: "); String courseId = objReader.readLine().trim();
		if(courseId.equals("")) throw new MyException.NullDataException("Please enter Course ID !!!!");
		if(courseId.equals("x")) return;
		System.out.print("Professor Name: "); String profName = objReader.readLine().trim();
		if(profName.equals("")) throw new MyException.NullDataException("Please enter Professor Name !!!!");
		System.out.print("Course Name: "); String courseName = objReader.readLine().trim();
		if(courseName.equals("")) throw new MyException.NullDataException("Please enter Course Name !!!!");
		System.out.print("Prerequisite: "); String prerequisite = objReader.readLine().trim();
		
		if(server.addCourse(courseId+" "+profName+" "+courseName+" "+prerequisite))
			System.out.println("Add SUCCESS !!!");
		else System.out.println("Add FAIL !!!");
	}
	
	private static void deleteCourse(BufferedReader objReader) throws RemoteException, IOException, MyException.NullDataException {
		System.out.println("<<<<<<<<<<<<<<   Delete Course   >>>>>>>>>>>>>>");
		System.out.print("Course ID: "); String courseId = objReader.readLine().trim();
		if(courseId.equals("")) throw new MyException.NullDataException("Please enter Course ID !!!!");
		if(courseId.equals("x")) return;
		if(server.deleteCourse(courseId)) 
			System.out.println("Delete SUCCESS !!!");
		else System.out.println("Delete FAIL !!!");
	}

	private static void addStudent(BufferedReader objReader) throws IOException, RemoteException, MyException.DuplicationDataException, MyException.NullDataException {
		System.out.println("<<<<<<<<<<<<<<   Add Student   >>>>>>>>>>>>>>");
		System.out.println("------Student Information------");
		System.out.print("Student ID: "); String studentId = objReader.readLine().trim();
		if(studentId.equals("")) throw new MyException.NullDataException("Please enter Student ID !!!!");
		if(studentId.equals("x")) return;
		System.out.print("Student Name: "); String studentName = objReader.readLine().trim();
		if(studentName.equals("")) throw new MyException.NullDataException("Please enter Student Name !!!!");
		System.out.print("Student Department: "); String studentDept = objReader.readLine().trim();
		if(studentDept.equals("")) throw new MyException.NullDataException("Please enter Student Department !!!!");
		System.out.print("Student Completed Course List: "); String completedCourseList = objReader.readLine().trim();
		
		if(server.addStudent(studentId+" "+studentName+" "+studentDept+" "+completedCourseList))
			System.out.println("Add SUCCESS !!!");
		else System.out.println("Add FAIL !!!");
	}

	private static void deleteStudent(BufferedReader objReader) throws RemoteException, IOException, MyException.NullDataException {
		System.out.println("<<<<<<<<<<<<<<   Delete Student   >>>>>>>>>>>>>>");
		System.out.print("Student ID: "); String studentId = objReader.readLine().trim();
		if(studentId.equals("")) throw new MyException.NullDataException("Please enter Student ID !!!!");
		if(studentId.equals("x")) return;
		if(server.deleteStudent(studentId)) 
			System.out.println("Delete SUCCESS !!!");
		else System.out.println("Delete FAIL !!!");
	}

	private static void printMenu() {
		System.out.println();
		System.out.println("******************** MENU *********************");
		System.out.println("1. List Students");
		System.out.println("2. List Courses");
		System.out.println("3. Add Student");
		System.out.println("4. Delete Student");
		System.out.println("5. Add Course");
		System.out.println("6. Delete Course");
		System.out.println("7. Make Reservation");
		System.out.println("8. List Reservation");
		System.out.println("x. Exit");
	}

	private static void showList(ArrayList<?> dataList) {
		Object object = dataList.get(0);
		if(object instanceof Student) System.out.println("<<<<<<<<<<<<<<   Student List   >>>>>>>>>>>>>>");
		if(object instanceof Course) System.out.println("<<<<<<<<<<<<<<   Course List   >>>>>>>>>>>>>>");
		String list = "";
		for(int i=0; i<dataList.size(); i++) {
			list += "\n" + dataList.get(i);
		}
		System.out.println(list);
	}
}
