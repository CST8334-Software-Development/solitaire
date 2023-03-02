package score;

import java.text.SimpleDateFormat;
import java.util.Date;
import solitaire.Solitaire;
public class Score {
	
	private String currentUser;
	private int currentScore;
	private String lastUpdateTime;
	private int gameMode=0;
	
	public Score(int gameMode) {
		this.currentScore=0;
		this.gameMode = gameMode;
	}
	
	public void setGameMode(int gameMode) {
		if (this.gameMode!= gameMode)
			this.gameMode = gameMode;
			resetScore();
		
	}
	
	public void resetScore() {
		if (this.gameMode == Solitaire.KLONDIKE_GAME_MODE)
			this.currentScore =Solitaire.KLONDIKE_GAME_INITIAL_SCORE;
		if (this.gameMode == Solitaire.VEGAS_GAME_MODE)
			this.currentScore =Solitaire.VEGAS_GAME_INITIAL_SCORE;
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
