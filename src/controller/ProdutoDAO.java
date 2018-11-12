package controller;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Produto;
import model.TipoProduto;
import utils.FileManager;

public class ProdutoDAO {
	private static Database db;
	private static PreparedStatement statement;
	private static ResultSet results = null;
	
	ProdutoDAO(){}
	
	private static void connect()
	{

		db = Database.getInstance();

	}
	
	private static PreparedStatement configureStatement(String sql) throws SQLException
	{
		return db.getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	}
	
	
	private static Produto produtoBuilder() throws SQLException
	{   
		TipoProduto tProduto = TipoProdutoDAO.queryByID(results.getInt(4));
		Produto p = new Produto();
		
		p.setId(results.getInt(1));
		p.setDescricao(results.getString(2));
		p.setPreco(results.getDouble(3));
		p.setTipo(tProduto);
		
		return p;
	}
	
	public static boolean insert(Produto p)
	{
		connect();
		try{
			String query = "insert into produto (descricao_produto,preco_produto,id_tProduto) values (?,?,?)";
			statement = configureStatement(query);
			statement.setString(1, p.getDescricao());
			statement.setDouble(2, p.getPreco());
			statement.setInt(3, p.getTipo().getId());
			statement.executeUpdate();
			db.getConnection().commit();
		}
		catch(SQLException ex){
			System.err.println("Erro na inserção: " + ex.getMessage());
			return false;
		}
		
		return true;
	}
	
	public static Produto queryByID(int id)
	{
		
		Produto p = new Produto();

		connect();
		try
		{
			String query = "select * from produto where id_produto = ?";
			statement = configureStatement(query);
			statement.setInt(1, id);
			results = statement.executeQuery();
			if(results.isFirst())
			{
				p = produtoBuilder();
			}
		}
		catch(SQLException ex)
		{
			System.err.println("Erro ao buscar por ID: " + ex);
			return null;
		}
		
		return p;
	}
	
	public static ArrayList<Produto> queryAll()
	{
		ArrayList<Produto> encontrados = new ArrayList<>();
		
		connect();
		
		try
		{
			String query = "select * from produto";
			statement = configureStatement(query);
			results = statement.executeQuery();
			
			do
			{	
				encontrados.add(produtoBuilder());
			}while(results.next());
		}
		catch(SQLException ex)
		{
			System.err.println("Erro na Busca por todos os Podutos: " + ex.getMessage());
		}
		
		return encontrados;
	}
	
	public static ArrayList<Produto> queryByDescricao(String descricao)
	{
		ArrayList<Produto> encontrados = new ArrayList<>();
		
		connect();
		
		try
		{
			String query = "select * from produto where descricao_produto like ?";
			statement = configureStatement(query);
			statement.setString(1, "%"+descricao+"%");
			results = statement.executeQuery();
			
			do
			{
				encontrados.add(produtoBuilder());
			}while(results.next());
		}
		catch(SQLException ex)
		{
			System.err.println("Erro na Busca por descricao dos Podutos: " + ex.getMessage());
		}
		
		return encontrados;
	}
	
	public static boolean delete(Produto prod)
	{
		connect();
		try
		{
			String query = "delete from produto where id_produto = ?";
			statement = configureStatement(query);
			statement.setInt(1, prod.getId());
			statement.executeUpdate();
			db.getConnection().commit();
			return true;
		}
		catch(SQLException ex)
		{
			System.err.println("Erro no delete do Produto: " + ex.getMessage());
		}
		
		return false;
	}
	
	public static boolean update(Produto prod, Produto novoProduto)
	{
		connect();
		try
		{
			String query = "update produto set descricao_produto = ?, preco_produto = ?, id_tProduto = ? where id_produto = ?";
			statement = configureStatement(query);
			statement.setString(1, novoProduto.getDescricao());
			statement.setDouble(2, novoProduto.getPreco());
			statement.setInt(3, novoProduto.getTipo().getId());
			statement.setInt(4, prod.getId());
			statement.executeUpdate();
			db.getConnection().commit();
			return true;
		}
		catch(SQLException ex)
		{
			System.err.println("Erro no update do Produto: " + ex.getMessage());
		}
		
		return false;
	}
	
}
