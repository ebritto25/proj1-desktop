package model;

public class ItensPedido {
	private int idProduto;
	private int idPedido;
	private int quantidade;
	
	public ItensPedido(){}

	public ItensPedido(int idProduto, int idPedido,int quantidade) {
		this.idProduto = idProduto;
		this.idPedido = idPedido;
		this.quantidade = quantidade;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	
	
	@Override
	public String toString() {
		return idProduto + "," + idPedido + "," + quantidade;
	}
	
}
