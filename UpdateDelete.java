import java.lang.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateDelete extends JFrame implements ActionListener,MouseListener{
		private JButton updateButton,deleteButton,backButton,mainmenu;
		private JPanel panel;
	public UpdateDelete(){
		super("Election Voting Management");
		this.setSize(800,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel=new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);
		
		updateButton=new JButton("UpdateInfo");
		updateButton.setBounds(300,80,150,40);
		updateButton.setBackground(Color.GREEN);
		updateButton.setFont(new Font("SERIF",Font.BOLD,20));
		updateButton.addActionListener(this);
		updateButton.addMouseListener(this);
		panel.add(updateButton);
		
		deleteButton=new JButton("DeleteInfo");
		deleteButton.setBounds(300,150,150,40);
		deleteButton.setBackground(Color.RED);
		deleteButton.setFont(new Font("SERIF",Font.BOLD,20));
		deleteButton.addActionListener(this);
		deleteButton.addMouseListener(this);
		panel.add(deleteButton);
		
		backButton=new JButton("Back");
		backButton.setBounds(300,220,150,40);
		backButton.setBackground(Color.YELLOW);
		backButton.setFont(new Font("SERIF",Font.BOLD,20));
		backButton.addActionListener(this);
		backButton.addMouseListener(this);
		panel.add(backButton);
		
		mainmenu=new JButton("HomePage");
		mainmenu.setBounds(300,290,150,40);
		mainmenu.setBackground(Color.CYAN);
		mainmenu.setFont(new Font("SERIF",Font.BOLD,20));
		mainmenu.addActionListener(this);
		mainmenu.addMouseListener(this);
		panel.add(mainmenu);
		
		
		this.add(panel);
	}
	public void mouseClicked(MouseEvent me){}
	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	public void mouseEntered(MouseEvent me){
		
		if(me.getSource().equals(updateButton)){
			updateButton.setBackground(Color.pink);
		}
		
		else if(me.getSource().equals(deleteButton)){
			deleteButton.setBackground(Color.pink);
		}
		else if(me.getSource().equals(backButton)){
			backButton.setBackground(Color.pink);
		}
		else if(me.getSource().equals(mainmenu)){
			mainmenu.setBackground(Color.pink);
		}
	}
	public void mouseExited(MouseEvent me){
		if(me.getSource().equals(updateButton)){
			updateButton.setBackground(Color.GREEN);
		}
		
		else if(me.getSource().equals(deleteButton)){
			deleteButton.setBackground(Color.RED);
		}
		else if(me.getSource().equals(backButton)){
			backButton.setBackground(Color.YELLOW);
		}
		else if(me.getSource().equals(mainmenu)){
			mainmenu.setBackground(Color.CYAN);
		}
		
	}
	
	public void actionPerformed(ActionEvent ae){
		 String actionText=ae.getActionCommand();
		if(actionText.equals(updateButton.getText())){
			UpdateInfo ui=new UpdateInfo();
			ui.setVisible(true);
			this.setVisible(false);
		}
		else if(actionText.equals(deleteButton.getText())){
			DeleteInfo di=new DeleteInfo();
			di.setVisible(true);
			this.setVisible(false);
		}
		else if(actionText.equals(mainmenu.getText())) {
			Welcome w1=new Welcome();
			w1.setVisible(true);
			this.setVisible(false);
		}
		else if(actionText.equals(backButton.getText())){
			Registration AUD=new Registration();
			AUD.setVisible(true);
			this.setVisible(false);
		}
	}
	
}