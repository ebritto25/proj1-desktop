package controller;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ItensPedido;
import model.Pedido;
import model.Produto;
import utils.FileManager;

public class ItensPedidoDAO {
	private static Database db;
	private static PreparedStatement statement;
	private static ResultSet results = null;
	
	private static void connect()
	{
		db = Database.getInstance();
	}
	
	private static PreparedStatement configureStatement(String sql) throws SQLException
	{
		return db.getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	}
	
	private static ItensPedido itensPedidoBuilder() throws SQLException
	{
		ItensPedido iP = new ItensPedido();
		
		iP.setPedido(PedidoDAO.queryByID(results.getInt(1)));
		iP.setProduto(ProdutoDAO.queryByID(results.getInt(2)));
		iP.setQuantidade(results.getInt(3));
		
		return iP;
	}
	
	public static boolean insert(ItensPedido ip)
	{
		connect();
		try
		{
			String query = "insert into itens_pedido (id_pedido,id_produto) values ?,?";
			statement = configureStatement(query);
			statement.setInt(1,ip.getPedido().getId());
			statement.setInt(2,ip.getProduto().getId());
			statement.executeUpdate();
			db.getConnection().commit();
			return true;
		}
		catch(SQLException ex)
		{
			System.err.println("Problema na inserção de ItensPedido: "+ex.getMessage());
		}
		return false;
	}
	
	public static ArrayList<ItensPedido> queryByPedido(int idPedido)
	{
		ArrayList<ItensPedido> encontrados = new ArrayList<>();
		
		connect();
		try
		{
			String query = "select * from itens_pedido where id_pedido = ?";
			statement = configureStatement(query);
			statement.setInt(1, idPedido);
			results = statement.executeQuery();
			
			do
			{
				encontrados.add(itensPedidoBuilder());
			}while(results.next());
		}
		catch(SQLException ex)
		{
			System.err.println("Erro ao buscar ItensPedido por ID de pedido: " + ex);
			return null;
		}
		
		return encontrados;
	}
	
	public static ArrayList<ItensPedido> queryAll()
	{
		ArrayList<ItensPedido> encontrados = new ArrayList<>();
		
		connect();
		try
		{
			String query = "select * from itens_pedido";
			statement = configureStatement(query);
			results = statement.executeQuery();
			
			do
			{
				encontrados.add(itensPedidoBuilder());
			}while(results.next());
		}
		catch(SQLException ex)
		{
			System.err.println("Erro ao buscar todos ItensPedido: " + ex);
			return null;
		}
		
		return encontrados;
	}
	
	public static boolean update(ItensPedido ip, ItensPedido novosDados)
	{
		connect();
		try
		{
			String query = "update itens_pedido set quantidade = ? where id_pedido = ? and id_produto = ?";
			statement = configureStatement(query);
			statement.setInt(1, novosDados.getQuantidade());
			statement.setInt(2, ip.getProduto().getId());
			statement.setInt(3, ip.getProduto().getId());
			statement.executeUpdate();
			db.getConnection().commit();
			return true;
		}
		catch(SQLException ex)
		{
			System.err.println("Erro no update do ItensPedido: " + ex.getMessage());
		}
		
		return false;
	}
	
	public static boolean delete(ItensPedido ip)
	{
		connect();
		try
		{
			String query = "delete from itens_pedido where id_pedido = ? and id_produto = ?";
			statement = configureStatement(query);
			statement.setInt(1, ip.getPedido().getId());
			statement.setInt(2, ip.getProduto().getId());
			statement.executeUpdate();
			db.getConnection().commit();
			return true;
		}
		catch(SQLException ex)
		{
			System.err.println("Erro no delete do ItensPedido: " + ex.getMessage());
		}
		
		return false;
	}
}
