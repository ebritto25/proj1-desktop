package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import model.Produto;
import model.TipoProduto;
import utils.FileManager;

public class ProdutoDAO {
	private static File db;
	
	ProdutoDAO(){}
	
	private static boolean connect()
	{
		try{
                    db = Database.getInstance().getDatabaseProdutos();		
		}
		catch(IOException ex)
		{
                    System.err.println(ex.getMessage());
                    return false;
		}
		
		return true;
	}
	/*
	private static Produto produtoBuilder(String registro)
	{
		String [] r_dados = registro.split(",");
		TipoProduto tProduto = TipoProdutoDAO.queryByID(Integer.parseInt(r_dados[3]));
		
		if(tProduto != null)
			return new Produto(registro,tProduto);
		else
			return null;
	}*/
	
	public static boolean insert(Produto p)
	{
		if(connect())
		{
			return FileManager.writeFile(db, p,true);
		}
		return false;
	}
	
	public static Produto queryByID(int id)
	{
		
		if(connect())
		{
			ArrayList<Produto> registros = FileManager.readFile(db);

			for(Object registro : registros)
			{
				Produto p = (Produto)registro;
				if(p.getId() == id)
				{
					return p;
				}
			}
		}
		return null;
	}
	
	public static ArrayList<Produto> queryAll()
	{
		ArrayList<Produto> encontrados = new ArrayList<>();
		
		if(connect())
		{
			encontrados = FileManager.readFile(db);
		}
		
		return encontrados;
	}
	
	public static ArrayList<Produto> queryByDescricao(String descricao)
	{
		ArrayList<Produto> encontrados = new ArrayList<>();
		
		if(connect())
		{
			ArrayList<Produto> registros = FileManager.readFile(db);
			
			if(registros.isEmpty())
				return encontrados;
			
			for(Object registro : registros)
			{
				Produto p = (Produto)registro;
				if(p.getDescricao().toUpperCase().contains(descricao.toUpperCase()))
				{
					encontrados.add(p);
				}
			}
		}
		
		return encontrados;
	}
	
	public static boolean delete(Produto prod)
	{
		if(connect())
		{
			ArrayList<Produto> registros = FileManager.readFile(db);	
			registros.remove(prod);
			
			FileManager.deleteFile(db);
			
			for(int i = 0;i < registros.size();i++)
			{
				FileManager.writeFile(db, registros.get(i), (i!=0));
			}
			return true;
		}
		return false; 
	}
	
	public static boolean update(Produto prod, Produto novoProduto)
	{
		if(connect())
		{
			ArrayList<Produto> registros = FileManager.readFile(db);
			registros.set(registros.indexOf(prod), novoProduto);
			
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
