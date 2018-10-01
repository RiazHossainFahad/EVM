import java.lang.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ResultTable extends JFrame implements ActionListener{
	
	private JTable myTable;
	private JScrollPane tableScrollPane;
	private JButton exitButton;
	private JPanel panel;
	private int awlv,bnpv,jpv;
	
	public ResultTable(){
		
		super("Result Table");
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel=new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);
		
		this.setVOte();
		
		Object[] []row={{"Total Vote",new Integer(awlv),new Integer(bnpv),new Integer(jpv)}};
		String []col={" ","Awami_league","BNP","Jatiya-Party"};
		
		myTable = new JTable(row,col);
		tableScrollPane = new JScrollPane(myTable);
		tableScrollPane.setBounds(30,50,350,50);
		myTable.setBackground(Color.BLACK);
		myTable.setForeground(Color.white);
		panel.add(tableScrollPane);
		
		exitButton=new JButton("Back");
		exitButton.setBounds(150,200,100,30);
		exitButton.setBackground(Color.GREEN);
		exitButton.addActionListener(this);
		panel.add(exitButton);
		
		this.add(panel);
		
	}
	
	public void actionPerformed(ActionEvent ae){
		String et=ae.getActionCommand();
		if(et.equals(exitButton.getText())){
			this.setVisible(false);
		}
	}
	
	public void setVOte(){
		String query="SELECT * from `vote`";
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
				this.awlv=rs.getInt("awl");
				this.bnpv=rs.getInt("bnp");
				this.jpv=rs.getInt("jp");
					}
				}
			catch(Exception e){}
	
	}
}