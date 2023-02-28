package score;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Score {
	private String currentUser;
	private int currentScore;
	private String lastUpdateTime;
	
	public Score() {
		this.currentScore=0;
		
	}
	
	public void initScore() {
		this.currentScore =0;
	}
	
	public void setUser(String currentUser) {
		this.currentUser = currentUser;
	}
	
	public void addScore(int score) {
		if (currentUser!=null) {
			this.currentScore+= score;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			this.lastUpdateTime = sdf.format(new Date());
		}
	}
	
	public void minusScore(int score) {
		if (currentUser!=null) {
			this.currentScore-=score;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			this.lastUpdateTime = sdf.format(new Date());
		}
	}
	
	public int getScore() {
		return this.currentScore;
	}
	
	public String getUser() {
		return this.currentUser==null?"":this.currentUser;
	}
	
	public String getScoreTime() {
		return this.lastUpdateTime;
	}
	
	public boolean isValid() {
		if (this.currentUser == null) return false;
		if (this.currentUser.equals("")) return false;
		return true;
	}
}
