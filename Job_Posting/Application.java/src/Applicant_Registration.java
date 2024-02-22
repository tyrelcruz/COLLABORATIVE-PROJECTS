import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.Font;


public class Applicant_Registration extends JFrame {

	private JPanel Register;
	private JTextField Name;
	private JTextField User;
	private JTextField Email;
	private JTextField Phone;
	private JButton btn_Clear;
	private JPasswordField Pass;
	private JCheckBox ShowHidePass;
	
    private static final String FILE_PATH = "/Users/luiz/Library/Mobile Documents/com~apple~TextEdit/Documents/Registered_Accounts.txt";

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Applicant_Registration frame = new Applicant_Registration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//METHOD ====================================================================================

	private void registerAccount(String name, String username, String password, String email, String phone) {
	 
	    try {
	    	BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH,true));
	        writer.write( name  + ","  +  username + "," +  password + ","  + email + ","  + phone);
	        writer.newLine();
	        writer.close();

	        JOptionPane.showMessageDialog(null, "Successfully Registered");
	    } catch (IOException ex) {
	        JOptionPane.showMessageDialog(null, "Error occurred while saving the account details");
	        ex.printStackTrace();
	    }
	}
	// ====================================================================================

	/**
	 * Create the frame.
	 */
	public Applicant_Registration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1026, 645);
		Register = new JPanel();
		Register.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(Register);
		setLocationRelativeTo(null);
		Register.setLayout(null);
		
		//TEXT FIELDS ====================================================================================

		//Name (ONLY ACCEPTS ALPHABETICAL CHARACTERS)
		Name = new JTextField();
		Name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if(Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c)) {
					
					Name.setEditable(true);
					
				} else {
					
					Name.setEditable(false);


				}							
			}
		});
		Name.setBounds(353, 140, 313, 44);
		Register.add(Name);
		Name.setColumns(10);
		
		//User Name (DOES NOT ACCEPT SPACES)
		User = new JTextField();
		User.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				char c = e.getKeyChar();			
				
				if(Character.isLetter(c) || Character.isISOControl(c) || Character.isDigit(c)) {
					
					User.setEditable(true);
					
				} else {
					
					User.setEditable(false);

				}
			}
		});
		User.setColumns(10);
		User.setBounds(353, 196, 313, 44);
		Register.add(User);
		
		//Show and hide Password
		ShowHidePass = new JCheckBox("Show Password");
		ShowHidePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ShowHidePass.isSelected()) {
					
					Pass.setEchoChar((char)0);
					
				} else {
					
					Pass.setEchoChar('•');
					
				}
			}
		});
		ShowHidePass.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		ShowHidePass.setBounds(353, 299, 116, 23);
		Register.add(ShowHidePass);
		
		//Password
		Pass = new JPasswordField();
		Pass.setBounds(353, 258, 313, 44);
		Register.add(Pass);
			
		//Email
		Email = new JTextField();
		Email.setColumns(10);
		Email.setBounds(353, 336, 313, 44);
		Register.add(Email);
		
		//Phone Number (DOES NOT ACCEPT DIGITS MORE THAN 11)
		Phone = new JTextField();
		Phone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
							
				//get JTextField String
				String PhoneNum = Phone.getText();

				//get length of string
				int length = PhoneNum.length();
				
				char c = e.getKeyChar();
				
				//check for numbers 0-9
				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
					
					if(length<11) {
						//Editable 
						Phone.setEditable(true);
						
					
					} else {
						//Not editable if lenghth is more than 11
						Phone.setEditable(false);
						JOptionPane.showMessageDialog(null,"Only Input 11 Digits");

					}
					
				} else if (e.getExtendedKeyCode()== KeyEvent.VK_BACK_SPACE || e.getExtendedKeyCode()== KeyEvent.VK_DELETE) {
					
					Phone.setEditable(true);
					
				} else {
					
					Phone.setEditable(false);
					JOptionPane.showMessageDialog(null,"Only Input Numbers from 0 to 9");


				}						
			}
			
		});
		Phone.setColumns(10);
		Phone.setBounds(353, 392, 313, 44);
		Register.add(Phone);
		
		
		//BUTTONS ====================================================================================

		//Register Button
		JButton btn_Submit = new JButton("");
		btn_Submit.setIcon(new ImageIcon("/Users/luiz/Downloads/SUBMIT (1).png"));
		btn_Submit.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				

                if (!Name.getText().equals("") && !User.getText().equals("") && !Pass.getPassword().equals("") && !Email.getText().equals("") && !Phone.getText().equals("")) {

                    //Register the account and save details to a text file
                    String name = Name.getText();
                    String username = User.getText();
                    String password = Pass.getText();
                    String email = Email.getText();
                    String phone = Phone.getText();

                    // Validate email format
                    if (!email.matches(".+@gmail\\.com$")) {
                        JOptionPane.showMessageDialog(null, "Invalid email format. Email must be a valid Gmail address (e.g., example@gmail.com)");
                        return;
                    }

                    // Validate phone number format
                    if (!phone.matches("09\\d{9}")) {
                        JOptionPane.showMessageDialog(null, "Invalid phone number format. Phone number must start with '09' and have 11 digits");
                        return;
                    }

                    registerAccount(name, username, password, email, phone);

                    Name.setText("");
                    User.setText("");
                    Pass.setText("");
                    Email.setText("");
                    Phone.setText("");

                } else {

                    JOptionPane.showMessageDialog(null, "Fill out all the fields and try again");
                }
				
			}
		});
		btn_Submit.setBounds(550, 464, 116, 40);
		Register.add(btn_Submit);
		
		//Clear Button
		btn_Clear = new JButton("");
		btn_Clear.setIcon(new ImageIcon("/Users/luiz/Downloads/CLEAR (1).png"));
		btn_Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Name.setText("");
				User.setText("");
				Pass.setText("");
				Email.setText("");
				Phone.setText("");
				
			}
		});
		btn_Clear.setBounds(353, 464, 116, 40);
		Register.add(btn_Clear);
		
		
		//Back Button
		JButton btn_Back = new JButton("");
		btn_Back.setIcon(new ImageIcon("/Users/luiz/Downloads/BACK (2).png"));
		btn_Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Applicant_Log AppplicantLog = new Applicant_Log(null, null);
				AppplicantLog.ApplicantLog.setVisible(true);
				dispose();
				
			}
		});
		btn_Back.setBounds(441, 558, 139, 40);
		Register.add(btn_Back);
		

		//BACKGROUND ====================================================================================

		JLabel lbl_Background = new JLabel("");
		lbl_Background.setIcon(new ImageIcon("/Users/luiz/Downloads/APPLICANT REGISTRATION2.png"));
		lbl_Background.setBounds(0, 0, 1026, 617);
		Register.add(lbl_Background);
					
		
	}
}