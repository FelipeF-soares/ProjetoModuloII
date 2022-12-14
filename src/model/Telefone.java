package model;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Telefone telefone = (Telefone) o;
		// Adicionei esse replace para comparar com ou sem hífen (só comparar os dígitos)
		return Objects.equals(ddd, telefone.ddd) && Objects.equals(numeroTelefone.replace("-", ""),
				telefone.numeroTelefone.replace("-", ""));
	}

	@Override
	public int hashCode() {
		return Objects.hash(ddd, numeroTelefone);
	}
}
