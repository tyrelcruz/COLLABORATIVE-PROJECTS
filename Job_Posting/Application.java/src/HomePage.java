import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {

	private JPanel Home;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
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
	public HomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1026, 645);
		Home = new JPanel();
		Home.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(Home);
		setLocationRelativeTo(null);
		Home.setLayout(null);
		
		//BUTTONS ====================================================================================
		
		//Applicant Button
		JButton btn_Applicant = new JButton("");
		btn_Applicant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Applicant_Log ApplicantLog = new Applicant_Log(null, null);
				ApplicantLog.ApplicantLog.setVisible(true);
				dispose();
			}
		});
		btn_Applicant.setIcon(new ImageIcon("/Users/luiz/Downloads/APPLICANT.png"));
		btn_Applicant.setBounds(26, 370, 194, 68);
		Home.add(btn_Applicant);
		
		//AdminButton
		JButton btn_Admin = new JButton("");
		btn_Admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Admin_Log AdminLog = new Admin_Log();
				AdminLog.setVisible(true);
				dispose();
				
			}
			
		});
		btn_Admin.setIcon(new ImageIcon("/Users/luiz/Downloads/ADMIN.png"));
		btn_Admin.setBounds(26, 464, 194, 68);
		Home.add(btn_Admin);
		
		//BACKGROUND====================================================================================
		
		JLabel lbl_HomeBG = new JLabel("");
		lbl_HomeBG.setIcon(new ImageIcon("C://Users//STLLR//COLLABORATIVE-PROJECTS//Job_Posting//Application.java//images"));
		lbl_HomeBG.setBounds(0, 0, 1920, 1080);
		Home.add(lbl_HomeBG);
		

	}
}