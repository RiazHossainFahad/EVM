import java.lang.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Vote extends JFrame implements ActionListener,MouseListener{
	private JLabel uid,pass,vote;
	private JTextField tf;
	private JPasswordField pf;
	private JButton login,backButton,forgotButton;
	private JPanel panel;
	private int voterid;
	public Vote(){
		super("Election Voting Management");
		this.setSize(800,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel=new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);
		
		vote=new JLabel("Login System For Vote ");
		vote.setBounds(200,50,300,30);
		vote.setBackground(Color.BLACK);
		vote.setForeground(Color.RED);
		vote.setOpaque(true);
		vote.setFont(new Font("ROMAN_BASELINE",Font.BOLD,20));
		panel.add(vote);
		
		uid=new JLabel("Voter Id: ");
		uid.setBounds(170,110,110,30);
		uid.setFont(new Font("SERIF",Font.PLAIN,20));
		uid.setBackground(Color.BLACK);
		uid.setForeground(Color.BLUE);
		uid.setOpaque(true);
		panel.add(uid);
		
		pass=new JLabel("Password: ");
		pass.setBounds(170,180,110,30);
		pass.setFont(new Font("SERIF",Font.PLAIN,20));
		pass.setBackground(Color.BLACK);
		pass.setForeground(Color.BLUE);
		pass.setOpaque(true);
		panel.add(pass);
		
		tf=new JTextField();
		tf.setBounds(300,110,150,30);
		tf.setFont(new Font("ROMANIA_BASELINE",Font.PLAIN,15));
		panel.add(tf);
		
		pf=new JPasswordField();
		pf.setEchoChar('*');
		pf.setBounds(300,180,150,30);
		panel.add(pf);
		
		login=new JButton("Login");
		login.setBounds(250,250,80,30);
		login.setBackground(Color.GREEN);
		login.setFont(new Font("ROMAN_BASELINE",Font.BOLD,15));
		login.addMouseListener(this);
		login.addActionListener(this);
		panel.add(login);
		
		backButton=new JButton("Back");
		backButton.setBounds(350,250,80,30);
		backButton.setBackground(Color.RED);
		backButton.setFont(new Font("ROMAN_BASELINE",Font.BOLD,15));
		backButton.addMouseListener(this);
		backButton.addActionListener(this);
		panel.add(backButton);
		
		forgotButton=new JButton("Forgot Password?");
		forgotButton.setBounds(255,300,165,30);
		forgotButton.setBackground(Color.BLACK);
		forgotButton.setFont(new Font("ROMAN_BASELINE",Font.BOLD,15));
		forgotButton.setForeground(Color.WHITE);
		forgotButton.addMouseListener(this);
		forgotButton.addActionListener(this);
		panel.add(forgotButton);
		
		
		this.add(panel);
		
	}
	
	public void mouseEntered(MouseEvent me){
		if(me.getSource().equals(login)){
			login.setBackground(Color.orange);
		}
	}
	public void mousePressed(MouseEvent me){}
	public void mouseClicked(MouseEvent me){}
	public void mouseExited(MouseEvent me){
		if(me.getSource().equals(login)){
			login.setBackground(Color.GREEN);
		}
	}
	public void mouseReleased(MouseEvent me){}
	public void actionPerformed(ActionEvent ae){
		String password="",UserT="";
		String elementText=ae.getActionCommand();
		String query = "SELECT `voterId`,`UserName`, `Password`,`UserType`FROM `registration`";     
        Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB
		
		 if(elementText.equals(login.getText())){
			 int flag=0;
			 String s1=tf.getText();
			 if(s1.equals("")){
				 JOptionPane.showMessageDialog(this,"Empty Voter ID,Give A voter Id");
			 }
		else{	 
		try
			{
			this.voterid=Integer.parseInt(s1);
			Class.forName("com.mysql.jdbc.Driver");//load driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebm","root","");
			st = con.createStatement();//create statement
			rs = st.executeQuery(query);//getting result					
			while(rs.next())
			{	
			if(rs.getInt("voterId")==this.voterid){
				password= rs.getString("Password");
				UserT=rs.getString("UserType");
				this.voterid=rs.getInt("voterId");
				flag=1;
			}
			}
			if(flag==0){
				JOptionPane.showMessageDialog(this,"Wrong Voter ID,Give A valid voter Id");
			}
		}
        catch(Exception ex){}
		finally {
			try
			{
                if(rs!=null)
					rs.close();

                if(st!=null)
					st.close();

                if(con!=null)
					con.close();
            }
            catch(Exception ex){}
			}
			 
		if(password.equals(pf.getText())){
			if(UserT.equals("Admin")){
			AdminOption ao=new AdminOption(voterid);
			ao.setVisible(true);
			this.setVisible(false);
			}
			else if(UserT.equals("User")) {
				this.checkVoteStatus();
			}
			else{}
			}
			else if(!password.equals(pf.getText())){
			JOptionPane.showMessageDialog(this,"Wrong Password");
			}
		}
	}
		
		else if(elementText.equals(backButton.getText())){
			Welcome w=new Welcome();
			w.setVisible(true);
			this.setVisible(false);
			
		}
		else if(elementText.equals(forgotButton.getText())){
			ChangePassword cp=new ChangePassword();
			cp.setVisible(true);
			this.setVisible(false);
			
		}
	}
	
	public void checkVoteStatus(){
		String queryCheck="SELECT `voteStatus` FROM `registration` WHERE `voterId`='"+this.voterid+"'";
		try{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebm","root","");
			Statement st = con.createStatement();//create statement
			ResultSet rs = st.executeQuery(queryCheck);//getting result					
			while(rs.next())
			{
			 int i=rs.getInt("voteStatus");
			 this.nextStep(i);
			}		
		}
		catch(Exception e){}
	}
	
	public void nextStep(int i){
		if(i==1){JOptionPane.showMessageDialog(this,"Already,You have Done Your Vote.\n\tThank You");}
		else {
			UserVote uv=new UserVote(this.voterid);
			uv.setVisible(true);
			this.setVisible(false);
		}
		
	}
}
	