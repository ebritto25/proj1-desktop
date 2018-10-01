    
package model;

import java.io.Serializable;

public class Produto implements Serializable{
    
	private static final long serialVersionUID = 1335421L;

    
	private int id;
	private String descricao;
	private double preco;
	private TipoProduto tipo;

	
	public Produto(){}
	
	public Produto(String produto, TipoProduto tProduto)
	{
		String[] dados = produto.trim().split(",");
		
		this.id = Integer.parseInt(dados[0]);
		this.descricao = dados[1];
		this.preco = Double.parseDouble(dados[2]);
		this.tipo = tProduto;
	}
	
	public Produto(int id, String descricao, double preco, TipoProduto tipo) {
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
		this.tipo = tipo;
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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public TipoProduto getTipo() {
		return tipo;
	}

	public void setTipo(TipoProduto tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return id + "," + descricao + "," + preco + "," + tipo.getId()+"\n";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || getClass() != obj.getClass())
			return false;
		
		Produto p = (Produto)obj;
		return this.id == p.getId();
	}
	
	
	
}
