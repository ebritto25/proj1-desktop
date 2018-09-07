
package model;

public class Cliente {
	private int id;
	private String nome;
	private String telefone;
	private String endereco;
	private String bairro;
	private String cep;
	
	public Cliente(){}
	
	public Cliente(String cliente)
	{
		String[] dados = cliente.trim().split(",");
		
		this.id = Integer.parseInt(dados[0]);
		this.nome = dados[1];
		this.telefone = dados[2];
		this.endereco = dados[3];
		this.bairro = dados[4];
		this.cep = dados[5];
	}
	
	public Cliente(int id, String nome, String telefone,String endereco,String bairro, String cep)
	{
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.bairro = bairro;
		this.cep = cep;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return id + "," + nome + "," + telefone + "," + endereco + "," + bairro + "," + cep + "\n";
	}
	
	
	
}
