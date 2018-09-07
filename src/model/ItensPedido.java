package model;

public class ItensPedido {
	private Produto produto;
	private Pedido pedido;
	private int quantidade;
	
	public ItensPedido(){}

	public ItensPedido(Produto produto, Pedido pedido,int quantidade) {
		this.produto = produto;
		this.pedido = pedido;
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	
	@Override
	public String toString() {
		return produto.getId() + "," + pedido.getId() + "," + quantidade+"\n";
	}
	
}
