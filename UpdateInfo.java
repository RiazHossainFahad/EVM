import java.lang.*;
import javax.swing.*;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class UpdateInfo extends JFrame implements ActionListener {
	private JLabel name,fatherName,motherName,ID,userType,gender,dateOfBirth,passwordLabel,registration,voterid,dateFormat,address;
	private JTextField nameTextField,fatherNameTF,motherNameTF,dobTextField,voteridTF,addressTF;
	private JPasswordField passwordPF,confirmPasswordPF;
	private JButton back,update;
	private JComboBox userTypeCombo,genderCombo;
	private JPanel panel;
	private int age,i;
	
	String nam,fn,mn,pass,g,ut,dob,ad;
	
	public UpdateInfo(){
		
				super("Election Voting Management");
				this.setSize(800,400);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				panel=new JPanel();
				panel.setBackground(Color.black);
				panel.setLayout(null);
				
				registration=new JLabel("Update Information");
				registration.setForeground(Color.BLUE);
				registration.setBounds(300,10,250,30);
				registration.setFont(new Font("SERIF",Font.BOLD,25));
				panel.add(registration);
				
				
				voterid=new JLabel("Voter Id: ");
				voterid.setBounds(10,40,100,25);
				voterid.setForeground(Color.red);
				voterid.setFont(new Font("ROMANIA_BASELINE",Font.BOLD,15));
				panel.add(voterid);
				
				voteridTF=new JTextField();
				voteridTF.setBounds(130,40,130,25);
				voteridTF.setFont(new Font("ROMANIA_BASELINE",Font.PLAIN,15));
				panel.add(voteridTF);
				
				name=new JLabel("Name: ");
				name.setBounds(10,90,130,25);
				name.setForeground(Color.red);
				name.setFont(new Font("ROMANIA_BASELINE",Font.BOLD,15));
				panel.add(name);
		
				nameTextField=new JTextField();
				nameTextField.setBounds(140,90,130,25);
				nameTextField.setFont(new Font("ROMANIA_BASELINE",Font.PLAIN,15));
				panel.add(nameTextField);
				
				fatherName=new JLabel("Father's Name: ");
				fatherName.setBounds(10,130,130,25);
				fatherName.setForeground(Color.red);
				fatherName.setFont(new Font("ROMANIA_BASELINE",Font.BOLD,15));
				panel.add(fatherName);
				
				fatherNameTF=new JTextField();
				fatherNameTF.setBounds(140,130,130,25);
				fatherNameTF.setFont(new Font("ROMANIA_BASELINE",Font.PLAIN,15));
				panel.add(fatherNameTF);
				
				motherName=new JLabel("Mother's Name:");
				motherName.setBounds(10,180,130,25);
				motherName.setForeground(Color.red);
				motherName.setFont(new Font("ROMANIA_BASELINE",Font.BOLD,15));
				panel.add(motherName);
				
				motherNameTF=new JTextField();
				motherNameTF.setBounds(140,180,130,25);
				motherNameTF.setFont(new Font("ROMANIA_BASELINE",Font.PLAIN,15));
				panel.add(motherNameTF);
				
				passwordLabel=new JLabel("Password: ");
				passwordLabel.setBounds(10,215,130,25);
				passwordLabel.setForeground(Color.red);
				passwordLabel.setFont(new Font("ROMANIA_BASELINE",Font.BOLD,15));
				panel.add(passwordLabel);
				
				passwordPF=new JPasswordField();
				passwordPF.setBounds(140,215,130,25);
				panel.add(passwordPF);
				
				gender=new JLabel("Gender : ");
				gender.setBounds(500,50,100,25);
				gender.setFont(new Font("ROMANIA_BASELINE",Font.BOLD,15));
				gender.setForeground(Color.red);
				panel.add(gender);
				
				
				String []s={"Male","Female"};
				genderCombo=new JComboBox(s);
				genderCombo.setBounds(620,50,110,25);
				genderCombo.setFont(new Font("ROMANIA_BASELINE",Font.BOLD,13));
				genderCombo.setBackground(Color.YELLOW);
				panel.add(genderCombo);
				
				
				userType=new JLabel("User Type : ");
				userType.setBounds(500,95,110,25);
				userType.setForeground(Color.RED);
				userType.setFont(new Font("ROMANIA_BASELINE",Font.BOLD,15));
				panel.add(userType);
				
				String []userTypeString={"Admin","User"};
				userTypeCombo=new JComboBox(userTypeString);
				userTypeCombo.setBounds(610,95,110,25);
				userTypeCombo.setBackground(Color.YELLOW);
				userTypeCombo.setFont(new Font("ROMANIA_BASELINE",Font.BOLD,13));
				panel.add(userTypeCombo);
				
				dateOfBirth= new JLabel("Date of Birth:");
				dateOfBirth.setBounds(500,140,110,25);
				dateOfBirth.setForeground(Color.RED);
				dateOfBirth.setFont(new Font("ROMANIA_BASELINE",Font.BOLD,15));
				panel.add(dateOfBirth);
				
				dobTextField=new JTextField("DD-MM-YYYY");
				dobTextField.setBounds(620,140,110,25);
				dobTextField.setFont(new Font("ROMANIA_BASELINE",Font.PLAIN,15));
				panel.add(dobTextField);
				
				address=new JLabel("Address: ");
				address.setBounds(500,185,110,25);
				address.setForeground(Color.RED);
				address.setFont(new Font("ROMANIA_BASELINE",Font.BOLD,18));
				panel.add(address);
				
				addressTF=new JTextField();
				addressTF.setBounds(620,185,140,25);
				addressTF.setFont(new Font("ROMANIA_BASELINE",Font.PLAIN,15));
				panel.add(addressTF);
				
				update=new JButton("Update");
				update.setBounds(300,310,100,30);
				update.setFont(new Font("SERIF",Font.BOLD,15));
				update.setForeground(Color.BLACK);
				update.setBackground(Color.GREEN);
				update.addActionListener(this);
				panel.add(update);
				
				back=new JButton("Back");
				back.setBounds(420,310,100,30);
				back.setFont(new Font("SERIF",Font.BOLD,15));
				back.setForeground(Color.BLACK);
				back.setBackground(Color.red);
				back.addActionListener(this);
				panel.add(back);
				
				this.add(panel);
	}
	public void actionPerformed(ActionEvent ae){
		String actionText=ae.getActionCommand();
		
		if(actionText.equals(update.getText())){
			String s1=voteridTF.getText();
			try{
				i=Integer.parseInt(s1);
				this.checkInDatabase(i);
			}
			catch(Exception e){}
			
		}
		else if(actionText.equals(back.getText())){
			UpdateDelete UD=new UpdateDelete();
			UD.setVisible(true);
			this.setVisible(false);
		}
		
	}
	public void checkInDatabase(int i){
		int flag=0;
		String query="SELECT * From `registration`";
		Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ebm","root","");
			st=con.createStatement();
			st = con.createStatement();//create statement
			rs = st.executeQuery(query);//getting result					
			while(rs.next()){
				if(i==rs.getInt("voterId")){
					this.nam=rs.getString("UserName");
					this.fn=rs.getString("FatherName");
					this.mn=rs.getString("MotherName");
					this.pass=rs.getString("Password");
					this.g=rs.getString("Gender");
					this.ut=rs.getString("UserType");
					this.dob=rs.getString("DOB");
					this.ad=rs.getString("Address");
					this.updateInformation(i);
					flag=1;
					
				}
			}
			if(flag==0){
			JOptionPane.showMessageDialog(this,"Wrong Voter Id,Enter Correctly ");}
	}
	catch(Exception e){}
	}
	
	public void updateInformation(int i){
		int flag=0;
		if(!("").equals(nameTextField.getText())){
			nam=nameTextField.getText();
		}
		if(!("").equals(fatherNameTF.getText())){
			fn=fatherNameTF.getText();
		}
		if(!("").equals(motherNameTF.getText())){
			mn=motherNameTF.getText();
		}
		if(!("").equals(passwordPF.getText())){
			pass=passwordPF.getText();
		}
		if(!("").equals(addressTF.getText())){
			ad=addressTF.getText();
		}
		this.g=genderCombo.getSelectedItem().toString();
		this.ut=userTypeCombo.getSelectedItem().toString();
		if(!("DD-MM-YYYY").equals(dobTextField.getText())){
			flag=1;
			this.calculateAge();
			if(this.age<17){
				if(this.age==0)
					JOptionPane.showMessageDialog(this,"Date Of Birth Must Be in 'dd-MM-yyyy' Format");
				else if(age>0){JOptionPane.showMessageDialog(this,"Age is Below 18");}
				else{JOptionPane.showMessageDialog(this,"Wrong Format!!!\nDate Of Birth Must Be in 'dd-MM-yyyy' Format");}
			}
			else{
				dob=dobTextField.getText();
				this.insertUpdatedDataToDatabase();	
			}
		}
		if(flag==0){
			this.insertUpdatedDataToDatabase();
		}
		
		
}
	public void insertUpdatedDataToDatabase(){
			
		String queryUpdate="UPDATE `registration` SET `UserName`='"+nam+"',`FatherName`='"+fn+"',`MotherName`='"+mn+"',`Address`='"+ad+"',`Password`='"+pass+"',`Gender`='"+g+"',`UserType`='"+ut+"',`DOB`='"+dob+"' where `voterId`='"+i+"'";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebm","root","");
			Statement st = con.createStatement();//create statement
			st.execute(queryUpdate);//getting result	
			con.close();
			st.close();
			JOptionPane.showMessageDialog(this,"Updated Info For Voter ID: "+i+"\n\n"+"Name: "+nam+"\n"+"Father's Name: "+fn+"\n"+"Mother's Name: "+mn+"\n"+"Password: "+pass+"\n"+"UserType : "+ut+"\n"+"Gender : "+g+"\n"+"Date Of Birth: "+dob+"\n"+"Address: "+ad+"\n");
			UpdateDelete up2=new UpdateDelete();
			up2.setVisible(true);
			this.setVisible(false);
	
	}
	catch(Exception e){}
	}


	public void calculateAge(){
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
		try{
		Date cAge=(Date) format.parse(dobTextField.getText());
		this.age=this.getAge(cAge);
		}
		catch(Exception e){}
	}
	
	public int getAge(Date  cAge){
		Calendar calAge=Calendar.getInstance();
		calAge.setTime(cAge);
		
		Calendar currentDate=Calendar.getInstance();
		currentDate.setTime(new Date());
		
		return currentDate.get(Calendar.YEAR)-calAge.get(Calendar.YEAR);
		
	}
	
}