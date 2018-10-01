import java.lang.*;
import javax.swing.*;
import java.util.Date;
import java.util.Calendar;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.sql.*;


public class Registration extends JFrame implements MouseListener,ActionListener{
	private JLabel name,fatherName,motherName,ID,userType,gender,dateOfBirth,passwordLabel,confirmPassword,registration,dateFormat,address;
	private JTextField nameTextField,fatherNameTF,motherNameTF,dobTextField,addressTF;
	private JPasswordField passwordPF,confirmPasswordPF;
	private JButton submit,back,updateDelete;
	private JComboBox userTypeCombo,genderCombo;
	private JPanel panel;
	private int age;
	
	public Registration(){
		
				super("Election Voting Management");
				this.setSize(800,400);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				panel=new JPanel();
				panel.setBackground(Color.black);
				panel.setLayout(null);
				
				registration=new JLabel("Registration");
				registration.setForeground(Color.BLUE);
				registration.setBounds(300,8,160,30);
				registration.setBackground(Color.WHITE);
				registration.setFont(new Font("SERIF",Font.BOLD,25));
				panel.add(registration);
				
				name=new JLabel("Name: ");
				name.setBounds(20,40,140,25);
				name.setForeground(Color.red);
				name.setFont(new Font("ROMANIA_BASELINE",Font.BOLD,18));
				panel.add(name);
				
				nameTextField=new JTextField();
				nameTextField.setBounds(160,40,130,25);
				nameTextField.setFont(new Font("ROMANIA_BASELINE",Font.PLAIN,15));
				panel.add(nameTextField);
				
				
				fatherName=new JLabel("Father's Name: ");
				fatherName.setBounds(20,95,140,25);
				fatherName.setForeground(Color.red);
				fatherName.setFont(new Font("ROMANIA_BASELINE",Font.BOLD,18));
				panel.add(fatherName);
				
				fatherNameTF=new JTextField();
				fatherNameTF.setBounds(160,95,130,25);
				fatherNameTF.setFont(new Font("ROMANIA_BASELINE",Font.PLAIN,15));
				panel.add(fatherNameTF);
				
				motherName=new JLabel("Mother's Name:");
				motherName.setBounds(20,140,140,25);
				motherName.setForeground(Color.red);
				motherName.setFont(new Font("ROMANIA_BASELINE",Font.BOLD,18));
				panel.add(motherName);
				
				motherNameTF=new JTextField();
				motherNameTF.setBounds(160,140,130,25);
				motherNameTF.setFont(new Font("ROMANIA_BASELINE",Font.PLAIN,15));
				panel.add(motherNameTF);
				
				passwordLabel=new JLabel("Password");
				passwordLabel.setBounds(20,185,140,25);
				passwordLabel.setForeground(Color.red);
				passwordLabel.setFont(new Font("ROMANIA_BASELINE",Font.BOLD,18));
				panel.add(passwordLabel);
				
				passwordPF=new JPasswordField();
				passwordPF.setBounds(160,185,130,25);
				panel.add(passwordPF);
				
				gender=new JLabel("Gender: ");
				gender.setBounds(500,50,120,25);
				gender.setFont(new Font("ROMANIA_BASELINE",Font.BOLD,18));
				gender.setForeground(Color.red);
				panel.add(gender);
				
				
				String []s={"Male","Female"};
				genderCombo=new JComboBox(s);
				genderCombo.setBounds(600,50,100,25);
				genderCombo.setFont(new Font("ROMANIA_BASELINE",Font.BOLD,13));
				genderCombo.setBackground(Color.YELLOW);
				panel.add(genderCombo);
				
				
				userType=new JLabel("User Type : ");
				userType.setBounds(500,100,120,25);
				userType.setForeground(Color.RED);
				userType.setFont(new Font("ROMANIA_BASELINE",Font.BOLD,18));
				panel.add(userType);
				
				String []userTypeString={"Admin","User"};
				userTypeCombo=new JComboBox(userTypeString);
				userTypeCombo.setBounds(620,100,120,25);
				userTypeCombo.setBackground(Color.YELLOW);
				userTypeCombo.setFont(new Font("ROMANIA_BASELINE",Font.BOLD,13));
				panel.add(userTypeCombo);
				
				dateOfBirth= new JLabel("Date of Birth:");
				dateOfBirth.setBounds(490,155,120,25);
				dateOfBirth.setForeground(Color.RED);
				dateOfBirth.setFont(new Font("ROMANIA_BASELINE",Font.BOLD,18));
				panel.add(dateOfBirth);
				
				dobTextField=new JTextField("DD-MM-YYYY");
				dobTextField.setBounds(620,155,110,25);
				dobTextField.setFont(new Font("ROMANIA_BASELINE",Font.PLAIN,15));
				panel.add(dobTextField);
				
				address=new JLabel("Address: ");
				address.setBounds(490,200,100,25);
				address.setForeground(Color.RED);
				address.setFont(new Font("ROMANIA_BASELINE",Font.BOLD,18));
				panel.add(address);
				
				addressTF=new JTextField();
				addressTF.setBounds(600,200,140,25);
				addressTF.setFont(new Font("ROMANIA_BASELINE",Font.PLAIN,15));
				panel.add(addressTF);
				
				submit=new JButton("Submit");
				submit.setBounds(200,310,100,30);
				submit.setFont(new Font("SERIF",Font.BOLD,15));
				submit.setForeground(Color.BLACK);
				submit.setBackground(Color.GREEN);
				submit.addMouseListener(this);
				submit.addActionListener(this);
				panel.add(submit);
				
				back=new JButton("Back");
				back.setBounds(320,310,100,30);
				back.setFont(new Font("SERIF",Font.BOLD,15));
				back.setForeground(Color.BLACK);
				back.setBackground(Color.red);
				back.addMouseListener(this);
				back.addActionListener(this);
				panel.add(back);
				
				updateDelete=new JButton("Update/Delete");
				updateDelete.setBounds(440,310,180,30);
				updateDelete.setFont(new Font("SERIF",Font.BOLD,15));
				updateDelete.setForeground(Color.BLACK);
				updateDelete.setBackground(Color.YELLOW);
				updateDelete.addMouseListener(this);
				updateDelete.addActionListener(this);
				panel.add(updateDelete);
				
				this.add(panel);
	}
	
