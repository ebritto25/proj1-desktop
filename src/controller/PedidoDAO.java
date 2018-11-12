package controller;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Pedido;
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
		if(connect())
		{
			return FileManager.writeFile(db, ped,true);
		}
		return false;
	}
	
	public static Pedido queryByID(int id)
	{
		if(connect())
		{
			ArrayList<Pedido> registros = FileManager.readFile(db);
			
			if(registros.isEmpty())
				return null;
			
			for(Object registro : registros)
			{
				Pedido p = (Pedido)registro;
				if(p.getId() == id)
				{
					return p;
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
			encontrados = FileManager.readFile(db);
		}
		
		return encontrados;
	}
	
	public static boolean delete(Pedido p)
	{
		if(connect())
		{
			ArrayList registros = FileManager.readFile(db);
			registros.remove(p);
			
			FileManager.deleteFile(db);
			
			for(int i = 0;i < registros.size();i++)
			{
				FileManager.writeFile(db, registros.get(i), (i!=0));
			}
			return true;
		}
		return false;
	}
	
	public static boolean update(Pedido p,Pedido novosDados)
	{
		if(connect())
		{
			ArrayList registros = FileManager.readFile(db);
			registros.set(registros.indexOf(p),novosDados);
		
			FileManager.deleteFile(db);
			
			for(int i = 0;i < registros.size();i++)
			{
				FileManager.writeFile(db, registros.get(i), (i!=0));
			}
			return true;
		}
		
		return false;
	}
	
}
