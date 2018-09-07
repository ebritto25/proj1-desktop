package controller;

import java.io.File;
import java.io.IOException;
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
			fileClientes = checaFile("clientesDatabase.csv");
		}

		return fileClientes;
	}
	
	public File getDatabaseProdutos() throws IOException
	{
		if(fileProdutos == null)
		{
			fileProdutos = checaFile("produtosDatabase.csv");
		}
		
		return fileProdutos;
	}
	
	public File getDatabaseTipoProduto() throws IOException
	{
		if(fileTipoProduto == null)
		{
			fileTipoProduto = checaFile("tipoProdutoDatabase.csv");
			String iniciais = "1,Refrigerante\n2,Comida";
			FileManager.writeFile(fileTipoProduto, iniciais,false);
		}
		
		return fileProdutos;
	}
	
	public File getDatabaseTipoPagamento() throws IOException
	{
		if(fileTipoPagamento == null)
		{
			fileTipoPagamento = checaFile("tipoPagamentoDatabase.csv");
			String iniciais = "1,Cartão\n2,Dinheiro";
			FileManager.writeFile(fileTipoPagamento, iniciais,false);
		}
		
		return fileTipoPagamento;
	}
	
	public File getDatabasePedido() throws IOException
	{
		if(filePedido == null)
		{
			filePedido = checaFile("pedidoDatabase.csv");
		}
		
		return filePedido;
	}
	
	public File getDatabaseItensPedido() throws IOException
	{
		if(fileItensPedido == null)
		{
			fileItensPedido = checaFile("itensPedidoDatabase.csv");
		}
		
		return fileItensPedido;
	}
	
	
	
}
