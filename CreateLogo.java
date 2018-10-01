import java.lang.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CreateLogo extends Fvote implements ActionListener{
	private  JPanel panel;
	private JTextField logoTF;
	private JButton add,back;
	
	
	public CreateLogo(){
		super();
		this.setSize(800,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel=new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);
		
		add=new JButton("Add");
		add.setBounds(110,300,120,30);
		add.setBackground(Color.GREEN);
		add.setFont(new Font("ROMAN_BASELINE",Font.BOLD,15));
		add.addActionListener(this);
		panel.add(add);
		
		logoTF=new JTextField();
		logoTF.setBounds(100,100,150,30);
		panel.add(logoTF);
				
		back=new JButton("Back");
		back.setBounds(250,300,120,30);
		back.setBackground(Color.PINK);
		back.setFont(new Font("ROMAN_BASELINE",Font.BOLD,15));
		back.addActionListener(this);
		panel.add(back);
		
		this.add(panel);
	} 
	
	public void actionPerformed(ActionEvent ae){
		String elementText=ae.getActionCommand();
		
		if(elementText.equals(add.getText())){
			String s1=logoTF.getText();
			if(!("").equals(s1)){
			super.newlogo=new JCheckBox(s1);
			super.newlogo.setBounds(400,80,150,30);
			//super.checkBoxGroup.add(newlogo);
			
			super.panel.add(newlogo);
			super.add(panel);
			}
			else{JOptionPane.showMessageDialog(this,"Give A Name of Party");}
		}
		
	}
	
}