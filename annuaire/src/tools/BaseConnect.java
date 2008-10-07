package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseConnect {

	private Connection connection;
	private boolean connected;
	private String url;
	private String username;
	private String pwd;
	private String driver;

	public BaseConnect(String driver, String url, String username, String pwd) {
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.pwd = pwd;
	}

	public boolean isConnected() {
		return connected;
	}

	/**
	 * Connexion a la base de données
	 * 
	 * @return boolean true si réussi , false autrement.
	 */
	public void connect() throws SQLException {
		System.out.println("Tentative de connexion a la  Base | hash : " + hashCode());
		try {
			System.out.println("Driver : " + driver);
			Class.forName(driver).newInstance();
		} catch (ClassNotFoundException ex1) {
			System.out.println("ClassNotFoundException " + ex1.getMessage());
			return;
		} catch (IllegalAccessException ex1) {
			System.out.println("IllegalAccessException " + ex1.getMessage());
			return;
		} catch (InstantiationException ex1) {
			System.out.println("InstantiationException " + ex1.getMessage());
			return;
		}

		connection = DriverManager.getConnection(url, username, pwd);
		// DriverManager.setLogWriter(new PrintWriter(System.out));
		System.out.println("Connexion Base OK");
		connected = true;
	}

	synchronized public PreparedStatement createPrepareStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}

	synchronized public Statement getStatement() throws SQLException {
		Statement statement = connection.createStatement();
		System.out.println("createStatement h: " + statement.hashCode());
		return statement;
	}

	synchronized public void close(Statement statement) {
		if (statement != null) {
			System.out.println("closeStatement h: " + statement.hashCode());
			try {
				statement.close();
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * Fermeture le la connexion
	 */
	public void close() {
		System.out.println("Fin de la Connexion | hash : " + hashCode());
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException ex) {
			}
		}
	}

}
