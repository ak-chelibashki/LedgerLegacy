package datahandler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class DataFileTableModel extends AbstractTableModel {
	
	protected Vector data;
	protected Vector columnNames;
	protected String filePath="D://Ledger//data.txt";
	
	public DataFileTableModel(String file) {
		filePath=file;
		initVectors();
	}
	
	public void initVectors() {
		
		String aLine;
		data=new Vector();
		columnNames=new Vector();
		
		try {
			//import FileInputStream and BufferedReader to read data from txt file
			FileInputStream fInput=new FileInputStream(filePath);
			BufferedReader br= new BufferedReader(new InputStreamReader(fInput));
			
			StringTokenizer st1=new StringTokenizer(br.readLine()," ");
			while(st1.hasMoreTokens())
				columnNames.addElement(st1.nextToken());
			while((aLine=br.readLine())!=null) {
				StringTokenizer st2 = new StringTokenizer(aLine," ");
				while(st2.hasMoreTokens())
					data.addElement(st2.nextToken());
			}
			br.close();
			//
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int getRowCount() {
		// return number of rows
		return data.size() / getColumnCount();
	}

	@Override
	public int getColumnCount() {
		// return number of columns 
		return columnNames.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return (String) data.elementAt((rowIndex*getColumnCount())+columnIndex);
	}
}
