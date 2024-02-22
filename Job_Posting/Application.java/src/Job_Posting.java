import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;


public class Job_Posting {

	public JFrame JobPosting;
	private JTextField PosCode;
	public JTextField JobTitle;
	private JTextField Responsibilities;
	private JTextField Salary;
	private JTable table_1;
	private JScrollPane scrollPane;
    private JTable dash_table;
	private JTable App_table;
	DefaultTableModel model;

    
    private static String FILE_PATH = "/Users/luiz/Library/Mobile Documents/com~apple~TextEdit/Documents/Job Posting.txt";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Executive_Dash ExecutiveDash = new Executive_Dash();
					Applicant_Process ApplicantProcess = new Applicant_Process();
					Job_Posting window = new Job_Posting(ExecutiveDash.getTable(), ApplicantProcess.getTable());
					window.JobPosting.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//METHODS ====================================================================================

	//Loads saved data from txt file to Jtable
    public void loadDataFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    model.addRow(data);
                    DefaultTableModel otherModel = (DefaultTableModel) dash_table.getModel(); 
                    DefaultTableModel otherModel1 = (DefaultTableModel) App_table.getModel();
                    otherModel.addRow(data);
                    otherModel1.addRow(data);
                }
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Saving data from Jtable to txt file
    public void saveDataToFile() {
        try {
            File file = new File(FILE_PATH);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    bw.write(model.getValueAt(i, j).toString());
                    if (j < model.getColumnCount() - 1) {
                        bw.write(",");
                    }
                }
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   // ====================================================================================

	/**
	 * Create the application.
	 */
	public Job_Posting(JTable dash_table,JTable App_table) {
        this.dash_table = dash_table;
        this.App_table = App_table;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JobPosting = new JFrame();
		JobPosting.setBounds(100, 100, 1026, 645);
		JobPosting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JobPosting.setLocationRelativeTo(null);
		JobPosting.getContentPane().setLayout(null);
		
		//TEXT FIELDS ====================================================================================
		
		//Position Code
		PosCode = new JTextField();
		PosCode.setBounds(562, 228, 169, 47);
		JobPosting.getContentPane().add(PosCode);
		PosCode.setColumns(10);
		
		//Job Title
		JobTitle = new JTextField();
		JobTitle.setBounds(562, 360, 169, 47);
		JobPosting.getContentPane().add(JobTitle);
		JobTitle.setColumns(10);
		
		//Responsibilites
		Responsibilities = new JTextField();
		Responsibilities.setBounds(812, 228, 169, 47);
		JobPosting.getContentPane().add(Responsibilities);
		Responsibilities.setColumns(10);
		
		//Salary
		Salary = new JTextField();
		Salary.setBounds(812, 360, 169, 47);
		Salary.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				String SalaryNum = Salary.getText();

				//get length of string
				int length = SalaryNum.length();
				
				char c = e.getKeyChar();
				
				//check for numbers 0-9
				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
					
					if(length<6) {
						//Editable 
						Salary.setEditable(true);
					
					} else {
						//Not editable if lenghth is more than 6
						Salary.setEditable(false);
					}
					
				} else if (e.getExtendedKeyCode()== KeyEvent.VK_BACK_SPACE || e.getExtendedKeyCode()== KeyEvent.VK_DELETE) {
					
					Salary.setEditable(true);
					
				} else {
					
					Salary.setEditable(false);
					JOptionPane.showMessageDialog(null,"Only Input Numbers from 0 to 9");

				}						
			}

		});
		JobPosting.getContentPane().add(Salary);
		Salary.setColumns(10);
		
		
		//Scroll Panel ====================================================================================

		//Add JScrollPane to show values in table
		scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 161, 464, 434);
		scrollPane.addMouseListener(new MouseAdapter() {	
			@Override			public void mouseClicked(MouseEvent e) {
				
				int i = table_1.getSelectedRow();
				PosCode.setText(model.getValueAt(i, 0).toString());
				JobTitle.setText(model.getValueAt(i, 1).toString());
				Responsibilities.setText(model.getValueAt(i, 2).toString());
				Salary.setText(model.getValueAt(i, 3).toString());
			}
		});
		JobPosting.getContentPane().add(scrollPane);
		
		//TABLE ====================================================================================
		
		table_1 = new JTable();
		table_1.setShowGrid(true);
		table_1.setShowHorizontalLines(true);
		table_1.setGridColor(Color.black);  
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int i = table_1.getSelectedRow();
				PosCode.setText(model.getValueAt(i,0).toString());
				JobTitle.setText(model.getValueAt(i,1).toString());
				Responsibilities.setText(model.getValueAt(i,2).toString());
				Salary.setText(model.getValueAt(i,3).toString());

			}
		});
		model = new DefaultTableModel();
		Object[] column = {"       Position Code","          Job Title", "    Responsibilities", "            Salary"};
		final Object[] row = new Object[4];
		model.setColumnIdentifiers(column);
		table_1.setModel(model);
		scrollPane.setViewportView(table_1);
		
		//BUTTONS ====================================================================================
						
        // Load data from file
        loadDataFromFile();
		
		//ADD BUTTON
		JButton Add = new JButton("");
		Add.setIcon(new ImageIcon("/Users/luiz/Downloads/BACKGROUND 2 BUTTON/ADD.png"));
		Add.setBounds(646, 454, 117, 40);
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							   	    
        
	         if (PosCode.getText().equals("")|| JobTitle.getText().equals("")|| Responsibilities.getText().equals("")|| Salary.getText().equals(""))
					
				{
					JOptionPane.showMessageDialog(null,"Please Complete All Fields");
					
				} else {
					
				row[0] = PosCode.getText();
				row[1] = JobTitle.getText();
				row[2] = Responsibilities.getText();
				row[3] = Salary.getText();
				model.addRow(row);
				PosCode.setText("");
				JobTitle.setText("");
				Responsibilities.setText("");
				Salary.setText("");
				
				//Add row to dash_table
                DefaultTableModel otherModel = (DefaultTableModel) dash_table.getModel();
                DefaultTableModel otherModel1 = (DefaultTableModel) App_table.getModel();
                otherModel.addRow(row);
                otherModel1.addRow(row);

				
                 // Save data to txt file
                saveDataToFile();
                
				}
			} 

		});
		JobPosting.getContentPane().add(Add);
		
		//UPDATE BUTTON
		JButton Update = new JButton("");
		Update.setIcon(new ImageIcon("/Users/luiz/Downloads/BACKGROUND 2 BUTTON/UPDATE.png"));
		Update.setBounds(805, 454, 117, 40);
		Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					int i = table_1.getSelectedRow();
					
					if(i>=0) {
						model.setValueAt(PosCode.getText(), i, 0);
						model.setValueAt(JobTitle.getText(), i, 1);
						model.setValueAt(Responsibilities.getText(), i, 2);
						model.setValueAt(Salary.getText(), i, 3);
						
						JOptionPane.showMessageDialog(null,"Updated Successfully!");
						
						PosCode.setText("");
						JobTitle.setText("");
						Responsibilities.setText("");
						Salary.setText("");
						
					    
                        DefaultTableModel otherModel = (DefaultTableModel) dash_table.getModel();
                        otherModel.setValueAt(PosCode.getText(), i, 0);
                        otherModel.setValueAt(JobTitle.getText(), i, 1);
                        otherModel.setValueAt(Responsibilities.getText(), i, 2);
                        otherModel.setValueAt(Salary.getText(), i, 3);
                        
                        DefaultTableModel otherModel1 = (DefaultTableModel) App_table.getModel();
                        otherModel1.setValueAt(PosCode.getText(), i, 0);
                        otherModel1.setValueAt(JobTitle.getText(), i, 1);
                        otherModel1.setValueAt(Responsibilities.getText(), i, 2);
                        otherModel1.setValueAt(Salary.getText(), i, 3);
						
                        // Save data to txt file
                        saveDataToFile();
                        
					} else {
					
						JOptionPane.showMessageDialog(null,"Select A Row To Update");
					}
			}
		});
		JobPosting.getContentPane().add(Update);
		
		//DELETE BUTTON
		JButton Delete = new JButton("");
		Delete.setIcon(new ImageIcon("/Users/luiz/Downloads/BACKGROUND 2 BUTTON/DELETE.png"));
		Delete.setBounds(646, 528, 117, 40);
		Delete.setBackground(Color.RED);
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i=table_1.getSelectedRow();
				if(i>=0) {
				model.removeRow(i);
				JOptionPane.showMessageDialog(null,"Deleted Successfully!");
				PosCode.setText("");
				JobTitle.setText("");
				Responsibilities.setText("");
				Salary.setText("");
				
                // Delete (dash_table)
                DefaultTableModel otherModel = (DefaultTableModel) dash_table.getModel();
                DefaultTableModel otherModel1 = (DefaultTableModel) App_table.getModel();
                otherModel.removeRow(i);
                otherModel1.removeRow(i);


                // Save data to file
                saveDataToFile();
				
				} else {
					
				JOptionPane.showMessageDialog(null,"Select A Row To Delete");
				
				}
			}
		});
		JobPosting.getContentPane().add(Delete);
		
		//CLEAR BUTTON
		JButton Clear = new JButton("");
		Clear.setIcon(new ImageIcon("/Users/luiz/Downloads/BACKGROUND 2 BUTTON/CLEAR.png"));
		Clear.setBounds(805, 528, 117, 40);
		Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PosCode.setText("");
				JobTitle.setText("");
				Responsibilities.setText("");
				Salary.setText("");
			}
		});
		JobPosting.getContentPane().add(Clear);
		
		//RETURN BUTTON
		JButton btn_Return = new JButton("");
		btn_Return.setBounds(881, 70, 119, 42);
		btn_Return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Executive_Dash ExecutiveDash = new Executive_Dash();
				ExecutiveDash.ExecutiveDash.setVisible(true);
				JobPosting.dispose();
				
				 // Save data to file
                saveDataToFile();
                			
			}
		});
		btn_Return.setIcon(new ImageIcon("/Users/luiz/Downloads/RETURN.png"));
		JobPosting.getContentPane().add(btn_Return);
		
	
		//BACKGROUND====================================================================================

		JLabel lblJobPosting_BG = new JLabel("");
		lblJobPosting_BG.setBounds(0, 0, 1026, 617);
		lblJobPosting_BG.setIcon(new ImageIcon("/Users/luiz/Downloads/JOB POSTING (1).png"));
		JobPosting.getContentPane().add(lblJobPosting_BG);
					

	}
}