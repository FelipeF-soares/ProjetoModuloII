package model;

public class Endereco {
	
		 private String cep;
		 private String logradouro;
		 private String numeroEndereco;
		 private String cidade;
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
