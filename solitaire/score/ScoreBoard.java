package score;

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

import javax.swing.JOptionPane;

public class ScoreBoard {
	public static String SCORE_FILE_PATH = "solitaire/config/scoreboard.txt";
	public static String REGEX = ";";
	private String currentUser;
	private ArrayList<ScoreRecord> scoreRecord;
	private int maxScoreRecordCount = 5;
	
	public ScoreBoard() {
		 this.readScoreBoardFromFile();
	}
	
	public String getScoreRecord() {
		String returnValue = "";
		String gap = "     ";
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		for (int i=0;i<scoreRecord.size();i++) {
			returnValue = returnValue + "No." + (i+1)+gap+scoreRecord.get(i).getName()+gap+scoreRecord.get(i).getScore()+gap+scoreRecord.get(i).getRecordTime()+"\n";
        } 
		returnValue = returnValue+"\n";
		returnValue = returnValue+"Please input your name:"+"\n";
		return returnValue;
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
			
			scoreRecord.add(newScoreRecord);		
			Collections.sort(scoreRecord);		
			
			ArrayList<ScoreRecord> temp = new ArrayList<ScoreRecord>();
			for (int i=0; i<(scoreRecord.size()>maxScoreRecordCount?maxScoreRecordCount:scoreRecord.size());i++ ) {
				temp.add(scoreRecord.get(i));
			}
			
			scoreRecord=temp;
			saveScoreBoardToFile();
		}
	}
	private void readScoreBoardFromFile()  {
		scoreRecord = new ArrayList<ScoreRecord>();
		File file = new File(SCORE_FILE_PATH);
		
        try {
    		
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            
            String line ;
            while ((line = br.readLine()) != null) {
            	String[] record = line.split(REGEX);
            	scoreRecord.add(new ScoreRecord(record[0].toString(),Integer.parseInt(record[1]),record[2]));
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
	
	private void saveScoreBoardToFile() {
		if (scoreRecord.size()>0) {
			File file = new File(SCORE_FILE_PATH);
			try {
		            FileOutputStream fos = new FileOutputStream(file);
		            OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
		            BufferedWriter bw = new BufferedWriter(osw);
		            for (ScoreRecord record:scoreRecord) {
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
