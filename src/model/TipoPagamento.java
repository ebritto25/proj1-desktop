package model;

public class TipoPagamento {
	private int id;
	private String descricao;
	
	
	public TipoPagamento(String tp)
	{
		String[] dados = tp.split(",");
		this.id = Integer.parseInt(dados[0]);
		this.descricao = dados[1];
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
			
}

