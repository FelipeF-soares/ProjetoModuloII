package controller;

import java.util.List;

import dto.ContatoDto;
import model.Agenda;
import model.Contato;

public class ContatoController {
	
	static Agenda agenda = new Agenda();
	
	public void adicionar(Contato contato) {
		agenda.adicionar(contato);
	}
	public Contato getContato(Integer index){
		return agenda.getContato(index);
	}

	public void editar(Integer index, Contato contato){
		agenda.editar(index, contato);

	}
	
	public List<Contato>  listar() {
		List<Contato> listar = agenda.listar();
		return listar;
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
