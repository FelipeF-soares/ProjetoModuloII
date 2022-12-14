package controller;

import java.util.List;

import dto.ContatoDto;
import model.Agenda;
import model.Contato;
import model.Endereco;
import model.Telefone;

public class ContatoController {
	
	static Agenda agenda = new Agenda();
	
	public void adicionar(Contato contato) {
		agenda.adicionar(contato);
	}
	public Contato getContato(Integer index){
		return agenda.getContato(index);
	}

	public Telefone removerTelefone(Integer indexContato, Integer indexTelefone){
		return agenda.removerTelefone(indexContato, indexTelefone);
	}
	public Endereco removerEndereco(Integer indexContato, Integer indexEndereco){
		return agenda.removerEndereco(indexContato, indexEndereco);
	}

	public void editar(Integer index, Contato contato){
		agenda.editar(index, contato);

	}
	
	public List<Contato>  listar() {
		return agenda.listar();
	}
	
	public Boolean verificaContato(ContatoDto dto) {
		String nome = dto.getNome();
		String sobrenome = dto.getSobrenome();
		return agenda.verificaContato(nome, sobrenome);
	}
	
	public List<Contato> buscar(ContatoDto dto) {
		String pesquisa = dto.getNome();

		return agenda.buscar(pesquisa);
	}
	
	public Contato retornaContato(ContatoDto dto) {
		String nome = dto.getNome();
		String sobrenome = dto.getSobrenome();
		return agenda.retornaContato(nome, sobrenome);
	}
	
	public void excluir(Contato contato) {
		agenda.excluir(contato);
	}
	
	public void removerTodos() {
		agenda.removerTodos();
	}

}
