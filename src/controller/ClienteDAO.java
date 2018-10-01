
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
			return FileManager.writeFile(db, cli,true);
		}
		
		return false;
	}
	
	public static Cliente queryByID(int id)
	{
		
		if(connect())
		{
			ArrayList registros = FileManager.readFile(db);
			
			if(registros.isEmpty())
				return null;
			
			for(Object r : registros)
			{
				Cliente c = (Cliente)r;
				if(id == c.getId())
					return c;
			}
			
		}
		
		return null;
	}
	
	public static ArrayList<Cliente> queryAll()
	{
		ArrayList<Cliente> clientes = new ArrayList<>();
		
		if(connect())
		{
			clientes = FileManager.readFile(db);                     
		}
				
		return clientes;
	}
	
	public static ArrayList<Cliente> queryByNome(String nome)
	{
		ArrayList<Cliente> encontrados = new ArrayList<>();
		
		if(connect())
		{
			ArrayList registros = FileManager.readFile(db);
			
			if(registros.isEmpty())
				return encontrados;
			
			for(Object registro : registros)
			{
				Cliente c = (Cliente) registro;
				
				if(c.getNome().toUpperCase().contains(nome.toUpperCase()))
					encontrados.add(c);
			}
		}
		
		return encontrados;
	}
	
	public static boolean delete(Cliente cli)
	{
		if(connect())
		{
			ArrayList registros = FileManager.readFile(db);
			registros.remove(cli);
			
			FileManager.deleteFile(db);
			
			for(int i = 0;i < registros.size();i++)
			{
				FileManager.writeFile(db, registros.get(i),(i!=0));
			}
			return true;
			
		}
		
		return false;
	}
	
	public static boolean update(Cliente cli,Cliente novosDados)
	{
		if(connect())
		{
			ArrayList registros = FileManager.readFile(db);
			registros.set(registros.indexOf(cli), novosDados);
			
			FileManager.deleteFile(db);
			
			for(int i = 0;i < registros.size();i++)
			{
				FileManager.writeFile(db, registros.get(i),(i!=0));
			}
			return true;
			
		}
		return false;
	}
	
}
