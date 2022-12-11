package model;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
	
	private List<Contato> contatos;

    public Agenda() {
        this.contatos = new ArrayList<>();
    }

    public Agenda(List<Contato> contatos) {
        this.contatos = new ArrayList<>();
    }

    public void adicionar(Contato contato) {
        this.contatos.add(contato);
    }
    public void editar(Integer index, Contato contato) {
        this.contatos.set(index, contato);
    }

    public void excluir(Contato contato) {
        contatos.remove(contato);
    }

    //Listar todos os contatos da agenda
    public List<Contato> listar() {
    	return contatos;
    }
    //Busca um por nome retorna o toString contato localizado
    public List<Contato> buscar(String pesquisa) {
    	List<Contato> listarContatos = this.listar();
        List<Contato> contatosBuscados = new ArrayList<>();

    	for(int i = 0; i < listarContatos.size(); i++) {
            if(listarContatos.get(i).getNomeCompleto().toUpperCase().trim().contains(pesquisa)) {
                contatosBuscados.add(listarContatos.get(i));
            }
    	}

        return contatosBuscados;
    }
    
    //verifica se o contato foi localizado
    public Boolean verificaContato(String nome, String sobrenome) {
    	List<Contato> listarContatos = this.listar();
    	for(int i = 0; i < listarContatos.size(); i++) {
    		if(listarContatos.get(i).getNome().equals(nome.toUpperCase().trim()) && listarContatos.get(i).getSobrenome().equals(sobrenome.toUpperCase().trim())) {
    			return false;
    		}
    	}
    	return true;
    }
    // retorna o Objeto Localizado
    public Contato retornaContato(String nome, String sobrenome) {
    	List<Contato> listarContatos = this.listar();
    	for(int i = 0; i < listarContatos.size(); i++) {
    		if(listarContatos.get(i).getNome().equals(nome) && listarContatos.get(i).getSobrenome().equals(sobrenome)) {
    			return listarContatos.get(i);
    		}
    	}
    	return null;
    }

    //remover todos os contatos da Agenda
    public void removerTodos() {
        contatos.clear();
    }

    public Contato getContato(Integer index) {
        return contatos.get(index);
    }
    public void setContato(Integer index, Contato contato) {
        contatos.set(index, contato);
    }
}