	public void mouseClicked(MouseEvent me){}
	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	public void mouseEntered(MouseEvent me){
		
		if(me.getSource().equals(submit)){
			submit.setBackground(Color.YELLOW);
		}
		
		else if(me.getSource().equals(back)){
			back.setBackground(Color.yellow);
		}
		else if(me.getSource().equals(updateDelete)){
			updateDelete.setBackground(Color.red);
		}
	}
	public void mouseExited(MouseEvent me){
		if(me.getSource().equals(submit)){
			submit.setBackground(Color.GREEN);
		}
		
		else if(me.getSource().equals(back)){
			back.setBackground(Color.red);
		}
		else if(me.getSource().equals(updateDelete)){
			updateDelete.setBackground(Color.yellow);
		}
		
	}
	
	public void actionPerformed(ActionEvent ae){
		
		String elementText=ae.getActionCommand();
		
		if(elementText.equals(submit.getText())){
			this.calculateAge();
		if(this.age>17){
			this.insertInfo();
		}
		else{
			if(this.age==0){
			JOptionPane.showMessageDialog(this,"Date Of Birth Must Be in 'dd-MM-yyyy' Format");
			}
			else{JOptionPane.showMessageDialog(this,"Age is Below 18.");}
		}
		}
		else if(elementText.equals(back.getText())){
			Welcome w1=new Welcome();
			w1.setVisible(true);
			this.setVisible(false);
		}
		else if(elementText.equals(updateDelete.getText())){
			UpdateDelete aud=new UpdateDelete();
			aud.setVisible(true);
			this.setVisible(false);
		}
		else{}
	}
	
	public void insertInfo(){
		if(nameTextField.getText().equals("")||fatherNameTF.getText().equals("")||motherNameTF.getText().equals("")||passwordPF.getText().equals("")||dobTextField.getText().equals("")||addressTF.getText().equals("")){
			JOptionPane.showMessageDialog(this,"Empty Is not Possible.Fill all the Portion :)");
		}
		
		else{
		String s1=userTypeCombo.getSelectedItem().toString();
		String s2=genderCombo.getSelectedItem().toString();
		int i=this.getVoterId();
		i++;
		String query = "INSERT INTO registration VALUES ('"+nameTextField.getText()+"','"+fatherNameTF.getText()+"','"+motherNameTF.getText()+"','"+s2+"','"+s1+"','"+ i +"','"+passwordPF.getText()+"','"+dobTextField.getText()+"','"+addressTF.getText()+"','"+0+"');";
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebm", "root", "");
			Statement stm = con.createStatement(); 
			stm.execute(query);
			this.showInfoInserted();
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
	}
	}
	
	public int getVoterId(){
		int v=0;
		String queryVoter="Select `voterId` from `registration`";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ebm","root","");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(queryVoter);
			while(rs.next()){
				v=rs.getInt("voterId");
			}
			return v;
		}
		catch(Exception e){System.out.println(e.getMessage());return 1;}
	}
	
	public void showInfoInserted(){
		String name="",fn="",mn="",gender="",password="",usertype="",dob="",ad="";
		int voterid=0;
		
		String query="SELECT * from `registration`";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ebm","root","");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next()){
						name=rs.getString("UserName");
						fn=rs.getString("FatherName");
						mn=rs.getString("MotherName");
						gender=rs.getString("Gender");
						password= rs.getString("Password");
						voterid=rs.getInt("voterId");
						usertype=rs.getString("UserType");
						dob=rs.getString("DOB");
						ad=rs.getString("Address");
				
			}
			JOptionPane.showMessageDialog(this,"Voter ID: "+voterid+"\n"+"Name: "+name+"\n"+"Father's Name: "+fn+"\n"+"Mother's Name: "+mn+"\n"+"Address: "+ad+"\n"+"password: "+password+"\n"+"UserType : "+usertype+"\n"+"Gender : "+gender+"\n");
			this.backToHome();
		}
		catch(Exception e){System.out.println(e.getMessage());}
		
	}
	
	public void backToHome(){
			Welcome w1=new Welcome();
			w1.setVisible(true);
			this.setVisible(false);
		
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