
package controller;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cliente;


public class ClienteDAO {

	private static Database db;
	private static PreparedStatement statement;
	private static ResultSet results = null;

	public ClienteDAO()
	{
		
	}
	
	private static void connect()
	{

		db = Database.getInstance();

	}
	
	private static PreparedStatement configureStatement(String sql) throws SQLException
	{
		return db.getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	}
	
	
	private static Cliente clienteBuilder() throws SQLException
	{
		Cliente c = new Cliente();
		
		c.setId(results.getInt(1));
		c.setNome(results.getString(2));
		c.setTelefone(results.getString(3));
		c.setEndereco(results.getString(4));
		c.setBairro(results.getString(5));
		c.setCep(results.getString(6));

		return c;
	}
	
	public static boolean insert(Cliente cli)
	{
		connect();
		
		try{
			String query = "insert into cliente (nome,telefone,endereco,bairro,cep) values (?,?,?,?,?)";
			statement = configureStatement(query);
			statement.setString(1, cli.getNome());
			statement.setString(2, cli.getTelefone());
			statement.setString(3, cli.getEndereco());
			statement.setString(4, cli.getBairro());
			statement.setString(5, cli.getCep());
			statement.executeUpdate();
			db.getConnection().commit();
		}
		catch(SQLException ex){
			System.err.println("Erro na inserção: " + ex.getMessage());
			return false;
		}
		
		return true;
	}
	
	
	
	public static Cliente queryByID(int id)
	{
		
		Cliente c = new Cliente();

		connect();
		try
		{
			String query = "select * from cliente where id_cliente = ?";
			statement = configureStatement(query);
			statement.setInt(1, id);
			results = statement.executeQuery();
			
			if(results.isFirst())
			{
				c = clienteBuilder();
			}
			
		}
		catch(SQLException ex)
		{
			System.err.println("Erro ao buscar por ID: " + ex);
			return null;
		}
		
		return c;
	}
	
	public static ArrayList<Cliente> queryAll()
	{
		ArrayList<Cliente> clientes = new ArrayList<>();
		
		connect();
		
		try
		{
			String query = "select * from cliente";
			statement = configureStatement(query);
			results = statement.executeQuery();
			
			do
			{	
				clientes.add(clienteBuilder());
			}while(results.next());
		}
		catch(SQLException ex)
		{
			System.err.println("Erro na Busca por todos os Clientes: " + ex.getMessage());
		}
				
		return clientes;
	}
	
	public static ArrayList<Cliente> queryByNome(String nome)
	{
		ArrayList<Cliente> encontrados = new ArrayList<>();
		
		connect();
		
		try
		{
			String query = "select * from cliente where nome like ?";
			statement = configureStatement(query);
			statement.setString(1, "%"+nome+"%");
			results = statement.executeQuery();
			
			do
			{
				encontrados.add(clienteBuilder());
			}while(results.next());
		}
		catch(SQLException ex)
		{
			System.err.println("Erro na Busca por nome dos Clientes: " + ex.getMessage());
		}
		
		return encontrados;
	}
	
	public static boolean delete(Cliente cli)
	{
		connect();
		try
		{
			String query = "delete from cliente where id_cliente = ?";
			statement = configureStatement(query);
			statement.setInt(1, cli.getId());
			statement.executeUpdate();
			db.getConnection().commit();
			return true;
		}
		catch(SQLException ex)
		{
			System.err.println("Erro no delete do Cliente: " + ex.getMessage());
		}
		
		return false;
	}
	
	public static boolean update(Cliente cli,Cliente novosDados)
	{
		connect();
		try
		{
			String query = "update cliente set nome = ?, telefone = ?, endereco = ?, bairro = ?, cep = ? where id_cliente = ?";
			statement = configureStatement(query);
			statement.setString(1, novosDados.getNome());
			statement.setString(2, novosDados.getTelefone());
			statement.setString(3, novosDados.getEndereco());
			statement.setString(4, novosDados.getBairro());
			statement.setString(5, novosDados.getCep());
			statement.setInt(6, cli.getId());
			statement.executeUpdate();
			db.getConnection().commit();
			return true;
		}
		catch(SQLException ex)
		{
			System.err.println("Erro no update do Cliente: " + ex.getMessage());
		}
		
		return false;
	}
	
}
