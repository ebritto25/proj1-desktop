package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import model.TipoPagamento;
import model.TipoProduto;
import utils.FileManager;

public class Database {
	private File fileClientes = null;
	private File fileProdutos = null;
	private File fileTipoProduto = null;
	private File fileTipoPagamento = null;
	private File filePedido = null;
	private File fileItensPedido = null;
	private static Database instance = null;
	
	private Database()
	{
		try
		{
			getDatabaseClientes();
			getDatabaseItensPedido();
			getDatabasePedido();
			getDatabaseProdutos();
			getDatabaseTipoPagamento();
			getDatabaseTipoProduto();
		}
		catch(IOException ex)
		{
			System.err.println(ex.getMessage());
		}
	}
		
	public static Database getInstance()
	{
		if(instance == null)
			instance = new Database();
		
		return instance;
	}
	
	private File checaFile(String file) throws IOException
	{
		File databaseFile = null;
		
			File homeDir = new File(System.getProperty("user.home"));
			File databaseDir = new File(homeDir,"ProjDesktop_database/");
			if(!databaseDir.exists())
			{
				if(!databaseDir.mkdir())
					throw new IOException("Impossível criar diretorio de database!");
				
				databaseFile = new File(databaseDir,file);
				
				if(!databaseFile.createNewFile())
					throw new IOException("Problema ao criar arquivo de database!");
				
				return databaseFile;
			}
			else
			{
				databaseFile = new File(databaseDir,file);
				
				if(!databaseFile.exists())
				{
					if(!databaseFile.createNewFile())
						throw new IOException("Problema ao criar arquivo de database!");
				}
				
				return databaseFile;
			}
	}
	
	public File getDatabaseClientes() throws IOException
	{
		if(fileClientes == null)
		{
			fileClientes = checaFile("clientesDatabase.dat");
		}

		return fileClientes;
	}
	
	public File getDatabaseProdutos() throws IOException
	{
		if(fileProdutos == null)
		{
			fileProdutos = checaFile("produtosDatabase.dat");
		}
		
		return fileProdutos;
	}
	
	public File getDatabaseTipoProduto() throws IOException
	{
		if(fileTipoProduto == null)
		{
			fileTipoProduto = checaFile("tipoProdutoDatabase.dat");
			
			ArrayList<TipoProduto> p = new ArrayList();
			
			p.add(new TipoProduto(1,"Refrigerante"));
			p.add(new TipoProduto(2,"Comida"));
			
			for(int i = 0;i < p.size();i++)	
				FileManager.writeFile(fileTipoProduto, p.get(i),(i!=0));
			
		}
		
		return fileTipoProduto;
	}
	
	public File getDatabaseTipoPagamento() throws IOException
	{
		if(fileTipoPagamento == null)
		{
			fileTipoPagamento = checaFile("tipoPagamentoDatabase.dat");
			
			ArrayList<TipoPagamento> p = new ArrayList();
			
			p.add(new TipoPagamento(1,"Cartão"));
			p.add(new TipoPagamento(2,"Dinheiro"));
			
			for(int i = 0;i < p.size();i++)	
				FileManager.writeFile(fileTipoPagamento, p.get(i),(i!=0));
			
		}
		
		return fileTipoPagamento;
	}
	
	public File getDatabasePedido() throws IOException
	{
		if(filePedido == null)
		{
			filePedido = checaFile("pedidoDatabase.dat");
		}
		
		return filePedido;
	}
	
	public File getDatabaseItensPedido() throws IOException
	{
		if(fileItensPedido == null)
		{
			fileItensPedido = checaFile("itensPedidoDatabase.dat");
		}
		
		return fileItensPedido;
	}
}
