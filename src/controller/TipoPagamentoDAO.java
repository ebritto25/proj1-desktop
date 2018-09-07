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
			return FileManager.writeFile(db, tp.toString());
		}
		
		return false;
	}
	
	public static TipoPagamento queryByID(int id)
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
					return new TipoPagamento(registro);
			}
		}
		
		return null;
	}
	
	public static ArrayList<TipoPagamento> queryAll()
	{
		ArrayList<TipoPagamento> encontrados = new ArrayList<>();
		
		if(connect())
		{
			String dados = FileManager.readFile(db);
			String[] registros = dados.split("\n");
			
			for(String registro : registros)
			{
				encontrados.add(new TipoPagamento(registro));
			}
		}
		
		return encontrados;
	}
	
	public static boolean delete(TipoPagamento tp)
	{
		if(connect())
		{
			String dados = FileManager.readFile(db);
			return FileManager.writeFile(db, dados.replace(tp.toString(),""));
		}
		return false;
	}
	
	public static boolean update(TipoPagamento tp, TipoPagamento novosDados)
	{
		if(connect())
		{
			String dados = FileManager.readFile(db);
			return FileManager.writeFile(db, dados.replace(tp.toString(),novosDados.toString()));
		}
		return false;
	}
}
