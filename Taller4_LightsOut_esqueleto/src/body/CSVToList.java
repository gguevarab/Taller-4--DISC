package body;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class CSVToList {
	
	private ArrayList<String> rowsString;
	private ArrayList<ArrayList<String>> rows;
	
	public CSVToList() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("././data/top10.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rows = new ArrayList<>();
	    rowsString = new ArrayList<>();

	    String line;
	    int ranking = 1;
	    try {
			while ((line = reader.readLine()) != null) {
				ArrayList<String> arrlst = new ArrayList<>();
				String[] temp = line.split(";");
				for (String s: temp) {
					arrlst.add(s);
				}
				String tempS = Integer.toString(ranking) + " / " + temp[0] + " / " + temp[1];
				ranking++;
				rows.add(arrlst);
				rowsString.add(tempS);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	    try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void showFrame() {
		
		JList<String> list = new JList<>(rowsString.toArray(new String[0]));
	    
	    list.setCellRenderer(new rendererJList());
	    
	    JFrame frame = new JFrame("CSV to List");
	    JPanel mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	    mainPanel.add(new JScrollPane(list));
	    mainPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
	    
	    frame.add(mainPanel);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.setPreferredSize(new Dimension(300, 500));
	    frame.pack();
	    frame.setVisible(true);
	    
	}
	
	public void addRecord(int score, String name) throws IOException {
		
		ArrayList<String> newRecord = new ArrayList<String>();
		newRecord.add(name);
		newRecord.add(Integer.toString(score));

		for (int i = 0; i < rows.size(); i++) {
		    ArrayList<String> currentRecord = rows.get(i);
		    String currentScoreStr = currentRecord.get(1);
		    int currentScore = Integer.parseInt(currentScoreStr);

		    String newScoreStr = newRecord.get(1);
		    int newScore = Integer.parseInt(newScoreStr);

		    if (newScore > currentScore) {
		    	rows.remove(9);
		        rows.add(i, newRecord);
		        break;
		    }
		}
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("././data/top10.csv"));
        for (ArrayList<String> row : rows) {
            String lineString = String.join(";", row);
            writer.write(lineString);
            writer.newLine();
        }
        writer.close();
		
	}

}


