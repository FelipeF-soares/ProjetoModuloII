package model;

import java.util.List;

public class Contato {
	
	private String nome;
	private String sobrenome;
	private List<Telefone> telefones;
	private List<Endereco> enderecos;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	@Override
	public String toString() {
		return "[" + "Nome: "+this.nome+" "+this.sobrenome+"\n[Telefones: "+this.telefones+"]\n[Endereços: "+this.enderecos+"]\n";
	}
	
}
