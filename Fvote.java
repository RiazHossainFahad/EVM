import java.lang.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Fvote extends JFrame implements MouseListener,ActionListener{
		private JLabel uvote,imgAWL,imgBNP,imgJatiyaParty;
		private JCheckBox AWLeague,BNP,JatiyaParty;
		private ImageIcon image1,image2,image3;
		protected ImageIcon image4;
		protected JCheckBox newlogo;
		private JButton confirmButton,backButton,mainmenuButton,createButton;
		protected ButtonGroup checkBoxGroup;
		protected JPanel panel;
		private int voterid,awlv,bnpv,jpv;
		
		public Fvote(){}
		
		public Fvote(int v){
				super("Election Voting Management");
				this.setSize(800,400);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				this.voterid=v;
				
				panel=new JPanel();
				panel.setBackground(Color.BLACK);
				panel.setLayout(null);
				
				uvote=new JLabel("Admin VOTE ");
				uvote.setBounds(250,10,200,30);
				uvote.setBackground(Color.BLACK);
				uvote.setForeground(Color.BLUE);
				uvote.setOpaque(true);
				uvote.setFont(new Font("ROMAN_BASELINE",Font.BOLD,30));
				panel.add(uvote);
				
				AWLeague=new JCheckBox("Awami-League");
				AWLeague.setBounds(100,80,150,30);
				AWLeague.setBackground(Color.black);
				AWLeague.setOpaque(true);
				AWLeague.setForeground(Color.CYAN);
				AWLeague.setFont(new Font("ROMAN_BASELINE",Font.BOLD,15));
				panel.add(AWLeague);
				
				image1=new ImageIcon("Boat_AL.jpg");
				imgAWL=new JLabel(image1);
				imgAWL.setBounds(250,80,50,50);
				imgAWL.setBackground(Color.black);
				imgAWL.setOpaque(true);
				panel.add(imgAWL);
				
				BNP=new JCheckBox("BD Nationalist Party");
				BNP.setBounds(100,150,180,30);
				BNP.setBackground(Color.black);
				BNP.setOpaque(true);
				BNP.setForeground(Color.GREEN);
				BNP.setFont(new Font("ROMAN_BASELINE",Font.BOLD,15));
				panel.add(BNP);
				
				image2=new ImageIcon("election-symbol-bnp.jpg");
				imgBNP=new JLabel(image2);
				imgBNP.setBounds(280,150,50,50);
				panel.add(imgBNP);
				
				JatiyaParty=new JCheckBox("Jatiya Party");
				JatiyaParty.setBounds(100,210,150,30);
				JatiyaParty.setBackground(Color.black);
				JatiyaParty.setOpaque(true);
				JatiyaParty.setForeground(Color.GRAY);
				JatiyaParty.setFont(new Font("ROMAN_BASELINE",Font.BOLD,15));
				panel.add(JatiyaParty);
				
				image3=new ImageIcon("election-symbol-jatiya-party.jpg");
				imgJatiyaParty=new JLabel(image3);
				imgJatiyaParty.setBounds(250,210,50,50);
				panel.add(imgJatiyaParty);
				
				
				checkBoxGroup=new ButtonGroup();
				checkBoxGroup.add(AWLeague);
				checkBoxGroup.add(BNP);
				checkBoxGroup.add(JatiyaParty);
				
				
				confirmButton=new JButton("CONFIRM");
				confirmButton.setBounds(110,300,120,30);
				confirmButton.setBackground(Color.GREEN);
				confirmButton.setFont(new Font("ROMAN_BASELINE",Font.BOLD,15));
				confirmButton.addMouseListener(this);
				confirmButton.addActionListener(this);
				panel.add(confirmButton);
				
				backButton=new JButton("Back");
				backButton.setBounds(250,300,120,30);
				backButton.setBackground(Color.PINK);
				backButton.setFont(new Font("ROMAN_BASELINE",Font.BOLD,15));
				backButton.addMouseListener(this);
				backButton.addActionListener(this);
				panel.add(backButton);
				
				createButton=new JButton("Add New");
				createButton.setBounds(400,300,120,30);
				createButton.setBackground(Color.RED);
				createButton.setFont(new Font("ROMAN_BASELINE",Font.BOLD,15));
				createButton.addMouseListener(this);
				createButton.addActionListener(this);
				panel.add(createButton);
				
				this.add(panel);
		}
	public void mousePressed(MouseEvent me){}
	public void mouseClicked(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	
	public void actionPerformed(ActionEvent ae){
		
		String elementText=ae.getActionCommand();
		if(elementText.equals(confirmButton.getText())){
			this.countVote();
			if(AWLeague.isSelected()){
				int i=this.checkVoteStatus();
				if(i==0){
				awlv++;
				this.voteUpdate(awlv,bnpv,jpv);
				this.setVoteStatus();
				//this.backHome();
				}
				else{this.nextStep();}
				
			}
			
		else if(BNP.isSelected()){
				int i=this.checkVoteStatus();
				if(i==0){
				bnpv++;
				this.voteUpdate(awlv,bnpv,jpv);
				this.setVoteStatus();
				//this.backHome();
				}
				else {this.nextStep();}
				
			}
			
		else if(JatiyaParty.isSelected()){
				int i=this.checkVoteStatus();
				if(i==0){
				jpv++;
				this.voteUpdate(awlv,bnpv,jpv);
				this.setVoteStatus();
				//this.backHome();
				}
				else{this.nextStep();}
				
			}
		}
		
		else if(elementText.equals(backButton.getText())){
			AdminOption ao=new AdminOption(voterid);
			ao.setVisible(true);
			this.setVisible(false);
		}
		
		else if(elementText.equals(createButton.getText())){
			this.addLogo();
		}

	}
	
	public void voteUpdate(int awlv,int bnpv,int jpv){
		String query="UPDATE `vote` SET `awl`='"+awlv+"',`bnp`='"+bnpv+"',`jp`='"+jpv+"'";
				try
				   {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebm", "root", "");
					Statement stm = con.createStatement(); 
					stm.execute(query);
					stm.close();
					con.close();
					JOptionPane.showMessageDialog(this,"Your Vote is Completed,Thanks For Your Interest ");
							
				}
				catch(Exception ex)
				{
					System.out.println("Exception : " +ex.getMessage());
				}
		
	}
		
		public void countVote(){
		String query = "SELECT `awl`,`bnp`,`jp`FROM `vote`";     
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
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
}

		public void backHome(){
			
			Welcome w1=new Welcome();
			w1.setVisible(true);
			this.setVisible(false);
			
			
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
			// this.nextStep(i);
			}	
				return i;
		}
		catch(Exception e){return 0;}
	}
	
	public void nextStep(){
		JOptionPane.showMessageDialog(this,"Already,You have Done Your Vote.\n\tThank You");	
	}
	
	public void setVoteStatus(){
		
		String querySet="UPDATE `registration` SET `voteStatus`=1 where `voterId`='"+this.voterid+"'";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebm","root","");
			Statement st = con.createStatement();
			st.execute(querySet);
			con.close();
			st.close();
		}
		catch(Exception e){}
	}
	public void addLogo(){
		String logo_name;
		logo_name = JOptionPane.showInputDialog("Logo Name");
		
		String confirm=JOptionPane.showConfirmDialog(null,JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(confirm==JOptionPane.OK_OPTION){
		newlogo=new JCheckBox(logo_name);
		newlogo.setBounds(400,80,150,30);
		panel.add(newlogo);
		}
	}
	
}