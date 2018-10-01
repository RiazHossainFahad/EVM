import java.lang.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ChangePassword extends JFrame implements ActionListener {
			private JLabel textLabel,voterIdLabel,passwordLabel,confirmLabel;
			private JTextField voterIdTF;
			private JPasswordField passwordPF,confirmPF;
			private JButton changeButton,mainmenuButton;
			private JPanel panel;
			private int voterid;
			
	public ChangePassword(){
			super("Election Voting Management");
			this.setSize(800,400);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			panel=new JPanel();
			panel.setBackground(Color.BLACK);
			panel.setLayout(null);
			
			textLabel=new JLabel("Change Password");
			textLabel.setBounds(250,10,250,30);
			textLabel.setForeground(Color.BLUE);
			textLabel.setFont(new Font("ROMANIA_SANS",Font.BOLD,25));
			panel.add(textLabel);
			
			voterIdLabel=new JLabel("Enter Voter Id: ");
			voterIdLabel.setBounds(80,100,170,30);
			voterIdLabel.setForeground(Color.CYAN);
			voterIdLabel.setFont(new Font("SERIF",Font.BOLD,20));
			panel.add(voterIdLabel);
			
			voterIdTF=new JTextField();
			voterIdTF.setBounds(255,100,150,30);
			voterIdTF.setFont(new Font("ROMANIA_BASELINE",Font.PLAIN,15));
			panel.add(voterIdTF);
			
			passwordLabel=new JLabel("New Password: ");
			passwordLabel.setBounds(80,150,170,30);
			passwordLabel.setForeground(Color.CYAN);
			passwordLabel.setFont(new Font("SERIF",Font.BOLD,20));
			panel.add(passwordLabel);
			
			passwordPF=new JPasswordField();
			passwordPF.setBounds(255,150,150,30);
			panel.add(passwordPF);
			
			confirmLabel=new JLabel("Confirm Password:");
			confirmLabel.setBounds(80,200,170,30);
			confirmLabel.setForeground(Color.CYAN);
			confirmLabel.setFont(new Font("SERIF",Font.BOLD,20));
			panel.add(confirmLabel);
			
			confirmPF=new JPasswordField();
			confirmPF.setBounds(255,200,150,30);
			panel.add(confirmPF);
			
			changeButton=new JButton("Change");
			changeButton.setBounds(110,270,120,30);
			changeButton.setBackground(Color.RED);
			changeButton.setFont(new Font("SERIF",Font.BOLD,15));
			changeButton.addActionListener(this);
			panel.add(changeButton);
			
			mainmenuButton=new JButton("Back");
			mainmenuButton.setBounds(250,270,120,30);
			mainmenuButton.setBackground(Color.YELLOW);
			mainmenuButton.setFont(new Font("SERIF",Font.BOLD,15));
			mainmenuButton.addActionListener(this);
			panel.add(mainmenuButton);
			
			this.add(panel);
			
			
		}
	
	public void actionPerformed(ActionEvent ae){
		String actionText=ae.getActionCommand();
		String vi=voterIdTF.getText();
		
		if(actionText.equals(changeButton.getText())){
			
		try{
			if(!("").equals(vi)){
			this.voterid=Integer.parseInt(vi);
			this.checkInDatabase(voterid);
			}
			else{JOptionPane.showMessageDialog(this,"Empty VoterId Field,Please Give a Valid ID ");}
		}
		catch(Exception e){}
			
		}
		if(actionText.equals(mainmenuButton.getText())){
			Vote v1=new Vote();
			v1.setVisible(true);
			this.setVisible(false);
			
		}
	}
	
	public void checkInDatabase(int v){
		int flag=0;
		String query="Select `voterId` FROM `registration`";
		try{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebm","root","");
			Statement st = con.createStatement();//create statement
			ResultSet rs = st.executeQuery(query);//getting result					
			while(rs.next())
			{
			 if(rs.getInt("voterId")==v){
				 this.matchPassword();
				 flag++;
			 }
			}
			if(flag==0){
				 JOptionPane.showMessageDialog(this,"Wrong VoterId!!Try Registered one");
			 }
		}
		catch(Exception e){}
		
	}
	
	public void matchPassword(){
		String s1=passwordPF.getText();
		String s2=confirmPF.getText();
		
		if(!("").equals(s1)&&!("").equals(s2)){
			if(s1.equals(s2)){
				String queryChange="UPDATE `registration` SET `Password`='"+s1+"' where `voterId`='"+this.voterid+"'";
		try{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebm","root","");
			Statement st = con.createStatement();//create statement
			st.execute(queryChange);
			con.close();
			st.close();
			JOptionPane.showMessageDialog(this,"Your VoterID: "+this.voterid+"\n"+"Your New Password: "+s1+"\n");
		}
		catch(Exception E){}
			}
			else{
			JOptionPane.showMessageDialog(this,"Password MisMatched....Try Again");
		}
			
		}
		else{
			JOptionPane.showMessageDialog(this,"Empty Password Field");
		}	
	}
}