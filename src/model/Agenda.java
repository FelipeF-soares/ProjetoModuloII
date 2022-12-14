package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.AgendaDao;

public class Agenda {
	
	private List<Contato> contatos;
	AgendaDao dao = new AgendaDao();

    public Agenda() {
        this.contatos = new ArrayList<>();
    }

    public Agenda(List<Contato> contatos) {
        this.contatos = new ArrayList<>();
    }

    public void adicionar(Contato contato) {
        this.contatos.add(contato);
        String contatoString = dao.preparaContato(contatos);
        try {
			dao.salvarContato(contatoString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void editar(Integer index, Contato contato) {
        this.contatos.set(index, contato);
    }

    public void excluir(Contato contato) {
        contatos.remove(contato);
    }

    //Listar todos os contatos da agenda
    public List<Contato> listar() {
    	List<Contato> ListaContatString =  new ArrayList<Contato>();
    	try {
    		ListaContatString = dao.listarContatos();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ListaContatString;
    }
    //Busca um por nome retorna o toString contato localizado
    public List<Contato> buscar(String pesquisa) {
    	List<Contato> listarContatos = this.listar();
        List<Contato> contatosBuscados = new ArrayList<>();

        for (Contato listarContato : listarContatos) {
            if (listarContato.getNomeCompleto().toUpperCase().trim().contains(pesquisa.toUpperCase().trim())) {
                contatosBuscados.add(listarContato);
            }
        }

        return contatosBuscados;
    }
    
    //verifica se o contato foi localizado
    public Boolean verificaContato(String nome, String sobrenome) {
    	List<Contato> listarContatos = this.listar();
        for (Contato listarContato : listarContatos) {
            if (listarContato.getNome().equals(nome.toUpperCase().trim()) && listarContato.getSobrenome().equals(sobrenome.toUpperCase().trim())) {
                return false;
            }
        }
    	return true;
    }
    // retorna o Objeto Localizado
    public Contato retornaContato(String nome, String sobrenome) {
    	List<Contato> listarContatos = this.listar();
        for (Contato listarContato : listarContatos) {
            if (listarContato.getNome().equals(nome) && listarContato.getSobrenome().equals(sobrenome)) {
                return listarContato;
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
    public Telefone removerTelefone(Integer indexContato, int indexTelefone){
        return this.contatos.get(indexContato).getTelefones().remove(indexTelefone);
    }
    public Endereco removerEndereco(Integer indexContato, int indexEndereco){
        return this.contatos.get(indexContato).getEnderecos().remove(indexEndereco);
    }
}
