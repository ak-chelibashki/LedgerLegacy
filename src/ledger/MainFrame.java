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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import datahandler.DataHandler;
import java.awt.PopupMenu;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JOptionPane;

/**
 *
 * @author 564951
 * ak-chelibashki
 */
public class MainFrame extends JPanel implements ActionListener{


    /**
     * local instances
     */
    private JFrame mainFrame,viewFrame;
    private JButton btnAdd, btnView,btnClear,btnExit;
    private JTextField txtTicker,txtName,txtPrice,txtQuantity,txtDate;
    private final String FILEPATH="D:\\data.txt";

    /**
     * Class constructor
     * @param frame
     */
    public MainFrame(JFrame frame){
        mainFrame=frame;     
        createPanel();
    }//end MainFrame

    /**
     * Method to generate frame content
     */
    public void createPanel(){

        /**
         * define labels within array
         * define local layout managers
         *  define local layout managers
         */

		GridLayout gl52=new GridLayout(5,2);
		GridLayout gl21=new GridLayout(2,1);		
		FlowLayout flow=new FlowLayout();
		
		//initialize panels and assign layout
		JPanel firstPanel=new JPanel();
		firstPanel.setLayout(gl52);
		JPanel secondPanel=new JPanel();
		secondPanel.setLayout(flow);
		
		//declare labels to be used for each text field
		JLabel lblTicker=new JLabel("Ticker: ");
		JLabel lblName=new JLabel("Name: ");
		JLabel lblPrice=new JLabel("Price: ");
		JLabel lblQuantity=new JLabel("Quantity: ");
		JLabel lblDate=new JLabel("Date: ");
		
		
		//declare text fields
		txtName=new JTextField("Name",15);
		txtName.setFocusable(true);
		
		txtTicker=new JTextField("Ticker",15);
		txtPrice=new JTextField("Price",15);
		txtQuantity=new JTextField("Quantity",15);
		txtDate=new JTextField("Date format : dd-mm-yyyy",10);
		
		//declare buttons and add action listener
		btnView=new JButton("View");
		btnView.addActionListener(this);

		btnAdd=new JButton("Add");
		btnAdd.addActionListener(this);
		
                
        btnExit=new JButton("Exit");
		btnExit.addActionListener(this);
                
                
        btnClear=new JButton("Clear");
		btnClear.addActionListener(this);
                
		//add components to panel 1		
		firstPanel.add(lblTicker);
		firstPanel.add(txtTicker);
		firstPanel.add(lblName);
		firstPanel.add(txtName);
		firstPanel.add(lblPrice);
		firstPanel.add(txtPrice);
		firstPanel.add(lblQuantity);
		firstPanel.add(txtQuantity);
		firstPanel.add(lblDate);
		firstPanel.add(txtDate);
		
		//add components to panel 2
		secondPanel.add(btnAdd);
		secondPanel.add(btnView);
        secondPanel.add(btnClear);
		secondPanel.add(btnExit);
                
		
		//set properties to addFrame
		mainFrame.setLayout(gl21);
		mainFrame.setResizable(false);
		mainFrame.add(firstPanel);
		mainFrame.add(secondPanel);
		mainFrame.setSize(500,300);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
    }//end createPanel

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame(new JFrame("Trade Ledger")).setVisible(true);
            }
        });
    }//end main

    /**
     * Button action events
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        final int MAX=5;
        if(e.getSource()==btnExit){
            mainFrame.setVisible(false);
            System.exit(0);     
        }//end if
        else if(e.getSource()==btnAdd) {
               try{
                    DataHandler.saveTrade(txtTicker.getText(),txtName.getText(),txtPrice.getText(),txtQuantity.getText(),txtDate.getText(),
                        FILEPATH);
                    
                     clear();
                }//end try
                catch(NullPointerException ea){
                    String error=ea.getMessage();
                    JOptionPane.showMessageDialog(null, error);
                 
                }//end catch
               catch(Exception ea){
                    String error=ea.getMessage();
                    JOptionPane.showMessageDialog(null, error);
               }//end catch
        }//end else if
        //clear form
        else if(e.getSource()==btnClear){
            clear();
        }//end else if
        //view saved data
        else if(e.getSource()==btnView) {
        	mainFrame.setVisible(false);
        	java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataTable().setVisible(true);
            }
        });
        }     
    }//end event listener

    /**
     * Method to clear all text fields
     */
    private void clear() {
    	txtTicker.setText("");
    	txtName.setText("");
    	txtPrice.setText("");
    	txtQuantity.setText("");
    	txtDate.setText("");
    }//end method
            
    
}//end class
