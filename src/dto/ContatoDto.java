package dto;

import java.util.ArrayList;
import java.util.List;

import model.Contato;
import model.Endereco;
import model.Telefone;

public class ContatoDto {
	
	 private String nome;
	 private String sobrenome;
	 private String cep;
	 private String logradouro;
	 private String numeroEndereco;
	 private String cidade;
	 private String estado;
	 private String ddd;
	 private String numeroTelefone;
	 
	 public Contato adicionarContato() {
		 Contato contato = new Contato();
		 Endereco endereco = new Endereco();
		 Telefone telefone = new Telefone();
		 List<Endereco> listaEndereco = new ArrayList<Endereco>(); 
		 List<Telefone> listaTelefone = new ArrayList<Telefone>(); 
		 
		 contato.setNome(nome);
		 contato.setSobrenome(sobrenome);
		 
		 endereco.setLogradouro(logradouro);
		 endereco.setNumeroEndereco(numeroEndereco);
		 endereco.setCep(cep);
		 endereco.setCidade(cidade);
		 endereco.setEstado(estado);
		 listaEndereco.add(endereco);
		 
		 telefone.setDdd(ddd);
		 telefone.setNumeroTelefone(numeroTelefone);
		 listaTelefone.add(telefone);
		 
		 contato.setEnderecos(listaEndereco);
		 contato.setTelefones(listaTelefone);
		 
		 
		 
		 return contato; 
	 }
	
	 
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome.toUpperCase().trim();
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome.toUpperCase().trim();
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumeroEndereco() {
		return numeroEndereco;
	}
	public void setNumeroEndereco(String numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) throws Exception {
		Integer.valueOf(ddd);
		this.ddd = ddd;
	}
	public String getNumeroTelefone() {
		return numeroTelefone;
	}
	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

}
