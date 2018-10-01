import java.lang.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Welcome extends JFrame implements MouseListener,ActionListener{
	private JLabel welcome,imglabel,enterVoterid;
	private JTextField searchField;
	private JButton vote,registration,search,exitButton;
	private JPanel panel;
	private ImageIcon image;
	public Welcome(){
		super("Election Voting Management");
		this.setSize(800,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel=new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);
		
		image=new ImageIcon("election.jpg");
		imglabel=new JLabel(image);
		imglabel.setBounds(280,20,110,100);
		panel.add(imglabel);
		
		welcome=new JLabel("Welcome To EVM");
		welcome.setBounds(200,140,250,30);
		welcome.setForeground(Color.BLUE);
		welcome.setFont(new Font(null,Font.BOLD,30));
		panel.add(welcome);
		
		enterVoterid=new JLabel("Enter VoterID ");
		enterVoterid.setBounds(580,40,100,30);
		enterVoterid.setForeground(Color.RED);
		panel.add(enterVoterid);
		
		search=new JButton("Search ");
		search.setBounds(700,10,80,30);
		search.setForeground(Color.RED);
		search.setBackground(Color.YELLOW);
		search.addActionListener(this);
		panel.add(search);
		
		searchField=new JTextField();
		searchField.setBounds(550,10,140,30);
		searchField.setForeground(Color.RED);
		searchField.setFont(new Font("ROMANIA_BASELINE",Font.PLAIN,15));
		panel.add(searchField);
		
		registration=new JButton("Registration");
		registration.setBounds(180,200,120,40);
		registration.setBackground(Color.GREEN);
		registration.setForeground(Color.BLUE);
		registration.setFont(new Font("ROMAN_BASELINE",Font.PLAIN,15));
		registration.addMouseListener(this);
		registration.addActionListener(this);
		panel.add(registration);
		
		vote=new JButton("V o t e");
		vote.setBounds(350,200,120,40);
		vote.setBackground(Color.GREEN);
		vote.setFont(new Font("ROMAN_BASELINE",Font.PLAIN,15));
		vote.setForeground(Color.BLUE);
		vote.addMouseListener(this);
		vote.addActionListener(this);
		panel.add(vote);
		
		exitButton=new JButton("Exit");
		exitButton.setBounds(255,260,120,40);
		exitButton.setBackground(Color.RED);
		exitButton.setFont(new Font("ROMAN_BASELINE",Font.PLAIN,17));
		exitButton.setForeground(Color.WHITE);
		exitButton.addMouseListener(this);
		exitButton.addActionListener(this);
		panel.add(exitButton);
		
		this.add(panel);
	}
	public void mouseEntered(MouseEvent me){
		if(me.getSource().equals(registration)){
			registration.setBackground(Color.CYAN);
		}
		else if(me.getSource().equals(vote)){
			vote.setBackground(Color.CYAN);
		}
		else if(me.getSource().equals(exitButton)){
		exitButton.setBackground(Color.CYAN);
	}
	}
	public void mousePressed(MouseEvent me){}
	public void mouseClicked(MouseEvent me){}
	public void mouseExited(MouseEvent me){
		if(me.getSource().equals(registration)){
			registration.setBackground(Color.GREEN);
		}
		else if(me.getSource().equals(vote)){
			vote.setBackground(Color.GREEN);
		}
		
	else if(me.getSource().equals(exitButton)){
		exitButton.setBackground(Color.RED);
	}
	}
	public void mouseReleased(MouseEvent me){}
	public void actionPerformed(ActionEvent ae){
		String elementText=ae.getActionCommand();
		if(elementText.equals(registration.getText())){
			Adminlogin a=new Adminlogin();
			a.setVisible(true);
			this.setVisible(false);
			
		}
		else if(elementText.equals(vote.getText())){
			Vote v=new Vote();
			v.setVisible(true);
			this.setVisible(false);
			
		}
		else if(elementText.equals(exitButton.getText())){
			System.exit(0);
			
		}
		
		else if(elementText.equals(search.getText())){
				String v=searchField.getText();
				if(!("").equals(searchField.getText())){
				int i=Integer.parseInt(v),f=0;
				String name="",fn="",mn="",gender="",password="",usertype="",dob="";
				int voterid=0,votest=0;
				String query = "SELECT * FROM `registration`";     
				Connection con=null;//for connection
				Statement st = null;//for query execution
				ResultSet rs = null;//to get row by row result from DB
				try
				{
					Class.forName("com.mysql.jdbc.Driver");//load driver
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebm","root","");
					st = con.createStatement();//create statement
					rs = st.executeQuery(query);//getting result
					while(rs.next())
					{	
				if(i==rs.getInt("voterId")){
						name=rs.getString("UserName");
						fn=rs.getString("FatherName");
						mn=rs.getString("MotherName");
						gender=rs.getString("Gender");
						password= rs.getString("Password");
						voterid=rs.getInt("voterId");
						usertype=rs.getString("UserType");
						dob=rs.getString("DOB");
						votest=rs.getInt("voteStatus");
						f=1;
				}
						
					}
				}
				catch(Exception ex)
				{
					System.out.println("Exception : " +ex.getMessage());
				}
		if(f==1){
			if(votest==0){
		JOptionPane.showMessageDialog(this,"Voter ID: "+voterid+"\n"+"Name: "+name+"\n"+"Password: "+password+"\n"+"Father's Name: "+fn+"\n"+"Mother's Name: "+mn+"\n"+"Date Of Birth: "+dob+"\n"+"UserType : "+usertype+"\n"+"Gender : "+gender+"\n"+"Vote? "+"Not Yet");}
		else {
			JOptionPane.showMessageDialog(this,"Voter ID: "+voterid+"\n"+"Name: "+name+"\n"+"Password: "+password+"\n"+"Father's Name: "+fn+"\n"+"Mother's Name: "+mn+"\n"+"Date Of Birth: "+dob+"\n"+"UserType : "+usertype+"\n"+"Gender : "+gender+"\n"+"Vote? "+"Yes,Done!");
		}
		}
		else if(f==0){JOptionPane.showMessageDialog(this,"No Information Found for Voter ID "+i);}
		}
		else {JOptionPane.showMessageDialog(this,"OOPS!!! Empty Search Field");}
	}
	}
	
}