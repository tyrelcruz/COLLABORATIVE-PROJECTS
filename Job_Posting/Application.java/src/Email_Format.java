import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Email_Format {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Email_Format window = new Email_Format();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Email_Format() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1026, 645);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//BUTTON ====================================================================================

		JButton Return = new JButton("");
		Return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Applicant_Process ApplicantProcess=  new Applicant_Process();
				ApplicantProcess.ApplicantProcess.setVisible(true);
				frame.dispose();
				
				
			}
		});
		Return.setIcon(new ImageIcon("/Users/luiz/Downloads/BACKGROUND 2 BUTTON (1) 1.24.19 PM/RETURN.png"));
		Return.setBounds(883, 86, 122, 44);
		frame.getContentPane().add(Return);
		
		//BACKGROUND ====================================================================================

		JLabel Background = new JLabel("");
		Background.setIcon(new ImageIcon("/Users/luiz/Downloads/EMAIL FORMAT.png"));
		Background.setBounds(0, 0, 1026, 617);
		frame.getContentPane().add(Background);
	}
}