package server.client;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import server.model.Credentials;
import server.model.Match;
import server.model.User;

public class JsonService {

	ObjectMapper objectMapper;
	
	public JsonService() {
		objectMapper = new ObjectMapper();
	}
	
	public User json2Player(String json) {
		
		User user = null;
	
		try {
			user = objectMapper.readValue(json, User.class);
		} catch(IOException e) {
			e.printStackTrace();
		}

		return user;
	}
	
	public String match2Json(Match match) {
		
		String result = null;
		
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.setDateFormat(new ISO8601DateFormat());
		
		try {
			result = objectMapper.writeValueAsString(match);
		} catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String credentials2Json(Credentials credentials) {
		
		String result = null;
		
		try {
			result = objectMapper.writeValueAsString(credentials);
		} catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
