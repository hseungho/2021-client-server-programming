package components.setFilter;

import framework.CommonFilterImpl;
import util.student.Student;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
public class SetStudentFilter extends CommonFilterImpl {
    @Override
    public boolean specificComputationForFilter() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(super.out);
        int byte_read = 0;
        Student student = null;
        String studentData = "";
        while(true) {
            ArrayList<Byte> buffer = new ArrayList<Byte>();
            while(byte_read != '\n' && byte_read != -1) {
                byte_read = in.read();
                if(byte_read != -1 && byte_read!=13 && byte_read!=10) buffer.add((byte)byte_read);
            }
            if((byte_read == '\n'||byte_read==-1) && buffer.size()!=0) {
                studentData = "";
                for(byte b : buffer)
                    studentData+=(char)b;
                if(!studentData.equals(""))
                    student = new Student(studentData);
                oos.writeObject(student);
            }
            if (byte_read == -1) return true;
            byte_read = '\0';
            student = null;
            studentData = "";
        }
    }
}
