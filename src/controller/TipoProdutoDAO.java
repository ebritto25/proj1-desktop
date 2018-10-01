package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import model.TipoProduto;
import utils.FileManager;

public class TipoProdutoDAO {
	private static File db;
	
	public TipoProdutoDAO(){}
	
	private static boolean connect()
	{
		try
		{
			db = Database.getInstance().getDatabaseTipoProduto();
			
		}
		catch(IOException ex)
		{
			System.err.println(ex.getMessage());
			return false;
		}
		
		return true;
	}
	
	public static boolean insert(TipoProduto tp)
	{
		if(connect())
		{
			return FileManager.writeFile(db, tp,true);
		}
		
		return false;
	}
	
	public static TipoProduto queryByID(int id)
	{

		if(connect())
		{
			ArrayList<TipoProduto> registros = FileManager.readFile(db);
			
			if(registros.isEmpty())
				return null;
			
			for(Object registro : registros)
			{
				TipoProduto tp = (TipoProduto)registro;
				if(tp.getId() == id)
					return tp;				
			}		
		}
		return null;
	}
	
	public static ArrayList<TipoProduto> queryAll()
	{
		
        ArrayList<TipoProduto> encontrados = new ArrayList<>();
		
		if(connect())
		{
			encontrados = FileManager.readFile(db);                  
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
