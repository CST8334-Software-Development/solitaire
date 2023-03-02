package score;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import solitaire.Solitaire;

public class ScoreBoard {
	public static String KLONDIKE_SCORE_FILE_PATH = "solitaire/config/Klondike_scoreboard.txt";
	public static String VEGAS_SCORE_FILE_PATH = "solitaire/config/Vegas_scoreboard.txt";
	public static String REGEX = ";";
	
	private String currentUser;
	private ArrayList<ScoreRecord> kScoreRecord;
	private ArrayList<ScoreRecord> vScoreRecord;

	private int maxScoreRecordCount = 5;
	private int currentGameMode = Solitaire.KLONDIKE_GAME_MODE;
	private ScoreBoardDialog thisDialog;
		
	public ScoreBoard(int gameMode) {
		this.currentGameMode = gameMode;
		this.readKScoreBoardFromFile();
		this.readVScoreBoardFromFile();
	}
	
	public void showScoreBoardDialog(JFrame owner) {
		thisDialog = new ScoreBoardDialog(owner);
		thisDialog.setVisible(true);
	}
	public void closeDialog() {
		thisDialog.dispose();
		thisDialog = null;
	}
	private class ScoreBoardDialog extends JDialog{
		private JTable kTable;
		private JTable vTable;
		private DefaultTableModel kTableModel;
		private DefaultTableModel vTableModel;
		private JButton okButton;
		private JButton cancelButton;
		private JLabel userNameLabel;
		private JTextField userNameTextField;
		private int dialog_height=500;
		private int dialog_width=400;
		
		public ScoreBoardDialog(JFrame owner) {
			super(owner,"Score Board", true) ;
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			int x = owner.getX()+owner.getWidth()/2-dialog_width/2;
			int y = owner.getY()+owner.getHeight()/2-dialog_height/2;
			this.setLocation(new Point(x,y));
			this.setSize(dialog_width,dialog_height);
			this.setResizable(false);
			initDialog();
		}
		
		private void initDialog() {
			initKTable();
			initVTable();
			JScrollPane kScrollPane = new JScrollPane(kTable);
			kTable.setFillsViewportHeight(true);
			
			JScrollPane vScrollPane = new JScrollPane(vTable);
			vTable.setFillsViewportHeight(true);
			
			JTabbedPane tabbedPane = new JTabbedPane();

			tabbedPane.addTab("KLONDIKE SCORE BOARD", null, kScrollPane,
			                  "KLONDIKE SCORE BOARD");
			
			tabbedPane.addTab("VEGAS SCORE BOARD", null, vScrollPane,
	                  "VEGAS SCORE BOARD");
			
			userNameLabel = new JLabel("Please input your name:");
			userNameTextField = new JTextField(20);
			userNameTextField.setText(currentUser);
			okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					    String newUserName = userNameTextField.getText();
					    if (!newUserName.equals("")) {
					    	if (!newUserName.equals(currentUser)) {
					    		setCurrentUser(newUserName);
					    	}
					    }
					    setVisible(false);
					    dispose();
				  } 
			} );
			cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) {
				    //setVisible(false);
				    dispose();
				}
			});
			
			JPanel panel1 = new JPanel();
			panel1.setLayout(new BorderLayout());
			panel1.add(userNameLabel,BorderLayout.WEST);
			panel1.add(userNameTextField,BorderLayout.EAST);

			JPanel panel2 = new JPanel();
			panel2.setLayout(new BorderLayout());
			panel2.add(okButton,BorderLayout.WEST);
			panel2.add(cancelButton,BorderLayout.EAST);
			
//			tabbedPane.setSize(dialog_width,300);
//			panel1.setSize(dialog_width,50);
//			panel2.setSize(dialog_width,50);

			this.setLayout(new BorderLayout());
			this.add(tabbedPane,BorderLayout.NORTH);
			this.add(panel1,BorderLayout.CENTER);
			this.add(panel2,BorderLayout.SOUTH);
			this.pack();
			
