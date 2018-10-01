package model;

import java.io.Serializable;

public class TipoPagamento implements Serializable{
    
    
	private static final long serialVersionUID = 1335421L;

    
	private int id;
	private String descricao;
	
	
	public TipoPagamento(String tp)
	{
		String[] dados = tp.split(",");
		this.id = Integer.parseInt(dados[0]);
		this.descricao = dados[1];
	}
	
	public TipoPagamento(int id,String descricao)
	{
		this.id = id;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}			

	@Override
	public String toString() {
		return id + "," + descricao + "\n";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || getClass() != obj.getClass())
			return false;
		
		TipoPagamento t = (TipoPagamento)obj;
		return this.id == t.getId();
	}
}

