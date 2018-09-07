package model;

import java.util.Date;

public class Pedido {
	private int id;
	private Date dataPedido;
	private Cliente cliente;
	private double total;
	private double desconto;
	private TipoPagamento pagamento;
	private double troco;
	private double subTotal;

	public Pedido(){}

	public Pedido(int id, Date dataPedido, Cliente cliente, double total, double desconto, 
												TipoPagamento pagamento, double troco,double subTotal) {
		this.id = id;
		this.dataPedido = dataPedido;
		this.cliente = cliente;
		this.total = total;
		this.desconto = desconto;
		this.pagamento = pagamento;
		this.troco = troco;
		this.subTotal = subTotal;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Cliente getIdCliente() {
		return cliente;
	}

	public void setIdCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public TipoPagamento getIdPagamento() {
		return pagamento;
	}

	public void setIdPagamento(TipoPagamento pagamento) {
		this.pagamento = pagamento;
	}

	public double getTroco() {
		return troco;
	}

	public void setTroco(double troco) {
		this.troco = troco;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	@Override
	public String toString() {
		return id + "," + dataPedido + "," + cliente.getId() + "," + total + "," + desconto + "," 
																+ pagamento.getId() + "," + troco + "," + subTotal + "\n";
	}
	
	
	
}
