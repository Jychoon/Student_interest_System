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
import javax.swing.UIManager;

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
		System.out.println(" 3"+stu.getName());
		jPanel.setLayout(null);
		//标题
		JLabel title=new JLabel("学 生 信 息",JLabel.CENTER);
		title.setBounds(300, 0, 300, 100);
		title.setFont(new Font("黑体",Font.PLAIN, 40));
		jPanel.add(title);
		//姓名
		JLabel nameLabel=new JLabel("姓 名:");
		JLabel name=new JLabel(""+stu.getName());
		nameLabel.setBounds(200, 100, 100, 100);
		name.setBounds(300, 100, 100, 100);
		jPanel.add(nameLabel);
		jPanel.add(name);
		//学号
		JLabel idLabel=new JLabel("学 号:");
		JLabel id=new JLabel(""+stu.getId());
		idLabel.setBounds(450,100,100,100);
		id.setBounds(550,100,300,100);
		jPanel.add(idLabel);
		jPanel.add(id);
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
		
		//修改按鈕
		JButton edit=new JButton("修改");
		edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				frame2.setVisible(true);
				frame2.edit(stu);
				}
		});
		edit.setBounds(300, 800, 100, 50);
		jPanel.add(edit);
		//返回
		JButton back=new JButton("返回");
		back.setBounds(500, 800, 100, 50);
		jPanel.add(back);
		add(jPanel);
	}
	public void addInterest(JPanel panel,Student stu) throws IOException {
		JLabel insLabel=new JLabel();
		String pathname="D:\\项目\\Interest.txt";
		File filename=new File(pathname);
		InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); 
        BufferedReader br = new BufferedReader(reader);
        br.readLine();
        br.mark(1);
        String insString=stu.getInterest();   //兴趣的编码
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