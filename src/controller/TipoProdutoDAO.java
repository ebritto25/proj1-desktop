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
		TipoProduto tp = null;

		connect();
		try
		{
			String query = "select * from tipo_produto where id_tProduto = ?";
			statement = configureStatement(query);
			results = statement.executeQuery();
			if(!results.isFirst())
			{
				tp.setId(results.getInt(1));
				tp.setDescricao(results.getString(2));
			}
		}
		catch(SQLException ex)
		{
			System.err.println("Erro ao buscar por ID: " + ex);
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
			TipoProduto tp = new TipoProduto();
			do
			{
				tp.setId(results.getInt(1));
				tp.setDescricao(results.getString(2));
			}while(results.next());
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
		
		if(connect())
		{
			ArrayList<TipoProduto> registros = FileManager.readFile(db);
			
			if(registros.isEmpty())
				return encontrados;
			
			for(Object registro : registros)
			{
				TipoProduto tp = (TipoProduto)registro;
				if(tp.getDescricao().toUpperCase().contains(descricao.toUpperCase()))
					encontrados.add(tp);
			}
		}
		
		return encontrados;
	}
	
	public static boolean update(TipoProduto tp, TipoProduto novosDados)
	{
		
		if(connect())
		{
			ArrayList<TipoProduto> registros = FileManager.readFile(db);
			registros.set(registros.indexOf(tp), novosDados);
			
			FileManager.deleteFile(db);
			
			for(int i = 0;i < registros.size();i++)
			{
				FileManager.writeFile(db, registros.get(i), (i!=0));
			}
			return true;
		}
		return false;
	}
		
	public static boolean delete(TipoProduto tp)
	{
		
		if(connect())
		{
			ArrayList<TipoProduto> registros = FileManager.readFile(db);
			registros.remove(tp);
			
			FileManager.deleteFile(db);
			
			for(int i = 0;i < registros.size();i++)
			{
				FileManager.writeFile(db, registros.get(i), (i != 0));
			}
			
			return true;
		}
		
		return false;
	}

}
