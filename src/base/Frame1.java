package base;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.*;

import static base.openFile.findStudent;
import static base.openFile.readerInterest;
import static base.openFile.readerStudent;

public class Frame1 extends JFrame{
	public static Frame2 frame2=new Frame2();
	public static JPanel jPanel=new JPanel();
	public Frame1(){
		setTitle("学生兴趣管理系统");
		setSize(900,900);
		setLocationRelativeTo(getOwner());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);	
		setResizable(false);
		//默认字体
		Font font = new Font("黑体",Font.PLAIN,24);
		UIManager.put("Label.font", font);
	}
	public void addComponent(Student stu) throws IOException {
		jPanel.removeAll();
		repaint();
		System.out.println(" 3"+stu.getName());
		jPanel.setLayout(null);
		//标题
		JLabel title=new JLabel("学 生 信 息",JLabel.CENTER);
		title.setBounds(300, 0, 300, 100);
		title.setFont(new Font("黑体",Font.PLAIN, 40));
		jPanel.add(title);
		//id
		String id=stu.getId();
		//姓名
		JLabel nameLabel=new JLabel("姓 名:");
		JLabel name=new JLabel(""+stu.getName());
		nameLabel.setBounds(200, 100, 100, 100);
		name.setBounds(300, 100, 100, 100);
		jPanel.add(nameLabel);
		jPanel.add(name);
		//学号
		JLabel noLabel=new JLabel("学 号:");
		JLabel no=new JLabel(""+stu.getNo());
		noLabel.setBounds(450,100,100,100);
		no.setBounds(550,100,300,100);
		jPanel.add(noLabel);
		jPanel.add(no);
		//性别
		JLabel sexLabel=new JLabel("性 别:");
		JLabel sex=new JLabel(""+stu.getSex());
		sexLabel.setBounds(200,150,100,100);
		sex.setBounds(300,150,100,100);
		jPanel.add(sexLabel);
		jPanel.add(sex);
		//班级
		JLabel gradeLabel =new JLabel("班 级:");
		JLabel grade=new JLabel(""+stu.getGrade());
		gradeLabel.setBounds(450,150,100,100);
		grade.setBounds(550,150,100,100);
		jPanel.add(gradeLabel);
		jPanel.add(grade);
		//专业
		JLabel majorLabel=new JLabel("专 业:");
		JLabel major=new JLabel(""+stu.getMajor());
		majorLabel.setBounds(200,200,100,100);
		major.setBounds(300,200,100,100);
		jPanel.add(majorLabel);
		jPanel.add(major);
		//兴趣
		JLabel interestLabel=new JLabel("兴 趣 爱 好");
		interestLabel.setBounds(200,200,200,200);
		jPanel.add(interestLabel);
		//棋类
		addInterest(jPanel,stu);
		//查找
		JTextField find=new JTextField();
		find.setBounds(220,800,100,50);
		jPanel.add(find);
		JButton findBtn=new JButton("查找");
		findBtn.setBounds(320,800,100,50);
		jPanel.add(findBtn);
		String findName=null;
		findBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(find.getText());
					if(findStudent(find.getText())!=null){
						addComponent(findStudent(find.getText()));
					}
					else{
						JOptionPane.showMessageDialog(jPanel, "无对象", "提示",JOptionPane.WARNING_MESSAGE);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		//修改按鈕
		JButton edit=new JButton("修改");
		edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				frame2.setVisible(true);
				try {
					frame2.edit(stu);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		edit.setBounds(500, 800, 100, 50);
		jPanel.add(edit);
		//上一个
		JButton previous=new JButton("<");
		previous.setBounds(0,300,50,50);
		//下一个
		JButton next=new JButton(">");
		next.setBounds(850,300,50,50);
		previous.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int row=Integer.parseInt(id)-1;
					stu.ReadInfo(row);
					addComponent(stu);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(id);
					int row=Integer.parseInt(id)+1;
					stu.ReadInfo(row);
					addComponent(stu);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		jPanel.add(next);
		jPanel.add(previous);
		add(jPanel);
		repaint();
	}
	public void addInterest(JPanel panel,Student stu) throws IOException {
		BufferedReader br=readerInterest();
		br.readLine();
        br.mark(100);
        String insString=stu.getInterest();   //兴趣的编码
		System.out.println(stu.getInterest());
        ArrayList insArray=new ArrayList<String>(); //编码数组保存在数组中
        int j=0;
        for(int i=0;i<insString.length();i++) {
        	
        	if(insString.charAt(i)=='{') {
        		j=i+1;
        	}
        	if(insString.charAt(i)=='}') {
        		String sub=insString.substring(j, i);
        		insArray.add(sub);
        	}
        }

        for(int i=0;i<insArray.size();i++) {   //将编码分隔为数字
        	String s=  insArray.get(i).toString();
        	String[] sArray=s.split(",");
//        	for(int k=0;k<sArray.length;k++) {
//        		System.out.println(sArray[k]);
//        	}
        	int row=Integer.parseInt(sArray[0]);
        	String sBr=null;
        	for(int k=0;k<row;k++) {
        		sBr=br.readLine();
        	}
        	String[] sBrArray=sBr.split(" ");
        	for(int x=0;x<sBrArray.length;x++) {
        		System.out.println(sBrArray[x]);
        	}
        	for(int x=0;x<sArray.length;x++) {
        		System.out.println(Integer.parseInt(sArray[x]));
        		JLabel label;
        		if(x==0) {
        			label=new JLabel(sBrArray[0]+":");
        		}
        		else{
        		label=new JLabel(sBrArray[Integer.parseInt(sArray[x])]);
        		}
        		label.setBounds(200+x*100, 300+i*50, 100, 100);
        		panel.add(label);
        	}
        	br.reset();
        }
        
        add(panel);
	}
	
}