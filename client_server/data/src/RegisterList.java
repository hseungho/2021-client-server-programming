import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RegisterList {
	protected ArrayList<Register> vRegister;
	private File registerFile;
	
	public RegisterList(String registerFileName) throws IOException {
		this.registerFile = new File(registerFileName);
		BufferedReader objStudentFile = new BufferedReader(new FileReader(this.registerFile));
		this.vRegister = new ArrayList<Register>();
		while (objStudentFile.ready()) {
			String regInfo = objStudentFile.readLine();
			if (!regInfo.equals("")) {
				this.vRegister.add(new Register(regInfo));
			}
		}
		objStudentFile.close();
	}
	
	public ArrayList<Register> getAllRegisterRecords()  {
		return this.vRegister;
	}
	
	public boolean addRegister(String studentId, String courseId) {
		for(Register register : this.vRegister) {
			if(register.match(studentId)) {
				register.addCourse(courseId);
				return true;
			}
		}
		if(this.vRegister.add(new Register(studentId, courseId))) return true;
		else return false;
	}

	public void saveData() throws IOException {
		if(!this.registerFile.exists()) {
			if(this.registerFile.createNewFile())
				System.out.println("Create New File !!!");
			else {
				System.out.println("Cannot Create New File !!!");
				return;
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(this.registerFile, false));
		for(int i=0; i<this.vRegister.size(); i++) {
			writer.write(this.vRegister.get(i).toString()+"\n");
		}
		writer.flush();
		writer.close();		
	}
}
