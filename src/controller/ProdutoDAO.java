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
			
			for(String registro : registros)
			{
				String[] r_dados = registro.split(",");
				int r_id = Integer.parseInt(r_dados[0]);
				if(r_id == id)
				{
					TipoProduto tProduto = TipoProdutoDAO.queryByID(Integer.parseInt(r_dados[3]));
					return new Produto(registro,tProduto);
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
			
                        for(String registro : registros)
                        {
                                String[] r_dados = registro.split(",");
                                TipoProduto tProduto = TipoProdutoDAO.queryByID(Integer.parseInt(r_dados[0]));
                                produtos.add(new Produto(registro,tProduto));
                        }
		}
		
		return produtos;
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
