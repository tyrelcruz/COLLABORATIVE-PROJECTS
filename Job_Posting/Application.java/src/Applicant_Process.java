import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;


import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;



public class Applicant_Process {

	public JFrame ApplicantProcess;
	public JLayeredPane layeredPane;
	public JPanel panel1;
	public JPanel panel2;
	public JPanel panel3;
	
	//View available positions
    private DefaultTableModel model;
	private JTable App_table;
    private DefaultTableModel model2;

    
    //Apply to a position
    private JTextField searchField;
    private TableRowSorter<DefaultTableModel> sorter;
    protected Executive_Dash ExecutiveDash;
    private JTable table;

    
    private static final String FILE_PATH = "/Users/luiz/Library/Mobile Documents/com~apple~TextEdit/Documents/Job Posting.txt";
  
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 Applicant_Process window = new Applicant_Process();
	                    window.ApplicantProcess.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//METHODS ====================================================================================

	//Loads Data from txt file to Available Positions Table
    public void loadDataFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    model.addRow(data);
                }
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Loads data for Search table
    public void loadDataFromFile2() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length > 0) {
                        model2.addRow(data);
                    }
                }
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Retrieves the data from the model and returns it as a list of string arrays
    private List<String[]> getDataFromModel() {
        List<String[]> data = new ArrayList<>();
        for (int i = 0; i < model2.getRowCount(); i++) {
            String[] rowData = new String[model2.getColumnCount()];
            for (int j = 0; j < model2.getColumnCount(); j++) {
                rowData[j] = (String) model2.getValueAt(i, j);
            }
            data.add(rowData);
        }
        return data;
    }
    
    
    //Row filter for search box
    private void applyRowFilter(String searchQuery) {
        RowFilter<DefaultTableModel, Object> rf = null;
        try {
            rf = RowFilter.regexFilter(searchQuery, 0);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
      
   // ====================================================================================
    
	//Panel Switch
	public void switchPanels(JPanel panel) {
		
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
				
	} 	

	/**
	 * Create the application.
	 */
	public Applicant_Process() {
		initialize();
		
		//View Postitions
        loadDataFromFile();
        
        //Apply
        loadDataFromFile2();
        getDataFromModel();
        ExecutiveDash = new Executive_Dash();
       
	}
    public JTable getTable() {
        return App_table;
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ApplicantProcess = new JFrame();
		ApplicantProcess.setBounds(100, 100, 1026, 645);
		ApplicantProcess.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ApplicantProcess.setLocationRelativeTo(null);
		ApplicantProcess.getContentPane().setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(222, 123, 798, 494);
		ApplicantProcess.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		
		//PANEL 1 ====================================================================================
		
		//HOME PAGE
		panel1 = new JPanel();
		layeredPane.add(panel1, "name_105864026786417");
		panel1.setLayout(null);
		
		JLabel Panel1BG = new JLabel("");
		Panel1BG.setIcon(new ImageIcon("/Users/luiz/Downloads/AVAILABE POSITIO  (3).png"));
		Panel1BG.setBounds(0, 0, 798, 494);
		panel1.add(Panel1BG);
		
		//PANEL 2 ====================================================================================

		//VIEW AVAILABLE POSITINS
		panel2 = new JPanel();
		layeredPane.add(panel2, "name_55815229348416");
		panel2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();	
		scrollPane.setBounds(34, 91, 738, 374);
		panel2.add(scrollPane);
		
        model = new DefaultTableModel();
		
		//Available Positions table
		App_table = new JTable();
		App_table.setModel(model);
		App_table.setShowGrid(true);
		App_table.setShowHorizontalLines(true);
		App_table.setGridColor(Color.black);
		App_table.setEnabled(false);
		App_table.setFocusable(false);
		App_table.setRowSelectionAllowed(false);
		App_table.getTableHeader().setReorderingAllowed(false);
		App_table.getTableHeader().setResizingAllowed(false);
	        Object[] column = {"                 Position Code","                       Job Title", "                  Responsibilities", "                         Salary"};
	        model.setColumnIdentifiers(column);
	        final Object[]row = new Object[4];
	        model.setColumnIdentifiers(column);
		scrollPane.setViewportView(App_table);
		
		//BACKGROUND	
		JLabel Panel1_BG = new JLabel("");
		Panel1_BG.setIcon(new ImageIcon("/Users/luiz/Downloads/AVAILABE POSITIO .png"));
		Panel1_BG.setBounds(0, 0, 798, 494);
		panel2.add(Panel1_BG);
		
		
		//PANEL 3 ====================================================================================

		//APPLY TO DESIRED POSITION
		panel3 = new JPanel();
		layeredPane.add(panel3, "name_55815229348417");
		panel3.setLayout(null);
		
		//Search Box
		searchField = new JTextField();
		searchField.setBounds(54, 103, 642, 46);
		panel3.add(searchField);
		searchField.setColumns(10);
		
		//Search Button
		JButton btn_Search = new JButton("");
		btn_Search.setIcon(new ImageIcon("/Users/luiz/Downloads/Untitled design (5).png"));
		btn_Search.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String searchQuery = searchField.getText().trim();
		        if (searchQuery.isEmpty()) {
		            // Clear the row filter
		            sorter.setRowFilter(null);
		        } else {
		            applyRowFilter(searchQuery);
		        }
		    }
		});

		btn_Search.setBounds(697, 103, 55, 46);
		panel3.add(btn_Search);
	    
		
		//Search Table
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(61, 181, 681, 177);
        panel3.add(scrollPane_1);
        
        table = new JTable();
        scrollPane_1.setViewportView(table);
        model2 = new DefaultTableModel();
        table.setShowGrid(false);
        table.setShowHorizontalLines(true);
        table.setGridColor(Color.black);
        table.setModel(model2);
        table.setEnabled(true);
        table.setFocusable(false);
        table.setRowSelectionAllowed(true);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setDefaultEditor(Object.class, null);
        Object[] column1 = { "Position Code" };
        model2.setColumnIdentifiers(column1);
        
        sorter = new TableRowSorter<>(model2);
        table.setRowSorter(sorter);

	        
        //Apply Button
        JButton btn_Apply = new JButton("");
        btn_Apply.setIcon(new ImageIcon("/Users/luiz/Downloads/SUBMIT (1).png"));
        btn_Apply.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
 
        	 int selectedRow = table.getSelectedRow();
        	 if (selectedRow == -1) {
        		 JOptionPane.showMessageDialog(ApplicantProcess, "Please select a position code to apply.", "No Selection", JOptionPane.WARNING_MESSAGE);
              } else {
        		 String positionCode = (String) table.getValueAt(selectedRow, 0);
        		 ExecutiveDash.incrementApplicants(positionCode);
        		 ExecutiveDash.saveDataToFile();
        		     JOptionPane.showMessageDialog(ApplicantProcess, "You have successfully applied for position code: " + positionCode, "Application Submitted", JOptionPane.INFORMATION_MESSAGE);
        		     Email_Format frame = new Email_Format();
        		     frame.frame.setVisible(true);
        		     ApplicantProcess.dispose();
        		 }
      
        	}
        });
        btn_Apply.setBounds(347, 363, 121, 46);
        panel3.add(btn_Apply);
        
        
		//Background	
        JLabel Background = new JLabel("");
        Background.setIcon(new ImageIcon("/Users/luiz/Downloads/AVAILABE POSITIO  (2).png"));
        Background.setBounds(0, 0, 798, 494);
        panel3.add(Background);
		
		
		//SWITCH PANEL BUTTONS ====================================================================================
        
		//HOME PAGE
        JButton HomePage = new JButton("");
        HomePage.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
				switchPanels(panel1);

        	}
        });
        HomePage.setIcon(new ImageIcon("/Users/luiz/Downloads/BACKGROUND 2 BUTTON (2)/Home Page.png"));
		HomePage.setBounds(21, 232, 189, 53);
		ApplicantProcess.getContentPane().add(HomePage);
		
		//VIEW AVAILABLE POSITINS
		JButton btnViewPositions = new JButton("");
		btnViewPositions.setIcon(new ImageIcon("/Users/luiz/Downloads/BACKGROUND 2 BUTTON (2)/VAP.png"));
		btnViewPositions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switchPanels(panel2);
				
			}
		});
		btnViewPositions.setBounds(21, 335, 189, 53);
		ApplicantProcess.getContentPane().add(btnViewPositions);
		
		
		//APPLY TO DESIRED POSITION
		JButton btnApply = new JButton("");
		btnApply.setIcon(new ImageIcon("/Users/luiz/Downloads/BACKGROUND 2 BUTTON (2)/ADP.png"));
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switchPanels(panel3);
				
			}
		});
		btnApply.setBounds(21, 432, 189, 53);
		ApplicantProcess.getContentPane().add(btnApply);
		
		//BUTTON ====================================================================================
		
		//Log out Button
		JButton LOGOUTBUTTON = new JButton("");
		LOGOUTBUTTON.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Applicant_Log ApplicantLog = new Applicant_Log(null, null);
				ApplicantLog.ApplicantLog.setVisible(true);
				ApplicantProcess.dispose();
				
			}
		});
		LOGOUTBUTTON.setIcon(new ImageIcon("/Users/luiz/Downloads/LOGOUT (1).png"));
		LOGOUTBUTTON.setBounds(880, 71, 116, 40);
		ApplicantProcess.getContentPane().add(LOGOUTBUTTON);
		
		
		//BACKGROUND ====================================================================================

		JLabel ApplicantProcess_BG = new JLabel("");
		ApplicantProcess_BG.setIcon(new ImageIcon("/Users/luiz/Downloads/APPLICANT PROCESS (1).png"));
		ApplicantProcess_BG.setBounds(0, 0, 1026, 617);
		ApplicantProcess.getContentPane().add(ApplicantProcess_BG);
				
		
	}
}