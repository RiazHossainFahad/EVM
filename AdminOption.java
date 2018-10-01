import java.lang.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminOption extends JFrame implements ActionListener{
	private JButton confirmButton,resultButton,resetButton,mainmenuButton,backButton,createNewButton;
	private JPanel panel;
	private int voterid;
	
	
	public AdminOption(int v){
		super("Election Voting Management");
				this.setSize(800,400);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				this.voterid=v;
				
				panel=new JPanel();
				panel.setBackground(Color.BLACK);
				panel.setLayout(null);
				
				confirmButton=new JButton("Vote");
				confirmButton.setBounds(300,30,150,40);
				confirmButton.setBackground(Color.GREEN);
				confirmButton.setFont(new Font("ROMAN_BASELINE",Font.BOLD,20));
				confirmButton.addActionListener(this);
				panel.add(confirmButton);
				
				resultButton=new JButton("Result");
				resultButton.setBounds(300,100,150,40);
				resultButton.setBackground(Color.PINK);
				resultButton.setFont(new Font("ROMAN_BASELINE",Font.BOLD,20));
				resultButton.addActionListener(this);
				panel.add(resultButton);
				
				resetButton=new JButton("Reset");
				resetButton.setBounds(300,170,150,40);
				resetButton.setBackground(Color.RED);
				resetButton.setFont(new Font("ROMAN_BASELINE",Font.BOLD,20));
				resetButton.addActionListener(this);
				panel.add(resetButton);
				
				mainmenuButton=new JButton("HomePage");
				mainmenuButton.setBounds(300,240,150,40);
				mainmenuButton.setBackground(Color.YELLOW);
				mainmenuButton.setFont(new Font("ROMAN_BASELINE",Font.BOLD,20));
				mainmenuButton.addActionListener(this);
				panel.add(mainmenuButton);
				
				backButton=new JButton("Logout");
				backButton.setBounds(300,310,150,40);
				backButton.setBackground(Color.CYAN);
				backButton.setFont(new Font("ROMAN_BASELINE",Font.BOLD,20));
				backButton.addActionListener(this);
				panel.add(backButton);
				
				this.add(panel);
		
		
	}
	public void actionPerformed(ActionEvent ae){
		String elementText=ae.getActionCommand();
		
		if(elementText.equals(confirmButton.getText())){
			Fvote fv=new Fvote(this.voterid);
			fv.setVisible(true);
			this.setVisible(false);	
			}
		
		else if(elementText.equals(resultButton.getText())){
			int check=this.checkVoteStatus();
			
			if(check==1){
			ResultTable rt=new ResultTable();
			rt.setVisible(true);
			}
			else if(check==0){JOptionPane.showMessageDialog(this,"You have to vote First,Otherwise Result will Not shown to You");}
		}
		
		else if(elementText.equals(resetButton.getText())){
			this.updaetVoteStatus();
			this.updateVoteResult();
			this.resetComplete();
		}
		else if(elementText.equals(mainmenuButton.getText())){
			Welcome w3=new Welcome();
			w3.setVisible(true);
			this.setVisible(false);
		}
		
		else if(elementText.equals(backButton.getText())){
			Vote vo=new Vote();
			vo.setVisible(true);
			this.setVisible(false);
		}
	}
	
	public int checkVoteStatus(){
		int i=0;
		String queryCheck="SELECT `voteStatus` FROM `registration` WHERE `voterId`='"+this.voterid+"'";
		try{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebm","root","");
			Statement st = con.createStatement();//create statement
			ResultSet rs = st.executeQuery(queryCheck);//getting result					
			while(rs.next())
			{
			 i=rs.getInt("voteStatus");
			}	
				return i;
		}
		catch(Exception e){return 0;}
	}
	
	public void updaetVoteStatus(){
		String queryUpdateVoteStatus="UPDATE `registration` SET `voteStatus`=0";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebm","root","");
			Statement st = con.createStatement();
			st.execute(queryUpdateVoteStatus);
			con.close();
			st.close();
		}
		catch(Exception e){}
	}
	
	public void updateVoteResult(){
		String queryUpdateVote="UPDATE `vote` SET `awl`=0,`bnp`=0,`jp`=0";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebm","root","");
			Statement st = con.createStatement();
			st.execute(queryUpdateVote);
			con.close();
			st.close();
		}
		catch(Exception e){}
	}
	
	public void resetComplete(){
		JOptionPane.showMessageDialog(this,"Reset Completed");
	}
	
}