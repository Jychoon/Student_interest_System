package base;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class Frame2 extends JFrame{
	public static JPanel jPanel=new JPanel();
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
	}
	public void edit(Student stu) {
		repaint();
		jPanel.setLayout(null);
		Font font = new Font("黑体",Font.PLAIN,24);
		//标题
		
		JLabel title=new JLabel("修 改 信 息",JLabel.CENTER);
		title.setBounds(300, 0, 300, 100);
		title.setFont(new Font("黑体",Font.PLAIN, 40));
		jPanel.add(title);
		//姓名
		JLabel nameLabel=new JLabel("姓 名:");
		nameLabel.setBounds(200, 100, 100, 100);
		jPanel.add(nameLabel);
		JTextField name=new JTextField(stu.getName());
		name.setBounds(300, 130, 150, 40);
		name.setFont(font);
		jPanel.add(name);
		//学号
		JLabel idLabel=new JLabel("学 号:");
		JTextField id=new JTextField(stu.getId());
		idLabel.setBounds(450,100,100,100);
		id.setBounds(550,130,150,40);
		id.setFont(font);
		jPanel.add(idLabel);
		jPanel.add(id);
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
		JButton submit=new JButton("提交");
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				stu.setName(name.getText());
				System.out.println(name.getText()+stu.getName());
				stu.setId(id.getText());
				stu.setSex(sex.getText());
				stu.setGrade(grade.getText());
				stu.setMajor(major.getText());
			}
		});
		submit.setBounds(300, 800, 100, 50);
		jPanel.add(submit);
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
	
}
