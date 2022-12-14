package dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import model.Contato;
import model.Endereco;
import model.Telefone;

public class AgendaDao {
	
	Path path = Paths.get("C:\\Temp\\agenda.txt");
	
	public void salvarContato(String contatoString) throws IOException {
		this.preparaArquivo();
		Files.writeString(path, contatoString, StandardOpenOption.APPEND);
	}
	
	public List<Contato>listarContatos() throws IOException{
		List<Contato> listaContato = new ArrayList<Contato>();
		List<Telefone> listaTelefone = new ArrayList<Telefone>();
		List<Endereco> listaEndereco = new ArrayList<Endereco>();
		List<String> listaString = Files.readAllLines(path);
			for(int i = 0; i<listaString.size(); i++) {
				Contato contato = new Contato();
				
				String[] nomeTelefoneEndereco = listaString.get(i).split("\\*");
				String[] nome = nomeTelefoneEndereco[0].split("-");
				contato.setNome(nome[1]);
				contato.setSobrenome(nome[2]);
				if(nomeTelefoneEndereco[1].contains("#")) {
					String[] telefones = nomeTelefoneEndereco[1].split("#");
					for(int j = 1; j < telefones.length-1; j+=2) {
						Telefone telefone = new Telefone();
						telefone.setDdd(telefones[j]);
						telefone.setNumeroTelefone(telefones[j+1]);
						listaTelefone.add(telefone);
					}
				}
				String[] enderecos = nomeTelefoneEndereco[2].split("\\|");
				if(enderecos.length>=1) {
					for(int j = 1; j < enderecos.length-1; j+=5) {
						Endereco endereco = new Endereco();
						endereco.setLogradouro(enderecos[j]);
						endereco.setNumeroEndereco(enderecos[j+1]);
						endereco.setCidade(enderecos[j+2]);
						endereco.setEstado(enderecos[j+3]);
						endereco.setCep(enderecos[j+4]);
						listaEndereco.add(endereco);
					}
				}
				contato.setEnderecos(listaEndereco);
				contato.setTelefones(listaTelefone);
				listaContato.add(contato);
			}
			
		return listaContato;
	}
	
	public String preparaContato(List<Contato> contato) {
		String contatoString = "-";
		for(int i = 0; i < contato.size(); i++) {
			contatoString = contatoString.concat(contato.get(i).getNome());
			contatoString = contatoString.concat("-");
			contatoString = contatoString.concat(contato.get(i).getSobrenome());
			contatoString = contatoString.concat("*");
			contatoString = contatoString.concat("#");
			for(int j = 0; j < contato.get(i).getTelefones().size(); j++) {
				contatoString = contatoString.concat(contato.get(i).getTelefones().get(j).getDdd());
				contatoString = contatoString.concat("#");
				contatoString = contatoString.concat(contato.get(i).getTelefones().get(j).getNumeroTelefone());
				contatoString = contatoString.concat("#");
			}
			
			contatoString = contatoString.concat("*");
			contatoString = contatoString.concat("|");
			for(int j = 0 ; j < contato.get(i).getEnderecos().size(); j++) {
				
				contatoString = contatoString.concat(contato.get(i).getEnderecos().get(j).getLogradouro());
				contatoString = contatoString.concat("|");
				contatoString = contatoString.concat(contato.get(i).getEnderecos().get(j).getNumeroEndereco());
				contatoString = contatoString.concat("|");
				contatoString = contatoString.concat(contato.get(i).getEnderecos().get(j).getCidade());
				contatoString = contatoString.concat("|");
				contatoString = contatoString.concat(contato.get(i).getEnderecos().get(j).getEstado());
				contatoString = contatoString.concat("|");
				contatoString = contatoString.concat(contato.get(i).getEnderecos().get(j).getCep());
				contatoString = contatoString.concat("|");
			}
			contatoString = contatoString.concat("\n");
		}
		return contatoString;
	}
	
	public void preparaArquivo() {
		if(!Files.exists(path)) {
			try {
				Files.createFile(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
