package server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class User {

	private String username;
	private String email;
	private Integer highScore;
	private Long playTime;
	private Integer kills;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getHighScore() {
		return highScore;
	}
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	public long getPlayTime() {
		return playTime;
	}
	public void setPlayTime(long playTime) {
		this.playTime = playTime;
	}
	public int getKills() {
		return kills;
	}
	public void setKills(int kills) {
		this.kills = kills;
	}
	
	@Override
	public String toString() {
		String result = username + " " + email + " " + highScore + " " + playTime + " " + kills;
		
		return result;
	}
}
