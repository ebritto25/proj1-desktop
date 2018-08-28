package model;

public class ItensPedido {
	private int idProduto;
	private int idPedido;
	
	public ItensPedido(){}

	public ItensPedido(int idProduto, int idPedido) {
		this.idProduto = idProduto;
		this.idPedido = idPedido;
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

	@Override
	public String toString() {
		return idProduto + "," + idPedido;
	}
	
}
