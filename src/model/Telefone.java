package model;

public class Telefone {
	
	private String ddd;
    private String numeroTelefone;
    
    
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getNumeroTelefone() {
		return numeroTelefone;
	}
	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}
	
	@Override
	public String toString() {
		return this.ddd+" "+this.numeroTelefone;
	}
    
}
