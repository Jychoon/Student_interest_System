package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static base.openFile.readerStudent;

public class Student extends StudentInfo{
	public Student() throws IOException {
		ReadInfo();
	}
	public void ReadInfo() throws IOException {
		BufferedReader br=readerStudent();
        String stuString=br.readLine();
        stuString=br.readLine();
        String[] info=stuString.split(" ");
        setName(info[0]);
        System.out.println("姓名"+info[0]);
        setId(info[1]);
        setSex(info[2]);
        setGrade(info[3]);
        setMajor(info[4]);
        setInterest(info[5]);
        System.out.println(info[5]);
	}
	public String toString(){
	    return getName()+" "+getId()+" "+getSex()+" "+getGrade()+" "+getMajor()+" "+getInterest();
    }
}
