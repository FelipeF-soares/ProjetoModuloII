package model;

import java.util.Objects;

public class Endereco {
	
		 private String cep;
		 private String logradouro;
		 private String numeroEndereco;
		 private String cidade;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Endereco endereco = (Endereco) o;
		// Adicionei esse replace para comparar com ou sem hífen (só comparar os dígitos)
		return Objects.equals(cep.replace("-",""), endereco.cep.replace("-","")) && Objects.equals(logradouro, endereco.logradouro) && Objects.equals(numeroEndereco, endereco.numeroEndereco) && Objects.equals(cidade, endereco.cidade) && Objects.equals(estado, endereco.estado);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cep, logradouro, numeroEndereco, cidade, estado);
	}

	private String estado;
	 
	 
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
		public String getNumeroEndereco() {
			return numeroEndereco;
		}
		public void setNumeroEndereco(String numeroEndereco) {
			this.numeroEndereco = numeroEndereco;
		}
		
		@Override
		public String toString() {
		return this.logradouro+" nº "+this.numeroEndereco+","+this.cidade+","+this.estado+","+this.cep;
		}
}
