import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JCheckBox;

public class Applicant_Log extends Accounts{

	public JFrame ApplicantLog;
	private JTextField ApplicantUser;
	private JPasswordField ApplicantPassword;
	
	private ArrayList<Accounts> accountsList = new ArrayList<Accounts>();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Applicant_Log window = new Applicant_Log(null,null);
					window.ApplicantLog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @return 
	 */
	
	//Initializes an instance with the provided username and password parameters 
	public Applicant_Log(String username, String password) {
		super(username,password);
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ApplicantLog = new JFrame();
		ApplicantLog.setBounds(100, 100, 1026, 645);
		ApplicantLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ApplicantLog.setLocationRelativeTo(null);
		ApplicantLog.getContentPane().setLayout(null);
		
		//TEXT FIELDS ====================================================================================
		
		//Applicant Username
		ApplicantUser = new JTextField();
		ApplicantUser.setBounds(63, 284, 294, 48);
		ApplicantLog.getContentPane().add(ApplicantUser);
		ApplicantUser.setColumns(10);
		
		//Show and Hide Password
		JCheckBox showHidePass = new JCheckBox("Show Password");
		showHidePass.setBounds(236, 370, 114, 23);
		showHidePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(showHidePass.isSelected()) {
					
					ApplicantPassword.setEchoChar((char)0);
					
				} else {
					
					ApplicantPassword.setEchoChar('•');
					
				}
			}
		});
		showHidePass.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		ApplicantLog.getContentPane().add(showHidePass);
		
		//Applicant Password
		ApplicantPassword = new JPasswordField();
		ApplicantPassword.setBounds(63, 395, 294, 48);
		ApplicantLog.getContentPane().add(ApplicantPassword);
		
		
		//BUTTONS ====================================================================================

		//Login Button
		JButton btn_ApplicantLog = new JButton("");
		btn_ApplicantLog.setBounds(225, 455, 139, 40);
		btn_ApplicantLog.setIcon(new ImageIcon("/Users/luiz/Downloads/LOGIN.png"));
		btn_ApplicantLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String UsernameInput = ApplicantUser.getText();
				String PasswordInput = ApplicantPassword.getText();
				
				
			    try {
			      FileReader fileReader = new FileReader("/Users/luiz/Library/Mobile Documents/com~apple~TextEdit/Documents/Registered_Accounts.txt");
			      BufferedReader bufferedReader = new BufferedReader(fileReader);
			      
			      String line;
			      while ((line = bufferedReader.readLine()) != null) {
			        if (line.trim().isEmpty()) {
			          continue;
			        }
			        String[] split = line.split(",");
			        if (split.length >= 5) {
			          String name = split[0];
			          String username = split[1];
			          String password = split[2];
			          String email = split[3];
			          String phone = split[4];
			          Accounts account = new Accounts(username, password);
			          accountsList.add(account);
			        }
			      }
			      
			      bufferedReader.close();
			      fileReader.close();
			      
			    } catch (IOException | ArrayIndexOutOfBoundsException ex) {
			    	
			        ex.getMessage(); 
			        
			    }
			    
			    boolean found = false;
			    
			    for (int i = 0; i < accountsList.size(); i++) {
			      String username = accountsList.get(i).getUsername();
			      String password = accountsList.get(i).getPassword();

			      if (UsernameInput.equals(username) && PasswordInput.equals(password)){
			          JOptionPane.showMessageDialog(null, "Login Successfully!");
			          found = true;
			          
			          Applicant_Process ApplicantProcess = new  Applicant_Process();
			          ApplicantProcess.ApplicantProcess.setVisible(true);
			          ApplicantLog.dispose();			              
			          break;
			          
			      } else if(UsernameInput.isEmpty() && PasswordInput.isEmpty()){
			    	  
			    	  JOptionPane.showMessageDialog(null, "Invalid Username and Password!");  
			          found = true;
			          break;
			          
			      } else if(UsernameInput.equals(username) && !PasswordInput.equals(password)){
			    	  
			    	  JOptionPane.showMessageDialog(null, "Invalid Password!");
			          found = true;
			          break;
			          
			      } else if(!UsernameInput.equals(username) && PasswordInput.equals(password)){
			    	  
			    	  JOptionPane.showMessageDialog(null, "Invalid Username!");
			          found = true;
			          break;
			          
			      }
			    }
			    
			    if(!found){
			    	JOptionPane.showMessageDialog(null, "Invalid Username and Password!");
			    	
			    }
			}
		});
		ApplicantLog.getContentPane().add(btn_ApplicantLog);
	
		
		//Back Button
		JButton BackButton = new JButton("");
		BackButton.setBounds(73, 455, 139, 40);
		BackButton.setIcon(new ImageIcon("/Users/luiz/Downloads/BACK (2).png"));
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				HomePage Home = new HomePage();
				Home.setVisible(true);
				ApplicantLog.dispose();
			}
		});
		ApplicantLog.getContentPane().add(BackButton);
		
		//Register Button
		JButton btn_ApplicantReg = new JButton("REGISTER");
		btn_ApplicantReg.setBounds(161, 582, 94, 29);
		btn_ApplicantReg.setBackground(Color.LIGHT_GRAY);
		btn_ApplicantReg.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btn_ApplicantReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Applicant_Registration Registration = new Applicant_Registration();
				Registration.setVisible(true);
				ApplicantLog.dispose();
				
			}
		});
		ApplicantLog.getContentPane().add(btn_ApplicantReg);
		
		
		//BACKGROUND ====================================================================================

		JLabel ApplicantPass = new JLabel("New label");
		ApplicantPass.setBounds(0, 0, 1026, 617);
		ApplicantPass.setIcon(new ImageIcon("/Users/luiz/Downloads/APPLICANT LOGIN (1).png"));
		ApplicantLog.getContentPane().add(ApplicantPass);
			

	}
}