package controller;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.TipoPagamento;
import utils.FileManager;

public class TipoPagamentoDAO {
	private static Database db;
        	private static PreparedStatement statement;
	private static ResultSet results = null;
	
	
	public TipoPagamentoDAO(){}
	
	private static void connect()
	{ 
            db = Database.getInstance();
	}
        
        private static PreparedStatement configureStatement(String sql) throws SQLException
	{
		return db.getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	}
	
	public static boolean insert(TipoPagamento tp)
	{
                connect();
		try{
			String query = "insert into tipo_pagamento (descricao_tpagamento) values (?)";
			statement = configureStatement(query);
			statement.setString(1, tp.getDescricao());
			statement.executeUpdate();
			db.getConnection().commit();
		}
		catch(SQLException ex){
			System.err.println("Erro na inserção: " + ex.getMessage());
			return false;
		}
		
		return true;
	}
	
	public static TipoPagamento queryByID(int id)
	{
            TipoPagamento tp = new TipoPagamento();

            connect();
            try
            {
                    String query = "select * from tipo_pagamento where id_tpagamento = ?";
                    statement = configureStatement(query);
                    results = statement.executeQuery();
                    if(!results.isFirst())
                    {
                            tp.setId(results.getInt(1));
                            tp.setDescricao(results.getString(2));
                    }
            }
            catch(SQLException ex)
            {
                    System.err.println("Erro ao buscar por ID: " + ex);
            }

            return tp;
	}
	
	public static ArrayList<TipoPagamento> queryAll()
	{
		ArrayList<TipoPagamento> encontrados = new ArrayList<>();
		
                connect();
                try
                {
                        String query = "select * from tipo_pagamento";
                        statement = configureStatement(query);
                        results = statement.executeQuery();
                        
                        
                        do
                        {
                            TipoPagamento tp = new TipoPagamento();
                            
                            tp.setId(results.getInt(1));
                            tp.setDescricao(results.getString(2));
                            
                            encontrados.add(tp);
                            
                        }while(results.next());
                }
                catch(SQLException ex)
                {
                        System.err.println("Erro ao buscar por ID: " + ex);
                }

		
		return encontrados;
	}
	
	public static ArrayList<TipoPagamento> queryByDescricao(String descricao)
	{
		ArrayList<TipoPagamento> encontrados = new ArrayList<>();
		
                connect();
                try
                {
                        String query = "select * from tipo_pagamento where descricao_tpagamento like ?";
                        statement = configureStatement(query);
                        results = statement.executeQuery();
                        
                        
                        do
                        {
                            TipoPagamento tp = new TipoPagamento();
                            
                            tp.setId(results.getInt(1));
                            tp.setDescricao(results.getString(2));
                            
                            encontrados.add(tp);
                            
                        }while(results.next());
                }
                catch(SQLException ex)
                {
                        System.err.println("Erro ao buscar por ID: " + ex);
                }

		
		return encontrados;
	}
	
	public static boolean delete(TipoPagamento tp)
	{
		if(connect())
		{
			ArrayList<TipoPagamento> registros = FileManager.readFile(db);
			registros.remove(tp);
			
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
