package ledger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class InsertFile {

	
	Vector data;
	Vector columns;
	public String fileName="D://Ledger//data.txt";

	public InsertFile() {
		String line;
		data=new Vector();
		columns=new Vector();
		
		try {
			FileInputStream fis=new FileInputStream(fileName);
			BufferedReader br=new BufferedReader(new InputStreamReader(fis));
			StringTokenizer st1=new StringTokenizer(br.readLine()," ");
			
			while(st1.hasMoreTokens())
				columns.addElement(st1.nextToken());
			while((line=br.readLine())!=null) {
				StringTokenizer st2=new StringTokenizer(line," ");
				while(st2.hasMoreTokens())
					data.addElement(st2.nextToken());
			}
			br.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getRowCount() {
		return data.size()/getColumnCount();
	}
	
	public int getColumnCount() {
		return columns.size();
	}
	
	public Object getValueAt(int rowIndex,int columnIndex) {
		return(String) data.elementAt((rowIndex*getColumnCount())+columnIndex);
	}
	
	public void createTable() {
		JTable table=new JTable();
		JScrollPane scrollpanel=new JScrollPane(table);
		JPanel panel=new JPanel();
		panel.add(scrollpanel);
		JFrame frame=new JFrame();
		frame.add(panel,"Center");
		frame.pack();
		frame.setVisible(false);
		
	}
}
