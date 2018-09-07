package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import model.Cliente;
import model.Pedido;
import model.TipoPagamento;
import utils.FileManager;

public class PedidoDAO {
	private static File db;
	
	private static boolean connect()
	{
		try
		{
			db = Database.getInstance().getDatabasePedido();
		}
		catch(IOException ex)
		{
			System.err.println(ex.getMessage());
		}
		return true;
	}
	
	public static boolean insert(Pedido ped)
	{
		if(connect())
		{
			return FileManager.writeFile(db, ped.toString(),true);
		}
		return false;
	}
	
	public static Pedido queryByID(int id)
	{
		if(connect())
		{
			String dados = FileManager.readFile(db);
			String[] registros = dados.split("\n");
			
			for(String registro : registros)
			{
				String[] r_dados = registro.split(",");
				int r_id = Integer.parseInt(r_dados[0]);
				if(r_id == id)
				{
					int cliente_id = Integer.parseInt(r_dados[2]);
					Cliente cliente = ClienteDAO.queryByID(cliente_id);
					int pagamento_id = Integer.parseInt(r_dados[5]);
					TipoPagamento pagamento = TipoPagamentoDAO.queryByID(pagamento_id);
					
					return new Pedido(registro,cliente,pagamento);
				}
				
			}
		}
		
		return null;
	}
	
	public static ArrayList<Pedido> queryAll()
	{
		ArrayList<Pedido> encontrados = new ArrayList<>();
	
		if(connect())
		{
			String dados = FileManager.readFile(db);
			String[] registros = dados.split("\n");
			
			for(String registro : registros)
			{
				String[] r_dados = registro.split(",");
				
				int cliente_id = Integer.parseInt(r_dados[2]);
				Cliente cliente = ClienteDAO.queryByID(cliente_id);
				int pagamento_id = Integer.parseInt(r_dados[5]);
				TipoPagamento pagamento = TipoPagamentoDAO.queryByID(pagamento_id);
				
				encontrados.add(new Pedido(registro,cliente,pagamento));
			}
		}
		
		return encontrados;
	}
	
	public static boolean delete(Pedido p)
	{
		if(connect())
		{
			String dados = FileManager.readFile(db);
			return FileManager.writeFile(db, dados.replace(p.toString(), ""), false);
		}
		return false;
	}
	
	public static boolean update(Pedido p,Pedido novosDados)
	{
		if(connect())
		{
			String dados = FileManager.readFile(db);
			return FileManager.writeFile(db, dados.replace(p.toString(), novosDados.toString()), false);
		}
		
		return false;
	}
	
}
