package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database {
	private final String connString = "jdbc:postgresql://localhost/projdesktop";
	private final String usuario = "postgres";
	private final String senha = "desktop1234";
	private Connection connection = null; 
	private static Database instance = null;


	private Database()	
	{
		try
		{
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(connString,usuario,senha);
			connection.setAutoCommit(false);
			
		}
		catch(ClassNotFoundException ex)
		{
			System.err.println("Driver não encontrado: " + ex.getMessage());
		}
		catch(SQLException ex)
		{
			System.err.println("Erro ao Conectar: " + ex.getMessage());
		}
	}
		
	public Connection getConnection()
	{
		return connection;
	}
	
	public static Database getInstance()
	{
		if(instance == null)
			instance = new Database();
		
		return instance;
	}
	
}