//			this.add(userNameLabel);
//			this.add(userNameTextField);
//			this.add(okButton);
//			this.add(cancelButton);
		}
		private void initKTable() {
			kTableModel= new DefaultTableModel();
			
			kTableModel.addColumn("Rank");
			kTableModel.addColumn("Name");
			kTableModel.addColumn("Score");
			kTableModel.addColumn("Time");
			
			for (int i=0;i<kTableModel.getRowCount();i++) {
				kTableModel.removeRow(i);
			}
			
			for (int i =0;i<kScoreRecord.size();i++) {
				Object[] rowObject = new Object[4];
				rowObject[0] = i+1;
				rowObject[1] = kScoreRecord.get(i).getName();
				rowObject[2] = kScoreRecord.get(i).getScore();
				rowObject[3] = kScoreRecord.get(i).getRecordTime();
				kTableModel.addRow(rowObject);
			}
			
			kTable = new JTable(kTableModel);
			
		}
		
		private void initVTable() {
			
			vTableModel= new DefaultTableModel();
			vTableModel.addColumn("Rank");
			vTableModel.addColumn("Name");
			vTableModel.addColumn("Score");
			vTableModel.addColumn("Time");

			for (int i=0;i<vTableModel.getRowCount();i++) {
				vTableModel.removeRow(i);
			}
			
			for (int i =0;i<vScoreRecord.size();i++) {
				Object[] rowObject = new Object[4];
				rowObject[0] = i+1;
				rowObject[1] = vScoreRecord.get(i).getName();
				rowObject[2] = vScoreRecord.get(i).getScore();
				rowObject[3] = vScoreRecord.get(i).getRecordTime();
				vTableModel.addRow(rowObject);
			}
			vTable = new JTable(vTableModel);
		}
	}
	
	public String getCurrentUser() {
		return currentUser==null?"":currentUser;
	}
	
	public void setCurrentUser(String user) {
		this.currentUser = user;
		
	}
	
	public void saveCurrentScore(Score currentScore) {
		if (currentScore.isValid()) {
			ScoreRecord newScoreRecord = new ScoreRecord(currentScore.getUser(),currentScore.getScore(),currentScore.getScoreTime());
			
			if (this.currentGameMode == Solitaire.KLONDIKE_GAME_MODE) {
				kScoreRecord.add(newScoreRecord);		
				Collections.sort(kScoreRecord);		
			
				ArrayList<ScoreRecord> temp = new ArrayList<ScoreRecord>();
				for (int i=0; i<(kScoreRecord.size()>maxScoreRecordCount?maxScoreRecordCount:kScoreRecord.size());i++ ) {
					temp.add(kScoreRecord.get(i));
				}
				
				kScoreRecord=temp;
				saveKScoreBoardToFile();
			}
			
			if (this.currentGameMode == Solitaire.VEGAS_GAME_MODE) {
				vScoreRecord.add(newScoreRecord);		
				Collections.sort(vScoreRecord);		
			
				ArrayList<ScoreRecord> temp = new ArrayList<ScoreRecord>();
				for (int i=0; i<(vScoreRecord.size()>maxScoreRecordCount?maxScoreRecordCount:vScoreRecord.size());i++ ) {
					temp.add(vScoreRecord.get(i));
				}
				
				vScoreRecord=temp;
				saveVScoreBoardToFile();
			}
		}
	}
	private void readKScoreBoardFromFile()  {
		kScoreRecord = new ArrayList<ScoreRecord>();
		File file = new File(KLONDIKE_SCORE_FILE_PATH);
        try {
    		
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            
            String line ;
            while ((line = br.readLine()) != null) {
            	String[] record = line.split(REGEX);
            	kScoreRecord.add(new ScoreRecord(record[0].toString(),Integer.parseInt(record[1]),record[2]));
            }
            br.close();
            isr.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
        	 e.printStackTrace();
        }
    }
	
	private void readVScoreBoardFromFile()  {
		vScoreRecord = new ArrayList<ScoreRecord>();
		File file = new File(VEGAS_SCORE_FILE_PATH);
        try {
    		
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            
            String line ;
            while ((line = br.readLine()) != null) {
            	String[] record = line.split(REGEX);
            	vScoreRecord.add(new ScoreRecord(record[0].toString(),Integer.parseInt(record[1]),record[2]));
            }
            br.close();
            isr.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
        	 e.printStackTrace();
        }
    }
	
	private void saveKScoreBoardToFile() {
		if (kScoreRecord.size()>0) {
			File file = new File(KLONDIKE_SCORE_FILE_PATH);
			try {
		            FileOutputStream fos = new FileOutputStream(file);
		            OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
		            BufferedWriter bw = new BufferedWriter(osw);
		            for (ScoreRecord record:kScoreRecord) {
		            	bw.write(record.getName() + REGEX + record.getScore() + REGEX + record.getRecordTime()+"\n");
		            } 
		            bw.close();
		            osw.close();
		            fos.close();
		            
		         } catch (FileNotFoundException e) {
		            e.printStackTrace();
		         } catch (UnsupportedEncodingException e) {
		            e.printStackTrace();
		         } catch (IOException e) {
		            e.printStackTrace();
	        }
		}
	}
	
	private void saveVScoreBoardToFile() {
		if (vScoreRecord.size()>0) {
			File file = new File(VEGAS_SCORE_FILE_PATH);
			try {
		            FileOutputStream fos = new FileOutputStream(file);
		            OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
		            BufferedWriter bw = new BufferedWriter(osw);
		            for (ScoreRecord record:vScoreRecord) {
		            	bw.write(record.getName() + REGEX + record.getScore() + REGEX + record.getRecordTime()+"\n");
		            } 
		            bw.close();
		            osw.close();
		            fos.close();
		            
		         } catch (FileNotFoundException e) {
		            e.printStackTrace();
		         } catch (UnsupportedEncodingException e) {
		            e.printStackTrace();
		         } catch (IOException e) {
		            e.printStackTrace();
	        }
		}
	}
	
	private class ScoreRecord implements Comparable {
		
		private String name;
		private int score;
		private String recordTime;
		
		public ScoreRecord(String name,int score,String time) {
			this.name = name;
			this.score = score;
			this.recordTime = time;
		}
		public String getName() {
			return name;
		}
		public int getScore() {
			return score;
		}
		public String getRecordTime() {
			return recordTime;
		}
		@Override
		public int compareTo(Object o) {
	        return ((ScoreRecord)o).getScore()-this.score;
		}
	}
}
