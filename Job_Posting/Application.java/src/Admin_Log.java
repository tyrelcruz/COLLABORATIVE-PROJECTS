import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.Font;

public class Admin_Log extends JFrame {

	public JPanel AdminLog;
	public JTextField AdminUser;
	public JButton btn_Login;
	private JPasswordField passwordField;
	private JPasswordField AdminPass;
	private JButton BackButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Log frame = new Admin_Log();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Admin_Log() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1026, 645);
		AdminLog = new JPanel();
		AdminLog.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(AdminLog);
		setLocationRelativeTo(null);
		AdminLog.setLayout(null);
		
		//TEXT FIELDS ====================================================================================
		
		//Admin Username
		AdminUser = new JTextField();
		AdminUser.setBounds(64, 318, 257, 43);
		AdminLog.add(AdminUser);
		AdminUser.setColumns(10);
		
		
		JCheckBox showHideButton = new JCheckBox("Show Password");
		showHideButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(showHideButton.isSelected()) {
					AdminPass.setEchoChar((char)0);
					
				} else {
					
					AdminPass.setEchoChar('•');
				}
			}
		});
		showHideButton.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		showHideButton.setBounds(207, 394, 114, 23);
		AdminLog.add(showHideButton);
		
		//Admin Password
		AdminPass = new JPasswordField();
		AdminPass.setBounds(64, 424, 257, 43);
		AdminLog.add(AdminPass);
		

		
		//BUTTONS ====================================================================================
		
		
		//Admin Login Button
		btn_Login = new JButton("");
		btn_Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String username = AdminUser.getText();
				String password = AdminPass.getText();
				
			    boolean found = false;

			    
			      if (username.equals("NCProjectsAdmin") && password.equals("Admin2023")){
			    	  
						AdminUser.setText(null);
						AdminPass.setText(null);
						
			            JOptionPane.showMessageDialog(null, "Login Successfully!");
			         
			          
						Executive_Dash ExecutiveDash = new Executive_Dash();
						ExecutiveDash.ExecutiveDash.setVisible(true);
						dispose();			              
			          
			      } else if(username.isEmpty() && password.isEmpty()){
			    	  
			    	  JOptionPane.showMessageDialog(null, "Enter Username and Password!");
			    	 			          
			          
			      } else if(username.equals("NCProjectsAdmin") && !password.equals("Admin2023")){
			    	  
			    	  JOptionPane.showMessageDialog(null, "Invalid Password!");
			          
			          
			      } else if(!username.equals("NCProjectsAdmin") && password.equals("Admin2023")){
			    	  
			    	  JOptionPane.showMessageDialog(null, "Invalid Username!");
			    	  
			      } else if(!username.equals("NCProjectsAdmin") && !password.equals("Admin2023")){
			    	  
			    	  JOptionPane.showMessageDialog(null, "Invalid Username and Password!"); 
			          
			      }
						
			}
		});
		btn_Login.setIcon(new ImageIcon("/Users/luiz/Downloads/LOGIN.png"));
		btn_Login.setBounds(142, 501, 139, 40);
		AdminLog.add(btn_Login);
		
		//Back Button
		BackButton = new JButton("");
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				HomePage Home = new HomePage();
				Home.setVisible(true);
				dispose();
				
			}
		});
		BackButton.setIcon(new ImageIcon("/Users/luiz/Downloads/BACK (2).png"));
		BackButton.setBounds(142, 556, 139, 40);
		AdminLog.add(BackButton);
		
		
		//BACKGROUND====================================================================================

		JLabel lbl_AdminLogBG = new JLabel("");
		lbl_AdminLogBG.setIcon(new ImageIcon("C:\\Users\\STLLR\\COLLABORATIVE-PROJECTS\\Job_Posting\\Application.java\\images"));
		lbl_AdminLogBG.setBounds(0, 0, 1026, 617);
		AdminLog.add(lbl_AdminLogBG);
		
	}
}