package model;

import java.util.Date;

public class Pedido {
	private int id;
	private Date dataPedido;
	private int idCliente;
	private double total;
	private double desconto;
	private int idPagamento;
	private double troco;
	private double subTotal;

	public Pedido(){}

	public Pedido(int id, Date dataPedido, int idCliente, double total, double desconto, 
												int idPagamento, double troco,double subTotal) {
		this.id = id;
		this.dataPedido = dataPedido;
		this.idCliente = idCliente;
		this.total = total;
		this.desconto = desconto;
		this.idPagamento = idPagamento;
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

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
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

	public int getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
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
		return id + "," + dataPedido + "," + idCliente + "," + total + "," + desconto + "," 
																+ idPagamento + "," + troco + "," + subTotal;
	}
	
	
	
}
