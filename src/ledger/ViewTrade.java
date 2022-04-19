/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledger;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import datahandler.DataHandler;

/**
 *
 * @author 564951
 * Atanas Chelibashki
 */
public class ViewTrade implements ActionListener{
    
	//Instances
    public JFrame mainFrame,viewFrame;
    public JButton btnImportFile,btnBack,btnExit;
    public JLabel headerLabel,statusLabel;
    public JPanel controlPanel;
    public String introText;
    private final String FILEPATH="D:\\data.txt";
    private JTable table1;
    private JTextArea textArea;
	//Constructor
    public ViewTrade(JFrame vFrame, JFrame mainFrame) {
            viewFrame=vFrame;
            this.mainFrame=mainFrame;
            createPanel();

    }//end constructor

    //Generate ViewTrade panel
    private void createPanel() {


       viewFrame.setLayout(new GridLayout(3,1));
       
       headerLabel=new JLabel("",JLabel.CENTER);
       statusLabel=new JLabel("",JLabel.CENTER);
       statusLabel.setSize(350,100);
       



       controlPanel=new JPanel();
       controlPanel.setLayout(new FlowLayout());	
       viewFrame.add(headerLabel);
       viewFrame.add(controlPanel);
       viewFrame.add(statusLabel);


        btnBack = new JButton("Back");
        btnBack.addActionListener(this);

        btnExit=new JButton("Exit");
        btnExit.addActionListener(this);

        controlPanel.add(btnBack);
        controlPanel.add(btnExit);

        //declare column names
        String[] cNames= {"Ticker","Name","Price","Amount","Date"};
        
        //initialise file
        File file=new File(FILEPATH);
            /*
                try to read from file with Buffered Reader 
                set table model
                create objects to store information from file
                initialise data table 
        
            */
           try {
                   BufferedReader br=new BufferedReader(new FileReader(file));
                   DefaultTableModel model=(DefaultTableModel)table1.getModel();
                   model.setColumnIdentifiers(cNames);
                   Object[] data= br.lines().toArray();
                   
                   //populate data table
                   for(int i=0;i<data.length;i++) {
                           String line=data[i].toString().trim();
                           String[] dataRow=line.split(",");
                           model.addRow(dataRow);
                   }//end for loop

           }//end try
           catch (Exception ex) {
                  System.out.println(ex);
           }//end catch
        
           
        //set viewFrame properties
        viewFrame.setResizable(false);  
        viewFrame.add(controlPanel);
        viewFrame.setSize(500,300);
        viewFrame.setLocationRelativeTo(null);
        viewFrame.setVisible(false);
        
	}//end createPanel
	
	// method to display content of arraylist in data table
	private void displayContent(ArrayList<String> content) {
		textArea.setText(null);
		for(String element : content) {
			textArea.append(element+"\n");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnBack) {
			//textArea.setText(introText);
			viewFrame.setVisible(false);
			mainFrame.setVisible(true);
                        java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                        new DataTable().setVisible(false);
            }
        });
		}
                else if(e.getSource()==btnImportFile)
		{
			ArrayList<String> content=new ArrayList<>();
			DataHandler.loadDataFromFile(FILEPATH,content);
			displayContent(content);
			System.out.println("You pressed read button");
			
		}
		else if(e.getSource()==btnExit){
                    viewFrame.setVisible(false);
                    //System.exit(0);
                }
		
	}//end action listener
	
}//end class
