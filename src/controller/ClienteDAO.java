
package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import model.Cliente;
import utils.FileManager;


public class ClienteDAO {

	public static File db;

	public ClienteDAO()
	{
		
	}
	
	private static boolean connect()
	{
		try{
			db = Database.getInstance().getDatabaseClientes();		
		}
		catch(IOException ex)
		{
			System.err.println(ex.getMessage());
			return false;
		}
		
		return true;
	}
	
	public static boolean insert(Cliente cli)
	{
		if(connect())
		{
			return FileManager.writeFile(db, cli.toString());
		}
		
		return false;
	}
	
	public static Cliente queryByID(int id)
	{
		
		if(connect())
		{
			String[] registros = FileManager.readFile(db).split("\n");
			if(registros.length > 0)
			{
				for(String r : registros)
				{
					String[] dadosCliente = r.split(",");
					int r_id = Integer.parseInt(dadosCliente[0]);
					if(id == r_id)
						return new Cliente(r);
				}
			}
		}
		
		return null;
	}
	
	public static ArrayList<Cliente> queryAll()
	{
		ArrayList<Cliente> clientes = new ArrayList<>();
		
		if(connect())
		{
			String[] registros = FileManager.readFile(db).split("\n");
                        
                        
			if(registros.length > 0 && !registros[0].equals(""))
			{
				for(String r : registros)
				{
					clientes.add(new Cliente(r));
				}
			}
                        
		}
				
		return clientes;
	}
	
	public static boolean delete(Cliente cli)
	{
		if(connect())
		{
			String dados = FileManager.readFile(db);
			String novosRegistros = dados.replace(cli.toString(),"");
			
			return FileManager.writeFile(db, novosRegistros);
			
		}
		
		return false;
	}
	
	public static boolean update(Cliente cli,Cliente novosDados)
	{
		if(connect())
		{
			String dados = FileManager.readFile(db);
			String novosRegistros = dados.replace(cli.toString(),novosDados.toString());
			
			return FileManager.writeFile(db, novosRegistros);
			
		}
		return false;
	}
	
}
