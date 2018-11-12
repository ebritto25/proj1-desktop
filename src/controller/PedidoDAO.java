package controller;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import model.Cliente;
import model.Pedido;
import model.TipoPagamento;
import utils.FileManager;

public class PedidoDAO {
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
	
	public static boolean insert(Pedido ped)
	{
		connect();
		try{
			String query = "insert into pedido (data_pedido,id_cliente,total,desconto,id_tpagamento,troco,subtotal) values (?,?,?,?,?,?,?)";
			statement = configureStatement(query);
                        statement.setObject(1,ped.getDataPedido());
			statement.setInt(2, ped.getIdCliente().getId());
                        statement.setDouble(3, ped.getTotal());
                        statement.setDouble(4,ped.getDesconto());
                        statement.setInt(5,ped.getIdPagamento().getId());
                        statement.setDouble(6,ped.getTroco());
                        statement.setDouble(7,ped.getSubTotal());
                        
			statement.executeUpdate();
			db.getConnection().commit();
		}
		catch(SQLException ex){
			System.err.println("Erro na inserção: " + ex.getMessage());
			return false;
		}
		
		return true;
	}
	
        
       
        private static Pedido pedidoBuilder() throws SQLException
	{
		Pedido p = new Pedido();
		
		p.setId(results.getInt(1));
		p.setDataPedido(results.getDate(2));
                
                Cliente cli = ClienteDAO.queryByID(results.getInt(3));
                
                p.setIdCliente(cli);
                p.setTotal(results.getDouble(4));
                p.setDesconto(results.getDouble(5));
                
                TipoPagamento tipo = TipoPagamentoDAO.queryByID(results.getInt(6));
                
                p.setIdPagamento(tipo);

                p.setTroco(results.getDouble(7));
                p.setSubTotal(results.getDouble(8));
                
		
		return p;
	}
        
	public static Pedido queryByID(int id)
	{
                Pedido p = new Pedido();

		connect();
		try
		{
			String query = "select * from pedido where id_pedido = ?";
			statement = configureStatement(query);
			statement.setInt(1, id);
			results = statement.executeQuery();
			if(results.isFirst())
			{
				p = pedidoBuilder();
			}
		}
		catch(SQLException ex)
		{
			System.err.println("Erro ao buscar por ID: " + ex);
			return null;
		}
		
		return p;
	}
	
	public static ArrayList<Pedido> queryAll()
	{
		ArrayList<Pedido> encontrados = new ArrayList<>();
		
		connect();
		
		try
		{
			String query = "select * from pedido";
			statement = configureStatement(query);
			results = statement.executeQuery();
			
			do
			{	
				encontrados.add(pedidoBuilder());
			}while(results.next());
		}
		catch(SQLException ex)
		{
			System.err.println("Erro na Busca por todos os pedidos: " + ex.getMessage());
		}
		
		return encontrados;
	}
	
	public static boolean delete(Pedido p)
	{
		connect();
		try
		{
			String query = "delete from pedido where id_pedido = ?";
			statement = configureStatement(query);
			statement.setInt(1, p.getId());
			statement.executeUpdate();
			db.getConnection().commit();
			return true;
		}
		catch(SQLException ex)
		{
			System.err.println("Erro no delete do Pedido: " + ex.getMessage());
		}
		
		return false;
	}
	
	public static boolean update(Pedido p,Pedido novosDados)
	{
		connect();
		try
		{
			String query = "update pedido set data_pedido = ?, id_cliente = ?, "
                                + "total = ?, desconto = ?, id_tpagamento = ?, troco = ?,"
                                + "subtotal = ? where id_pedido = ?";
                        
			statement = configureStatement(query);
			statement.setObject(1,novosDados.getDataPedido());
			statement.setInt(2, novosDados.getIdCliente().getId());
                        statement.setDouble(3, novosDados.getTotal());
                        statement.setDouble(4, novosDados.getDesconto());
                        statement.setInt(5, novosDados.getIdPagamento().getId());
                        statement.setDouble(6, novosDados.getTroco());
                        statement.setDouble(7, novosDados.getSubTotal());
                        statement.setInt(8, p.getId());
                        
			
			statement.executeUpdate();
			db.getConnection().commit();
			return true;
		}
		catch(SQLException ex)
		{
			System.err.println("Erro no update do Pedido: " + ex.getMessage());
		}
		
		return false;
	}
	
}
