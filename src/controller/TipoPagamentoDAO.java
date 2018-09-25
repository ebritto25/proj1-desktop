package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import model.TipoPagamento;
import utils.FileManager;

public class TipoPagamentoDAO {
	private static File db;
	
	public TipoPagamentoDAO(){}
	
	private static boolean connect()
	{ 
		try
		{
			db = Database.getInstance().getDatabaseTipoPagamento();
		}
		catch(IOException ex)
		{
			System.err.println(ex.toString());
			return false;
		}
		
		return true;
	}
	
	public static boolean insert(TipoPagamento tp)
	{
		if(connect())
		{
			return FileManager.writeFile(db, tp,true);
		}
		
		return false;
	}
	
	public static TipoPagamento queryByID(int id)
	{
		if(connect())
		{
			ArrayList<TipoPagamento> registros = FileManager.readFile(db);
			
			if(registros.isEmpty())
				return null;
			
			for(Object registro : registros)
			{
				TipoPagamento tp = (TipoPagamento)registro;
				if(tp.getId() == id)
					return tp;
			}
		}
		return null;
	}
	
	public static ArrayList<TipoPagamento> queryAll()
	{
		ArrayList<TipoPagamento> encontrados = new ArrayList<>();
		
		if(connect())
		{
			encontrados = FileManager.readFile(db);
		}
		
		return encontrados;
	}
	
	public static ArrayList<TipoPagamento> queryByDescricao(String descricao)
	{
		ArrayList<TipoPagamento> encontrados = new ArrayList<>();
		
		if(connect())
		{
			ArrayList<TipoPagamento> registros = FileManager.readFile(db);
			
			if(registros.isEmpty())
				return encontrados;
			
			for(Object registro : registros)
			{
				TipoPagamento tp = (TipoPagamento)registro;
				if(tp.getDescricao().toUpperCase().contains(descricao.toUpperCase()))
					encontrados.add(tp);
			}
		}
		
		return encontrados;
	}
	
	public static boolean delete(TipoPagamento tp)
	{
		if(connect())
		{
			ArrayList<TipoPagamento> registros = FileManager.readFile(db);
			registros.remove(tp);
			
			if(registros.isEmpty())
				FileManager.deleteFile(db);
			
			for(int i = 0;i < registros.size();i++)
			{
				FileManager.writeFile(db, registros.get(i), (i!=0));
			}
			
			return true;
			
		}
		return false;
	}
	
	public static boolean update(TipoPagamento tp, TipoPagamento novosDados)
	{
		if(connect())
		{
			ArrayList<TipoPagamento> registros = FileManager.readFile(db);
			registros.set(registros.indexOf(tp), novosDados);
			
			for(int i = 0;i < registros.size();i++)
			{
				FileManager.writeFile(db, registros.get(i), (i!=0));
			}
			return true;
		}
		return false;
	}
}
