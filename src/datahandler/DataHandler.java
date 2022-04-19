package datahandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import ledger.DateValidatorUsingDateTimeFormatter;

/**
 * author
 * 564951
 * ak-chelibashki
 */
public class DataHandler {


    /**
     * Test method to display
     * user input to console
     */
	private static void viewData(String txtTicker,String txtName,String txtPrice,String txtQuantity,String txtDate) {
		
		//Display ledger record
		System.out.println("Ticker: "+txtTicker+"\nName: "+txtName+"\nPrice: "+txtPrice+"\nQuantity: "+txtQuantity+"\nDate: "+txtDate);	
	
	}//end method


    /**
     * Save user input to text file
     * @param txtTicker
     * @param txtName
     * @param txtPrice
     * @param txtQuantity
     * @param txtDate
     * @param fileName
     */
	public static void saveTrade(String txtTicker,String txtName,String txtPrice,String txtQuantity, String txtDate,String fileName)
	{
		File file= new File(fileName);
		PrintWriter pW=null;
		boolean test=false;
                String[] cNames= {"Ticker","Name","Price","Amount","Date"};

                try{       
                    //if result is false terminate the program
                    if(!validateAllData(txtTicker, txtName, txtPrice, txtQuantity, txtDate)){
                        System.out.println("Program terminating...");     
                    }
                    else{
                        //try to access file location
                        try
                        {
                            boolean fileEx=file.exists();
                            System.out.println("Does file exist? : "+fileEx);
                            //if file does not exist create one
                           if(fileEx==false)
                           {
                               
                               file.createNewFile();
                               pW=new PrintWriter(new FileOutputStream(fileName,true));
                               
                               
                               //append column names to the file if it doesnt already exists      
                               for(String line : cNames){
                                   pW.append(line+",");
                                   
                                }//end for/each
                               
                               
                               //indicate creation of new file
                                System.out.println("Creating new file...");
                                //clear PrintWriter and close it
                               pW.flush();
                               pW.close();
                           }//end if
                           
                           
                            //write to file
                           //write user input to txt file
                           pW=new PrintWriter (new FileOutputStream(fileName,true));                   
                           pW.append("\n"+txtTicker+","+txtName+","+txtPrice+","+txtQuantity+","+txtDate);
                           
                           
                           //clear PrintWriter and close it
                           pW.flush();
                           pW.close();
                        }//end try
                        catch (IOException ioex) {
                                //display error message
                                JOptionPane.showMessageDialog(null, "IOException Error");

                        }
                        finally
                        {
                                //empty writer and close the file
                                if(pW != null) {
                                        pW.flush();
                                        pW.close();
                                }//endIf
                                //display user input
                                viewData(txtTicker,txtName,txtPrice,txtQuantity,txtDate);
                        }//end final     
                    }//end else
                }//end try
            catch(Exception e){
                String error=e.getMessage();
                JOptionPane.showMessageDialog(null, error);
            }//end catch
	}//end saveTrade

    /**
     *Method to check there are no empty fields
     * when the user submits a new trade
     * @param txtTicker
     * @param txtName
     * @param txtPrice
     * @param txtQuantity
     * @param txtDate
     * @return
     */
    public static boolean validateAllData(String txtTicker,String txtName,String txtPrice,String txtQuantity, String txtDate ){
        //local variables to be used to test all fields
        boolean testTicker=true,testName=true,testPrice=true,testAmount=true,testDate=true,testAll=false;

            //execute all tests and assign results to boolean variables    
            testTicker=validateString(txtTicker,"Ticker");
            testName=validateString(txtName,"Name");
            testPrice=validateNumbers(txtPrice,"Price");
            testAmount=validateNumbers(txtQuantity,"Amount");
            testDate=validateDate(txtDate);
            
            if(testTicker && testName && testPrice && testAmount && testDate){
                testAll=true;
            }//end if
            
            System.out.println("TEST ALL : "+testAll);
            return testAll;
        }//end

    /**
     * Method to load user saved data
     * from text file to table
     */
	public static void loadDataFromFile(String filePath, ArrayList<String> content) {
		try {
			File file=new File(filePath);
			FileReader fReader=new FileReader(filePath);
			BufferedReader bReader=new BufferedReader(fReader);
			String line;
			while((line=bReader.readLine())!=null) {
				content.add(line);
			}
			fReader.close();
		}//end try
		catch(IOException e) {
			System.out.println("File operation failure!");
			System.out.println(e);
		}//end catch
	}//end method

    /**
     * Method implementing regex to validate string input
     * @param line
     * @param message
     * @return
     */
        public static boolean validateString(String line, String message){
            //local variables and instances
            boolean test=false,test2=false;
            Pattern charactersOnly=Pattern.compile("^([A-Za-z])+$");
            Matcher match1=charactersOnly.matcher(line);
            test=match1.find();
            test2=validateEmptyString(line);

            try{
                if( !test|| test2)
                JOptionPane.showMessageDialog(null,"Only characters accpeted ! "+"Test fail for : "+message);
             
            }//end try
            catch(Exception e){
                System.out.println("ERROR ! UNVALID STRING "+ e.getMessage());
            }//end catch
            return test;
        }//end method

    /**
     * Method to validate numeric values entered from the user
     */
        public static boolean validateNumbers(String line,String message){
            boolean test=false,test2=false;
            Pattern numbersOnly = Pattern.compile("[0-9]*\\.?[0-9]*");
            Matcher match1=numbersOnly.matcher(line);
            
            test=match1.find();
            test2=validateEmptyString(line);
            
            if(!test )
            {
                JOptionPane.showMessageDialog(null,"No numeric value found "+"Test Fail for : "+message);
            }
            if(test2){
                JOptionPane.showMessageDialog(null,"Empty input ! "+"Test Fail for : "+message);
            }
            return test;
        }//end method

    /**
     * Method to validate user string input
     * @param line
     * @return
     */
    public static boolean validateEmptyString(String line){
            boolean test=false;
            
            test=line.isEmpty();
            
            if(test)
                System.out.println("Is empty? "+test);
            else
                System.out.println("Is empty? "+test);
            

            return test;
        }//end method

    /**
     * Method to be tested
     * Save user input for new trade on txt file
     * @param text
     * @param text0
     * @param text1
     * @param text2
     * @param text3
     * @param FILEPATH
     * @param usrInput
     */
         public static void saveTrade(String text, String text0, String text1, String text2, String text3, URL FILEPATH, ArrayList<String> usrInput) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }//end


    /**
     * Method to be tested
     * Save user input for new trade on txt file
     * @param text
     * @param text0
     * @param text1
     * @param text2
     * @param text3
     * @param FILEPATH
     * @param usrInput
     */
    public static void saveTrade(String text, String text0, String text1, String text2, String text3, String FILEPATH, ArrayList<String> usrInput) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    /**
     * Interface to blueprint date validation through classes
     */
    public interface DateValidator{
        boolean isValid(String dateStr);
    }//end method

    /**
     * Method to validate user input for date
     * @param line
     * @return
     */
    public static boolean validateDate(String line){
            boolean test=false;
            DateTimeFormatter dateFormatter= DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
            DateValidator validator = new DateValidatorUsingDateTimeFormatter(dateFormatter);

            test=validator.isValid(line);
            System.out.println(validator.isValid("Date is : " +test));
            if(!test)
                JOptionPane.showMessageDialog(null,"Invalid Date Format! dd-mm-yyyy");


            return test;

        }//end method


}//end class
