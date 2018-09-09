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
	
	private static Produto produtoBuilder(String registro)
	{
		String [] r_dados = registro.split(",");
		TipoProduto tProduto = TipoProdutoDAO.queryByID(Integer.parseInt(r_dados[3]));
		
		if(tProduto != null)
			return new Produto(registro,tProduto);
		else
			return null;
	}
	
	public static boolean insert(Produto p)
	{
		if(connect())
		{
			return FileManager.writeFile(db, p.toString(),true);
		}
		return false;
	}
	
	public static Produto queryByID(int id)
	{
		
		if(connect())
		{
			String dados = FileManager.readFile(db);
			String[] registros = dados.split("\n");
			
			if(registros[0].equals(""))
				return null;
			
			for(String registro : registros)
			{
				String[] r_dados = registro.split(",");
				int r_id = Integer.parseInt(r_dados[0]);
				if(r_id == id)
				{
					return produtoBuilder(registro);
				}
					
			}
		}
		return null;
	}
	
	public static ArrayList<Produto> queryAll()
	{
		ArrayList<Produto> produtos = new ArrayList<>();
		
		if(connect())
		{
			String dados = FileManager.readFile(db);
			String[] registros = dados.split("\n");
			
			if(registros[0].equals(""))
				return produtos;
			
			for(String registro : registros)
			{
				Produto prod = produtoBuilder(registro);
				if(prod != null)
					produtos.add(prod);
			}
		}
		
		return produtos;
	}
	
	public static ArrayList<Produto> queryByDescricao(String descricao)
	{
		ArrayList<Produto> encontrados = new ArrayList<>();
		
		if(connect())
		{
			String dados = FileManager.readFile(db);
			String[] registros = dados.split("\n");
			
			if(registros[0].equals(""))
				return encontrados;
			
			for(String registro : registros)
			{
				String[] r_dados = registro.split(",");
				String r_desc = r_dados[1];
				
				if(r_desc.contains(descricao))
				{
					Produto prod = produtoBuilder(registro);
					if(prod != null)
						encontrados.add(prod);
				}
			}
		}
		
		return encontrados;
	}
	
	public static boolean delete(Produto prod)
	{
		if(connect())
		{
			String dados = FileManager.readFile(db);	
			return FileManager.writeFile(db, dados.replace(prod.toString(), ""),false);
		}
		
		return false; 
	}
	
	public static boolean update(Produto prod, Produto novoProduto)
	{
		if(connect())
		{
			String dados = FileManager.readFile(db);
			return FileManager.writeFile(db, dados.replace(prod.toString(), novoProduto.toString()),false);
		}
		return false;
	}
	
}
