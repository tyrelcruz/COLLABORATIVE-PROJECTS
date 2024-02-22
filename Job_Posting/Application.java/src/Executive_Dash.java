import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JPanel;

public class Executive_Dash {

    public JFrame ExecutiveDash;
    
    //Added Positions
    private DefaultTableModel model;
    private JTable dash_table;
    
    //Applicant Count
    private DefaultTableModel model2;
    private JPanel AppCount;
    
    private Map<String, Integer> applicantCounts = new HashMap<String, Integer>();
    private static final String FILE_PATH = "/Users/luiz/Library/Mobile Documents/com~apple~TextEdit/Documents/Job Posting.txt";
    private static final String FILE_PATH2 = "/Users/luiz/Library/Mobile Documents/com~apple~TextEdit/Documents/App_Count.txt";


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Executive_Dash window = new Executive_Dash();
                    window.ExecutiveDash.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // METHODS ====================================================================================
    

    //Loads data from the txt file to available positions table
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
            updateApplicantCountLabel(); // Update the applicant count labels
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Loads data from the txt file to Applicant Count 
    public void loadDataFromFile2() {
        try {
            File file = new File(FILE_PATH2);
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    String positionCode = data[0]; // Assuming position code is the first column
                    int count = Integer.parseInt(data[1]); // Assuming count is the second column

                    model2.addRow(data);
                    applicantCounts.put(positionCode, count); // Update the applicant count
                }
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     //Saves applicant count per positions in a txt file
     public void saveDataToFile() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH2);
            fw.write(""); // Clear file content

            for (Map.Entry<String, Integer> entry : applicantCounts.entrySet()) {
                String positionCode = entry.getKey();
                int count = entry.getValue();

                fw.write(positionCode + "," + count + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    // Updates applicant count per position in dashboard
    private void updateApplicantCountLabel() {
        AppCount.removeAll(); // Clear existing labels

        int y = 6;
        for (Map.Entry<String, Integer> entry : applicantCounts.entrySet()) {
            String positionCode = entry.getKey();
            int count = entry.getValue();

            JLabel label = new JLabel(positionCode + ": " + count + " applicant/s");
            label.setFont(new Font("Arial", Font.PLAIN, 12));
            label.setBounds(6, y, 382, 23);
            AppCount.add(label);

            y += 25;
        }

        AppCount.revalidate();
        AppCount.repaint();

        saveDataToFile(); // Save applicant count to file
    }
    
    //Increment of number of applicants per position whenever "submit" button is clicked
    public void incrementApplicants(String positionCode) {
        if (applicantCounts.containsKey(positionCode)) {
            int count = applicantCounts.get(positionCode);
            count++;
            applicantCounts.put(positionCode, count);
        } else {
            applicantCounts.put(positionCode, 1);
        }
        updateApplicantCountLabel();
    }

    // ====================================================================================

    /**
     * Create the application.
     */
    public Executive_Dash() {
        initialize();
        loadDataFromFile();
        loadDataFromFile2();
    }
    public JTable getTable() {
        return dash_table;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        ExecutiveDash = new JFrame();
        ExecutiveDash.setBounds(100, 100, 1026, 645);
        ExecutiveDash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ExecutiveDash.setLocationRelativeTo(null);
        ExecutiveDash.getContentPane().setLayout(null);

        
        // Tables ====================================================================================

	    // Added Positions
        model = new DefaultTableModel();
	    
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(37, 197, 484, 386);
        ExecutiveDash.getContentPane().add(scrollPane);
        
        dash_table = new JTable();
        scrollPane.setViewportView(dash_table);
        dash_table.setModel(model);       
        dash_table.setShowGrid(true);
        dash_table.setShowHorizontalLines(true);
        dash_table.setGridColor(Color.black);   
        dash_table.setEnabled(false);
        dash_table.setFocusable(false);
        dash_table.setRowSelectionAllowed(false);
        dash_table.getTableHeader().setReorderingAllowed(false);
        dash_table.getTableHeader().setResizingAllowed(false);
        dash_table.setFont(new Font("Arial", Font.PLAIN, 12));
        Object[] column = {"       Position Code","          Job Title", "    Responsibilities", "            Salary"};
        model.setColumnIdentifiers(column);
        final Object[] row = new Object[4];
        model.setColumnIdentifiers(column);      
        scrollPane.setViewportView(dash_table);

	    
	    // Applicant Count 
        model2 = new DefaultTableModel();

	    AppCount = new JPanel();
        AppCount.setBounds(589, 197, 400, 386);
        AppCount.setBackground(Color.WHITE);
        ExecutiveDash.getContentPane().add(AppCount);
        AppCount.setLayout(null);
        loadDataFromFile2();

        // BUTTONS ====================================================================================

        //Job Posting Button
        JButton btn_JobPosting = new JButton("");
        btn_JobPosting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Job_Posting JobPosting = new Job_Posting(dash_table, getTable());
                JobPosting.JobPosting.setVisible(true);
                ExecutiveDash.dispose();
            }
        });
        btn_JobPosting.setIcon(new ImageIcon("/Users/luiz/Downloads/JOB POSTING .png"));
        btn_JobPosting.setBackground(new Color(11, 20, 10));
        btn_JobPosting.setBounds(754, 67, 116, 40);
        ExecutiveDash.getContentPane().add(btn_JobPosting);

        //Log Out Button
        JButton btn_LogOut = new JButton("");
        btn_LogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Admin_Log AdminLog = new Admin_Log();
                AdminLog.setVisible(true);
                ExecutiveDash.dispose();
            }
        });
        btn_LogOut.setIcon(new ImageIcon("/Users/luiz/Downloads/LOGOUT (1).png"));
        btn_LogOut.setBounds(882, 67, 116, 40);
        ExecutiveDash.getContentPane().add(btn_LogOut);

        // BACKGROUND====================================================================================

        JLabel ExecutiveDash_BG = new JLabel("");
        ExecutiveDash_BG.setIcon(new ImageIcon("/Users/luiz/Downloads/EXECUTIVE DASH (1).png"));
        ExecutiveDash_BG.setBounds(0, 0, 1026, 617);
        ExecutiveDash.getContentPane().add(ExecutiveDash_BG);
    }
}