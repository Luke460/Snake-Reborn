package game;

public class UserLocal {
	private String nomeUtente;
	private String password;
	
	public UserLocal(){
		
	}

	public String getUsername() {
		return this.nomeUtente;
	}

	public void setUsername(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}	

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
