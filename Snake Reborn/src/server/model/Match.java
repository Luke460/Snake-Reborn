package server.model;
import java.util.Date;

public class Match {

	private Date date;
	private int score;
	private long playingTime;
	private int kills;
	
	public Match() {
		date = new Date();
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getPlayingTime() {
		return playingTime;
	}
	public void setPlayingTime(long playingTime) {
		this.playingTime = playingTime;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getKills() {
		return kills;
	}
	public void setKills(int kills) {
		this.kills = kills;
	}
}