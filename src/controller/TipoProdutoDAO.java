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
			return FileManager.writeFile(db, tp.toString());
		}
		
		return false;
	}
	
	public static TipoProduto queryByID(int id)
	{

		if(connect())
		{
			String dados = FileManager.readFile(db);
			String[] registros = dados.split("\n");
			
			for(String registro : registros)
			{
				String[] dadosRegistro = registro.split(",");
				int r_id = Integer.parseInt(dadosRegistro[0]);

				if(r_id == id)
					return new TipoProduto(registro);				
			}		
		}
		
		return null;
	}
	
	public static ArrayList<TipoProduto> queryAll()
	{
		
                ArrayList<TipoProduto> encontrados = new ArrayList<>();
		
		if(connect())
		{
			String dados = FileManager.readFile(db);
                        
			String[] registros = dados.split(",");
                      
			for(String registro : registros)
			{
				encontrados.add(new TipoProduto(registro));
			}
		}
		
		return encontrados;
	}
	
	public static boolean update(TipoProduto tp, TipoProduto novosDados)
	{
		
		if(connect())
		{
			String dados = FileManager.readFile(db);
			return FileManager.writeFile(db, dados.replace(tp.toString(), novosDados.toString()));
		}
		
		return false;
	}
		
	public static boolean delete(TipoProduto tp)
	{
		
		if(connect())
		{
			String dados = FileManager.readFile(db);
			return FileManager.writeFile(db, dados.replace(tp.toString(), ""));
		}
		
		return false;
	}
}
