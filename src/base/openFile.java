package base;

import java.io.*;

import static java.awt.SystemColor.info;

public class openFile {
    public static BufferedReader readerStudent(){
        String pathname="D:\\项目\\StudentInfo.txt";
        java.io.File filename=new java.io.File(pathname);
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(reader);
        return br;
    }
    public static BufferedWriter writerStudent() throws IOException {
        String pathname="D:\\项目\\StudentInfo.txt";
        java.io.File filename=new java.io.File(pathname);
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(filename,true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedWriter wr = new BufferedWriter(writer);
        return wr;
        //写入数据

    }
    public static void addStudent(Student stu) throws IOException {
        String info=stu.toString();
        //System.out.println(info);
        BufferedReader br=readerStudent();
        BufferedWriter wr=writerStudent();
        String string;
        int flag=0;
        do{
            string=br.readLine();
            if(string==null){
                continue;
            }
            if(string.equals(info))
                flag=1;
        }while(string!=null);
        if(flag==0){
            wr.newLine();
            wr.write(info);
        }
        wr.flush();
        br.close();
    }
    public static BufferedReader readerInterest() throws FileNotFoundException {
        String pathname="D:\\项目\\Interest.txt";
        File filename=new File(pathname);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        return br;
    }
    public static BufferedWriter writerInterest(){
        String pathname="D:\\项目\\Interest.txt";
        java.io.File filename=new java.io.File(pathname);
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedWriter wr = new BufferedWriter(writer);
        return wr;
    }
    public static void addFileInterest(String insName,String ins) throws IOException {

        BufferedReader br=readerInterest();

        String string;
        String[] strings=new String[10];
        int row=0;
        int flag=0;
        do{
            string=br.readLine();
            if(string==null){
                break;
            }
            strings[row]=string;
            System.out.println(string);
            row++;
        }while(string!=null);
        BufferedWriter wr=writerInterest();
        for(int i=0;i<row;i++){
            System.out.println(strings[i]);
            if(strings[i].indexOf(insName)!=-1){
                flag=1;
                if(strings[i].indexOf(ins)!=-1){

                }
                else{
                    strings[i]=strings[i]+" "+ins;
                }
            }
            wr.write(strings[i]);
            wr.newLine();
        }
        if(flag==0){
            wr.write(insName+" "+ins);
            wr.newLine();
        }
        wr.flush();
        wr.close();
    }

}
