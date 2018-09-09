package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import model.ItensPedido;
import model.Pedido;
import model.Produto;
import utils.FileManager;

public class ItensPedidoDAO {
	private static File db;
	
	private static boolean connect()
	{
		try
		{
			db = Database.getInstance().getDatabaseItensPedido();
		}
		catch(IOException ex)
		{
			System.err.println(ex.getMessage());
			return false;
		}
		
		return true;
	}
	
	public static boolean insert(ItensPedido ip)
	{
		if(connect())
		{
			return FileManager.writeFile(db,ip.toString(),true);
		}
		return false;
	}
	
	public static ArrayList<ItensPedido> queryByPedido(int idPedido)
	{
		ArrayList<ItensPedido> encontrados = new ArrayList<>();
		if(connect())
		{
			String dados = FileManager.readFile(db);
			String[] registros = dados.split("\n");
			
			if(registros[0].equals(""))
				return encontrados;
			
			for(String registro : registros)
			{
				String[] r_dados = registro.split(",");
				int r_idPedido = Integer.parseInt(r_dados[1]);

				if(r_idPedido == idPedido)
				{
					Pedido pedido = PedidoDAO.queryByID(r_idPedido);
					
					int r_idProduto = Integer.parseInt(r_dados[0]);
					Produto produto = ProdutoDAO.queryByID(r_idProduto);
					
					int quantidade = Integer.parseInt(r_dados[2]);
					encontrados.add(new ItensPedido(produto,pedido,quantidade));
				}
				
			}
		}
		return encontrados;
	}
	
	public static ArrayList<ItensPedido> queryAll()
	{
		ArrayList<ItensPedido> encontrados = new ArrayList<>();
		
		if(connect())
		{
			String dados = FileManager.readFile(db);
			String[] registros = dados.split("\n");
			
			if(!registros[0].equals(""))
				return encontrados;
			
			for(String registro : registros)
			{
				String[] r_dados = registro.split(",");
				int r_idPedido = Integer.parseInt(r_dados[1]);

				
				Pedido pedido = PedidoDAO.queryByID(r_idPedido);

				int r_idProduto = Integer.parseInt(r_dados[0]);
				Produto produto = ProdutoDAO.queryByID(r_idProduto);

				int quantidade = Integer.parseInt(r_dados[2]);
				encontrados.add(new ItensPedido(produto,pedido,quantidade));
				
			}
		}
		
		return encontrados;
	}
	
	public static boolean update(ItensPedido ip, ItensPedido novosDados)
	{
		if(connect())
		{
			String dados = FileManager.readFile(db);
			return FileManager.writeFile(db, dados.replace(ip.toString(), novosDados.toString()), false);
		}
	
		
		return false;
	}
	
	public static boolean delete(ItensPedido ip)
	{
		if(connect())
		{
			String dados = FileManager.readFile(db);
			return FileManager.writeFile(db, dados.replace(ip.toString(),""), false);
		}
		return false;
	}
}
