package base;

import com.sun.crypto.provider.JceKeyStore;
import com.sun.javafx.collections.MappingChange;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import static base.View.stu;
import static base.openFile.addFileInterest;
import static base.openFile.addStudent;
import static base.openFile.writerStudent;

public class Frame2 extends JFrame{
	public static JPanel jPanel=new JPanel();
	private List[] list=new ArrayList[10];
	private List inList=new ArrayList();
	private String addString1;
	private String addString2;
	public Frame2(){
		setTitle("修改信息");
		setSize(900,900);
		setLocationRelativeTo(getOwner());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(false);	
		setResizable(false);
		//默认字体
		Font font = new Font("黑体",Font.PLAIN,24);
		UIManager.put("Label.font", font);
		for(int i=0;i<10;i++){
			list[i]=new ArrayList<>();
		}
	}
	public void edit(Student stu) throws IOException {
		jPanel.removeAll();
		repaint();
		jPanel.setLayout(null);
		Font font = new Font("黑体",Font.PLAIN,24);
		//标题
		JLabel title=new JLabel("修 改 信 息",JLabel.CENTER);
		title.setBounds(300, 0, 300, 100);
		title.setFont(new Font("黑体",Font.PLAIN, 40));
		jPanel.add(title);
		//id号
		String id=stu.getId();
		//姓名
		JLabel nameLabel=new JLabel("姓 名:");
		nameLabel.setBounds(200, 100, 100, 100);
		jPanel.add(nameLabel);
		JTextField name=new JTextField(stu.getName());
		name.setBounds(300, 130, 150, 40);
		name.setFont(font);
		jPanel.add(name);
		//学号
		JLabel noLabel=new JLabel("学 号:");
		JTextField no=new JTextField(stu.getNo());
		noLabel.setBounds(450,100,100,100);
		no.setBounds(550,130,150,40);
		no.setFont(font);
		jPanel.add(noLabel);
		jPanel.add(no);
		//性别
		JLabel sexLabel=new JLabel("性 别:");
		JTextField sex=new JTextField(stu.getSex());
		sexLabel.setBounds(200,150,100,100);
		sex.setBounds(300,180,40,40);
		sex.setFont(font);
		jPanel.add(sexLabel);
		jPanel.add(sex);
		//班级
		JLabel gradeLabel =new JLabel("班 级:");
		JTextField grade=new JTextField(stu.getGrade());
		gradeLabel.setBounds(450,150,100,100);
		grade.setBounds(550,180,150,40);
		grade.setFont(font);
		jPanel.add(gradeLabel);
		jPanel.add(grade);
		//专业
		JLabel majorLabel=new JLabel("专 业:");
		JTextField major=new JTextField(stu.getMajor());
		majorLabel.setBounds(200,200,100,100);
		major.setBounds(300,230,150,40);
		major.setFont(font);
		jPanel.add(majorLabel);
		jPanel.add(major);
		//兴趣
		JLabel interestLabel=new JLabel("兴 趣 爱 好");
		interestLabel.setBounds(200,200,200,200);
		jPanel.add(interestLabel);
		editInterest(jPanel,stu);
		JButton edit=new JButton("更改");
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stu.setName(name.getText());
				System.out.println(name.getText()+stu.getName());
				stu.setNo(no.getText());
				stu.setSex(sex.getText());
				stu.setGrade(grade.getText());
				stu.setMajor(major.getText());
				//mode 1 编辑
				try {
					addStudent(stu,"edit");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		edit.setBounds(200, 800, 80, 50);
		jPanel.add(edit);
		JButton addInfo=new JButton("添加");
		addInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stu.setName(name.getText());
				System.out.println(name.getText()+stu.getName());
				stu.setNo(no.getText());
				stu.setSex(sex.getText());
				stu.setGrade(grade.getText());
				stu.setMajor(major.getText());
				//mode 1 编辑
				try {
					addStudent(stu,"add");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		addInfo.setBounds(300, 800, 80, 50);
		jPanel.add(addInfo);
		JButton delete=new JButton("删除");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stu.setId(id);
				try {
					addStudent(stu,"delete");
					stu.ReadInfo(Integer.parseInt(id)+1);

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		delete.setBounds(400, 800, 80, 50);
		jPanel.add(delete);
		//返回
		JButton back=new JButton("返回");
		back.setBounds(500, 800, 100, 50);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				Frame1 frame1=new  Frame1();
				try {
					frame1.addComponent(stu);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		jPanel.add(back);
		add(jPanel);
	}

	public void editInterest(JPanel panel,Student stu) throws IOException {
		String pathname="D:\\JavaCode\\RJ2Project\\Interest.txt";
		File filename=new File(pathname);
		InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
		BufferedReader br = new BufferedReader(reader);
		br.readLine();
		br.mark(1);
		String  rows=br.readLine();
		String[] insArray;
		int row_y=0;
		int x=1;
        int btn_y=0;
		while(rows!=null){
			System.out.println(x++);
			insArray= rows.split(" ");
			if(insArray==null){
				break;
			}
			JLabel insName=new JLabel(insArray[0]);
			insName.setBounds(200, 300+50*row_y, 100, 100);
			panel.add(insName);

			for(int i=1;i<insArray.length;i++) {
				JCheckBox checkBox=new JCheckBox();
				checkBox.setBounds(170+100*i,330+50*row_y,30,30);
				jPanel.add(checkBox);
				JLabel ins = new JLabel(insArray[i]);
				ins.setBounds(200 + 100*i, 300 + 50 * row_y, 100, 100);
				panel.add(ins);
				checkBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Point location = checkBox.getLocation();
                        int x=(int)(location.getX()-170)/100;
                        int y=(int)(location.getY()-330)/50;
                        if(checkBox.isSelected()){
                            addInterest(y,x);
                        }
                    }
                });
				btn_y=row_y;
			}

			row_y++;
			rows=br.readLine();

            }
        JButton addBtn=new JButton("+");
        addBtn.setBounds(500,410+50*btn_y,50,20);
        jPanel.add(addBtn);
        //输入兴趣框
        Font font = new Font("黑体",Font.PLAIN,24);
        JLabel addLabel1=new JLabel("类别");
        JTextField add1=new JTextField(addString1);
        addLabel1.setBounds(200,375+50*btn_y,100,100);
        add1.setBounds(300,400+50*btn_y,100,40);
        add1.setFont(font);
        jPanel.add(addLabel1);
        jPanel.add(add1);
        addString1=add1.getText();
        //
        JTextField add2=new JTextField(addString2);
        add2.setBounds(400,400+50*btn_y,100,40);
        add2.setFont(font);
        jPanel.add(add2);
        addString2=add2.getText();
//        add1.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				addString1=add1.getText();
//				System.out.println("#"+addString1);
//			}
//		});
//		add2.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				addString2=add2.getText();
//			}
//		});
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	addString1=add1.getText();
            	addString2=add2.getText();
				try {
					addFileInterest(addString1,addString2);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					edit(stu);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
        });
		}
        public void addInterest(int y,int x){
			int flag=0,flag3=0;
			String string1,string2;
			if(list[y].isEmpty()){
				list[y].add(y+1);
				list[y].add(x);
			}

			else if((int)list[y].get(0)==(y+1)){
                flag=0;

				for(int i = 1 ; i < list[y].size() ; i++) {
					if((int)list[y].get(i)==x)
						break;
					else{
						flag=1;
					}
				}
				if(flag==1){
					list[y].add(x);
				}
			}
			string2=list[y].toString();
			string1=inList.toString();
			if(flag==0&&string1.indexOf(string2)==-1){
				inList.add(list[y]);
			}

			String string=inList.toString();
			StringBuffer stringBuffer=new StringBuffer();
			int begin=0,end=0;
			for(int i=0;i<string.length();i++){
				int flag2=0;
				if(string.charAt(i)=='['){
					Character c=string.charAt(i+1);
					if(Character.isDigit(c)){
						begin=i+1;
					}
				}
				if(string.charAt(i)==']'){
					Character c=string.charAt(i-1);
					if(Character.isDigit(c)){
						end=i;
						flag2=1;
					}
				}
				if(flag2==1) {
					string = inList.toString();
					stringBuffer.append("{");
					stringBuffer.append(string.substring(begin,end));
					stringBuffer.append("}");
					String s=stringBuffer.toString();
					s=s.replaceAll(" ","");
                    System.out.println(s);
					stu.setInterest(s);
				}

			}
        }
}
