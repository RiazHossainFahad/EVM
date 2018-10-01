import java.lang.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class DeleteInfo extends JFrame implements ActionListener{
		private JLabel deleteLabel;
		private JTextField deleteTF;
		private JButton deleteButton,backButton;
		private JPanel panel;
	public DeleteInfo(){
		super("Election Voting Management");
		this.setSize(800,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel=new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);
		
		deleteLabel=new JLabel("Enter Voter Id: ");
		deleteLabel.setBounds(80,100,150,30);
		deleteLabel.setForeground(Color.BLUE);
		deleteLabel.setFont(new Font("SERIF",Font.BOLD,20));
		panel.add(deleteLabel);
		
		deleteTF=new JTextField();
		deleteTF.setBounds(240,100,150,30);
		deleteTF.setFont(new Font("ROMANIA_BASELINE",Font.PLAIN,15));
		panel.add(deleteTF);
		
		deleteButton=new JButton("Delete");
		deleteButton.setBounds(140,200,100,40);
		deleteButton.setFont(new Font("SERIF",Font.BOLD,15));
		deleteButton.setBackground(Color.RED);
		deleteButton.addActionListener(this);
		panel.add(deleteButton);
		
		backButton=new JButton("Back");
		backButton.setBounds(260,200,100,40);
		backButton.setFont(new Font("SERIF",Font.BOLD,15));
		backButton.setBackground(Color.GREEN);
		backButton.addActionListener(this);
		panel.add(backButton);
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae){
		String actionText=ae.getActionCommand();
		if(actionText.equals(deleteButton.getText())){
			String s1=deleteTF.getText();
			try{
			if(s1==""){JOptionPane.showMessageDialog(this,"Empty Search Field ");}
			
			else{
			int i=Integer.parseInt(s1);
			this.getVoterID(i);
			}
			}
			catch(Exception e){}
			
		}
		else if(actionText.equals(backButton.getText())){
			UpdateDelete ud=new UpdateDelete();
			ud.setVisible(true);
			this.setVisible(false);
		}
	}
	
	public void getVoterID(int i){
		int v=0;
		String query="SELECT `voterId` from `registration`";
			try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ebm","root","");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next()){
				if(i==rs.getInt("voterId")){
				v=rs.getInt("voterId");
				}
			}
		if(v==0){JOptionPane.showMessageDialog(this,"Voter Id not Found ");}
		else {
			this.deleteID(v);
		}	
		}
		catch(Exception e){System.out.println(e.getMessage());}
		
	}
	
	public void deleteID(int v){
		String queryDelete="DELETE FROM `registration` WHERE `registration`.`voterId` = '"+v+"'";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ebm","root","");
			Statement st=con.createStatement();
			st.execute(queryDelete);
			JOptionPane.showMessageDialog(this,"Information For Voter ID:"+v+" is Deleted");
			con.close();
			st.close();
	}
	catch(Exception e){System.out.println(e.getMessage());}
	
}
}