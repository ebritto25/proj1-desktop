package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import model.ItensPedido;
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
			return FileManager.writeFile(db,ip,true);
		}
		return false;
	}
	
	public static ArrayList<ItensPedido> queryByPedido(int idPedido)
	{
		ArrayList<ItensPedido> encontrados = new ArrayList<>();
		if(connect())
		{
			ArrayList registros = FileManager.readFile(db);
			
			if(registros.isEmpty())
				return encontrados;
			
			for(Object registro : registros)
			{
				ItensPedido ip = (ItensPedido)registro;
				if(ip.getPedido().getId() == idPedido)
				{
					encontrados.add(ip);
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
			encontrados = FileManager.readFile(db);		
		}
		
		return encontrados;
	}
	
	public static boolean update(ItensPedido ip, ItensPedido novosDados)
	{
		if(connect())
		{
			ArrayList<ItensPedido> registros = FileManager.readFile(db);
			registros.set(registros.indexOf(ip), novosDados);
			
			for(int i = 0;i < registros.size();i++)
			{
				FileManager.writeFile(db, registros.get(i), (i!=0));
			}
			
			return true;
		}
	
		return false;
	}
	
	public static boolean delete(ItensPedido ip)
	{
		if(connect())
		{
			ArrayList<ItensPedido> registros = FileManager.readFile(db);
			registros.remove(ip);
			
			if(registros.isEmpty())
				return FileManager.deleteFile(db);
			
			for(int i = 0;i < registros.size();i++)
			{
				FileManager.writeFile(db, registros.get(i), (i!=0));
			}
			
		}
		return false;
	}
}
