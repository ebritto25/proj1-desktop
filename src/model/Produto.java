
package model;

public class Produto {
	private int id;
	private String descricao;
	private double preco;
	private int tipo;

	
	public Produto(){}
	
	public Produto(int id, String descricao, double preco, int tipo) {
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

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return id + "," + descricao + "," + preco + "," + tipo;
	}
	
	
	
}
