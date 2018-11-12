package controller;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.TipoProduto;
import utils.FileManager;

public class TipoProdutoDAO {
	private static Database db;
	private static PreparedStatement statement;
	private static ResultSet results = null;
	
	public TipoProdutoDAO(){}
	
	private static void connect()
	{

		db = Database.getInstance();

	}
	
	private static PreparedStatement configureStatement(String sql) throws SQLException
	{
		return db.getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	}
	
	public static boolean insert(TipoProduto tp)
	{
		connect();
		try{
			String query = "insert into tipo_produto (descricao_tProduto) values (?)";
			statement = configureStatement(query);
			statement.setString(1, tp.getDescricao());
			statement.executeUpdate();
			db.getConnection().commit();
		}
		catch(SQLException ex){
			System.err.println("Erro na inserção: " + ex.getMessage());
			return false;
		}
		
		return true;
	}
	
	public static TipoProduto queryByID(int id)
	{
		TipoProduto tp = new TipoProduto();

		connect();
		try
		{
			String query = "select * from tipo_produto where id_tProduto = ?";
			statement = configureStatement(query);
			statement.setInt(1, id);
			results = statement.executeQuery();
			
			if(results.isFirst())
			{
				tp.setId(results.getInt(1));
				tp.setDescricao(results.getString(2));
			}
			
		}
		catch(SQLException ex)
		{
			System.err.println("Erro ao buscar por ID: " + ex);
			return null;
		}
		
		return tp;
	}
	
	public static ArrayList<TipoProduto> queryAll()
	{
		
        ArrayList<TipoProduto> encontrados = new ArrayList<>();
		
		connect();
		
		try
		{
			String query = "select * from tipo_produto";
			statement = configureStatement(query);
			results = statement.executeQuery();
			
			while(results.next())
			{	
				TipoProduto tp = new TipoProduto();
				tp.setId(results.getInt(1));
				tp.setDescricao(results.getString(2));
				encontrados.add(tp);
			}
		}
		catch(SQLException ex)
		{
			System.err.println("Erro na Busca por todos os TiposPodutos: " + ex.getMessage());
		}
		
		return encontrados;
	}
	
	public static ArrayList<TipoProduto> queryByDescricao(String descricao)
	{
		ArrayList<TipoProduto> encontrados = new ArrayList<>();
		
		connect();
		
		try
		{
			String query = "select * from tipo_produto where descricao_tProduto like ?";
			statement = configureStatement(query);
			statement.setString(1, "%"+descricao+"%");
			results = statement.executeQuery();
			
			do
			{
				TipoProduto tp = new TipoProduto();
				tp.setId(results.getInt(1));
				tp.setDescricao(results.getString(2));
				encontrados.add(tp);
			}while(results.next());
		}
		catch(SQLException ex)
		{
			System.err.println("Erro na Busca por descricao dos TiposPodutos: " + ex.getMessage());
		}
		
		return encontrados;
	}
	
	public static boolean update(TipoProduto tp, TipoProduto novosDados)
	{
		
		connect();
		try
		{
			String query = "update tipo_produto set descricao_tProduto = ? where id_tProduto = ?";
			statement = configureStatement(query);
			statement.setString(1, novosDados.getDescricao());
			statement.setInt(2, tp.getId());
			statement.executeUpdate();
			db.getConnection().commit();
			return true;
		}
		catch(SQLException ex)
		{
			System.err.println("Erro no update do TipoProduto: " + ex.getMessage());
		}
		
		return false;
	}
		
	public static boolean delete(TipoProduto tp)
	{
		
		connect();
		try
		{
			String query = "delete from tipo_produto where id_tProduto = ?";
			statement = configureStatement(query);
			statement.setInt(1, tp.getId());
			statement.executeUpdate();
			db.getConnection().commit();
			return true;
		}
		catch(SQLException ex)
		{
			System.err.println("Erro no delete do TipoProduto: " + ex.getMessage());
		}
		
		return false;
	}

}
