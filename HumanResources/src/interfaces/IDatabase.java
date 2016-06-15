package interfaces;

import java.sql.Connection;

public interface IDatabase {
	public Connection link();
	public Connection connect(String url, String user, String password);
}
