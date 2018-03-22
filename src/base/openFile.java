package base;

import java.io.*;

import static java.awt.SystemColor.info;

public class openFile {
    public static BufferedReader readerStudent(){
        String pathname="D:\\JavaCode\\RJ2Project\\StudentInfo.txt";
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
        String pathname="D:\\JavaCode\\RJ2Project\\StudentInfo.txt";
        java.io.File filename=new java.io.File(pathname);
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedWriter wr = new BufferedWriter(writer);
        return wr;
        //写入数据

    }
    public static void addStudent(Student stu,String mode) throws IOException {
        //mode =edit为编辑 mode =add为添加 mode=delete 为删除
        String info=stu.toString();
        BufferedReader br=readerStudent();

        String string;
        String [] strings=new String[100]; //保存读入缓存
        int flag=0;
        int row=0;
        do{
            string=br.readLine();
            if(string==null){
                break;
            }
            strings[row]=string;
            row++;
        }while(string!=null);
        BufferedWriter wr=writerStudent();
        //mode 1
        int index=Integer.parseInt(stu.getId());  //获得索引
        if(mode.equals("edit")){
            strings[index]=stu.toString();
        }
        if(mode.equals("add")){
            stu.setId(row+"");
            strings[row]=stu.toString();
            row++;
        }
        if(mode.equals("delete")){  //删除这一行，其余行更新id
            strings[index]="";
            int rowId=1;
            for(int i=1;i<row;i++){ //从第二行开始
                if(strings[i]!=""){
                    String [] rowStrings=strings[i].split(" ");
                    rowStrings[0]=""+rowId;
                    strings[i]=rowStrings[0]+" "+rowStrings[1]+" "+rowStrings[2]+" "+rowStrings[3]+" "+
                            rowStrings[4]+" "+rowStrings[5]+" "+rowStrings[6];
                    rowId++;
                }
            }
        }
        for(int i=0;i<row;i++){
            if(strings[i]==""){
                continue;
            }
            wr.write(strings[i]);
            wr.newLine();
        }
        wr.flush();
        br.close();
    }
    public static Student findStudent(String name) throws IOException {
        BufferedReader br=readerStudent();
        String string;
        String [] strings=new String[100]; //保存读入缓存
        Student stu=null;
        do{
            string=br.readLine();
            if(string==null){
                break;
            }
            strings=string.split(" ");
            if(strings[1].equals(name)){
                stu=new Student();
                stu.ReadInfo(string);
                System.out.println(stu.toString());
                return stu;
            }
        }while(string!=null);
        return stu;
    }
    public static BufferedReader readerInterest() throws FileNotFoundException {
        String pathname="D:\\JavaCode\\RJ2Project\\Interest.txt";
        File filename=new File(pathname);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        return br;
    }
    public static BufferedWriter writerInterest(){
        String pathname="D:\\JavaCode\\RJ2Project\\Interest.txt";
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
