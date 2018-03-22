package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static base.openFile.readerStudent;

public class Student extends StudentInfo{
	public Student() throws IOException {
		ReadInfo(1);
	}
	public void ReadInfo(int row) throws IOException {
		BufferedReader br=readerStudent();
		String stuString;
        stuString=br.readLine();
        if(row<=0){
            row=1;
        }
        int i;
        for (i= 0; i < row; i++) {
            stuString = br.readLine();
        }
        if(stuString==null){
            return ;
        }
        String[] info=stuString.split(" ");
        setId(info[0]);
        setName(info[1]);
        System.out.println("姓名"+info[1]);
        setNo(info[2]);
        setSex(info[3]);
        setGrade(info[4]);
        setMajor(info[5]);
        setInterest(info[6]);
        System.out.println(info[5]);
	}
    public void ReadInfo(String s) throws IOException {
        String[] info=s.split(" ");
        setId(info[0]);
        setName(info[1]);
        System.out.println("姓名"+info[1]);
        setNo(info[2]);
        setSex(info[3]);
        setGrade(info[4]);
        setMajor(info[5]);
        setInterest(info[6]);
        System.out.println(info[5]);
    }
	public String toString(){
	    return getId()+" "+getName()+" "+getNo()+" "+getSex()+" "+getGrade()+" "+getMajor()+" "+getInterest();
    }
}
