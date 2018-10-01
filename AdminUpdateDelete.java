import java.lang.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class AdminUpdateDelete extends JFrame implements ActionListener{
	
	private JLabel uid,pass,loginLabel;
	private JTextField tf;
	private JPasswordField pf;
	private JButton login,backButton;
	private JPanel panel;
	private int voterid;
	public AdminUpdateDelete(){
		super("Election Voting Management");
		this.setSize(800,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel=new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);
		
		loginLabel=new JLabel("Login System For Update/Delete");
		loginLabel.setBounds(200,50,350,30);
		loginLabel.setBackground(Color.BLACK);
		loginLabel.setForeground(Color.RED);
		loginLabel.setOpaque(true);
		loginLabel.setFont(new Font("ROMAN_BASELINE",Font.BOLD,20));
		panel.add(loginLabel);
		
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
		login.addActionListener(this);
		panel.add(login);
		
		backButton=new JButton("Back");
		backButton.setBounds(350,250,80,30);
		backButton.setBackground(Color.RED);
		backButton.setFont(new Font("ROMAN_BASELINE",Font.BOLD,15));
		backButton.addActionListener(this);
		panel.add(backButton);
		
		this.add(panel);
	}
	
	
	public void actionPerformed(ActionEvent ae){
		String actiontext=ae.getActionCommand();
		int flag=0;
		if(actiontext.equals(backButton.getText())){
			Registration r=new Registration();
			r.setVisible(true);
			this.setVisible(false);
		}
		else if(actiontext.equals(login.getText())){
			String s1=tf.getText();
			String password="",UserT="";
		String query = "SELECT `voterId`, `Password`,`UserType`FROM `registration`";     
        Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB		 
		if(!("").equals(s1)){
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
				flag=1;
				
			}
			}
			if(flag==0){JOptionPane.showMessageDialog(this,"Wrong Voter Id,Enter a Valid one ");}
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
			UpdateDelete ud=new UpdateDelete();
			ud.setVisible(true);
			this.setVisible(false);
			}
		else if(UserT.equals("User")) {
				JOptionPane.showMessageDialog(this,"Only Admin can Update/Delete\n You are An User");
			}
	}
	else{JOptionPane.showMessageDialog(this,"Wrong voterId or Password,try Again");}
		}
		
	else{JOptionPane.showMessageDialog(this,"Empty Voter ID Field");}
		}
	
	}
}