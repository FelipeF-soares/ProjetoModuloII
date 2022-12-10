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
	
	public List<Contato>  listar() {
		List<Contato> listar = agenda.listar();
		return listar;
	}
	
	public Boolean verificaContato(ContatoDto dto) {
		String nome = dto.getNome();
		String sobrenome = dto.getSobrenome();
		return agenda.verificaContato(nome, sobrenome);
	}
	
	public String buscar(ContatoDto dto) {
		String nome = dto.getNome();
		String sobrenome = dto.getSobrenome();
		return agenda.buscar(nome, sobrenome);
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
