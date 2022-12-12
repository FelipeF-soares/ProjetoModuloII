package dto;

import java.util.List;

import erros.ValorVazioError;
import model.Agenda;
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
	 
	 
	 public Contato adicionarContato(List<Endereco> listaEndereco, List<Telefone> listaTelefone) {
		 Contato contato = new Contato();
		 contato.setNome(nome);
		 contato.setSobrenome(sobrenome);
		 contato.setEnderecos(listaEndereco);
		 contato.setTelefones(listaTelefone);
		 
		 return contato; 
	 }
	 
	 public Endereco adicionaEndereco(List<Endereco> listaEndereco) {
		 Endereco endereco = new Endereco(); 
		 endereco.setLogradouro(logradouro);
		 endereco.setNumeroEndereco(numeroEndereco);
		 endereco.setCep(cep);
		 endereco.setCidade(cidade);
		 endereco.setEstado(estado);
		 int count=0;

		 if(listaEndereco != null && !listaEndereco.isEmpty()){
			 for (int i=0; i<listaEndereco.size(); i++){
				 if(endereco.equals(listaEndereco.get(i))){
					 count++;
				 }
			 }
		 }
		 if(count>0){
			 throw new IllegalArgumentException("[ERRO] Endereço  já existe!\n");
		 }

		 return endereco;
	 }
	 
	 public Telefone adicionaTelefone(List<Telefone> listaTelefones){
		 Telefone telefone = new Telefone();
		 telefone.setDdd(ddd);
		 telefone.setNumeroTelefone(numeroTelefone);
		 int count=0;

		 if(listaTelefones != null && !listaTelefones.isEmpty()){
			 for (int i=0; i<listaTelefones.size(); i++){
				 if(telefone.equals(listaTelefones.get(i))){
					 count++;
				 }
			 }
		 }
		 if(count>0){
			 throw new IllegalArgumentException("[ERRO] Telefone  já existe!\n");
		 }

		 return telefone;
	 }
	  
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) throws ValorVazioError {
		if(nome.isBlank()|| nome.isEmpty()) {
			throw new ValorVazioError();
		}else {
			this.nome = nome.toUpperCase().trim();
		}
		
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
		boolean seguePadrao = (cep.matches("\\d\\d\\d\\d\\d-\\d\\d\\d") ||
				cep.matches("\\d\\d\\d\\d\\d\\d\\d\\d")
		);
		if(seguePadrao){
			this.cep = cep;
		} else {
			throw new IllegalArgumentException("Nº do cep deve ter 8 números, com ou sem hífen");
		}
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
	public void setDdd(String ddd){
		boolean seguePadrao = (ddd.matches("\\d\\d"));
		if(seguePadrao){
			this.ddd = ddd;
		} else {
			throw new IllegalArgumentException("DDD do telefone deve ter 2 números");
		}
	}
	public String getNumeroTelefone() {
		return numeroTelefone;
	}
	public void setNumeroTelefone(String numeroTelefone) {
		boolean seguePadrao = (numeroTelefone.matches("\\d\\d\\d\\d\\d-\\d\\d\\d\\d") ||
				numeroTelefone.matches("\\d\\d\\d\\d\\d\\d\\d\\d\\d") ||
				numeroTelefone.matches("\\d\\d\\d\\d-\\d\\d\\d\\d") ||
				numeroTelefone.matches("\\d\\d\\d\\d\\d\\d\\d\\d")
		);
		if(seguePadrao){
			this.numeroTelefone = numeroTelefone;
		} else {
			throw new IllegalArgumentException("Nº do telefone deve ter 8 ou 9 números, com ou sem hífen");
		}
	}

}
