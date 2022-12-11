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
	
	@Override
	public String toString() {
		return this.ddd+" "+this.numeroTelefone;
	}
    
}
