package ru.californication.models;

import java.util.Map;
import java.util.List;

public interface DAO {

	public String getPassword(String password);
	public void createUser(Map<String, String> info);
	public boolean resetPassword(String userName, String secret, String password);
	public boolean setMessage(String from, String to, String text);
	public List<?> getUserInfo(String userName);
	public List<?> getUserMessages(String userName);
	public List<?> getUsersList();

}
