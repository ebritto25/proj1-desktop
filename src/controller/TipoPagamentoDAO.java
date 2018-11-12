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
                    statement.setInt(1, id);
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
                        
                        
                        while(results.next())
                        {
                            TipoPagamento tp = new TipoPagamento();
                            
                            tp.setId(results.getInt(1));
                            tp.setDescricao(results.getString(2));
                            
                            encontrados.add(tp);
                            
                        }
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
			statement.setString(1, "%"+descricao+"%");
                        results = statement.executeQuery();
                        
                        
                        while(results.next())
                        {
                            TipoPagamento tp = new TipoPagamento();
                            
                            tp.setId(results.getInt(1));
                            tp.setDescricao(results.getString(2));
                            
                            encontrados.add(tp);
                            
                        }
                }
                catch(SQLException ex)
                {
                        System.err.println("Erro ao buscar por ID: " + ex);
                }

		
		return encontrados;
	}
	
	public static boolean delete(TipoPagamento tp)
	{
		connect();
		try
		{
			String query = "delete from tipo_produto where id_tpagamento = ?";
			statement = configureStatement(query);
			statement.setInt(1, tp.getId());
			statement.executeUpdate();
			db.getConnection().commit();
			return true;
		}
		catch(SQLException ex)
		{
			System.err.println("Erro no delete do TipoProduto: " + ex.getMessage());
		}
		
		return false;
	}
	
	public static boolean update(TipoPagamento tp, TipoPagamento novosDados)
	{
		connect();
		try
		{
			String query = "update tipo_pagamento set descricao_tpagamento = ? where id_tpagamento = ?";
			statement = configureStatement(query);
			statement.setString(1, novosDados.getDescricao());
			statement.setInt(2, tp.getId());
			statement.executeUpdate();
			db.getConnection().commit();
			return true;
		}
		catch(SQLException ex)
		{
			System.err.println("Erro no update do TipoProduto: " + ex.getMessage());
		}
		
		return false;
	}
}
