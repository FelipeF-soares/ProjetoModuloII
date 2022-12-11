package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.ContatoController;
import dto.ContatoDto;
import model.Contato;
import model.Endereco;
import model.Telefone;

public class AgendaUI {
	static Scanner scanner = new Scanner(System.in);
	static ContatoController contatoController =  new ContatoController();
	static Boolean retornoMenu = true; 
	
	public void menu() {
		System.out.println(".:: Boas vindas à sua agenda! ::.");
		do {
			System.out.println(".:: MENU PRINCIPAL::.");
            System.out.println("Digite uma opção: ");
            System.out.println("1- Adicionar contato");
            System.out.println("2- Listar todos os contatos");
            System.out.println("3- Buscar contato");
            System.out.println("4- Remover contato por nome");
            System.out.println("5- Remover todos os contatos");
            System.out.println("6- Editar contatos");
            System.out.println("0- Sair do sistema");

            switch (scanner.nextLine()) {
                case "1" -> this.adicionar();
                case "2" -> this.listar();
                case "3" -> this.buscarPorNome();
                case "4" -> this.removerPorNome();
                case "5" -> this.removerTodosContatos();
                case "6" -> this.menuAtualizar();
                case "0" ->{
                	System.out.println("Obrigado por utilizar nosso sistema :)");
                	retornoMenu = false;
                }
                default -> System.out.println("Ops, opção inválida!");
            }
			
		}while(retornoMenu);
	}
	
	private void menuAtualizar() {
		Boolean retornoMenuAtualizar = true;
		do {
			System.out.println(".:: MENU PARA EDITAR CONTATO::.");
            System.out.println("Digite uma opção: ");
            System.out.println("1- Adicionar telefone para contato");
            System.out.println("2- Adicionar endereço para contato");
            System.out.println("3- Remover telefone para contato");
            System.out.println("4- Remover endereço para contato");
            System.out.println("0- Retornar ao menu principal");

            switch (scanner.nextLine()) {
				case "1" -> this.adicionarTelefone();
				case "2" -> this.adicionarEndereco();
				case "3" -> this.removerTelefone();
				case "4" -> this.removerEndereco();
				case "0" -> retornoMenuAtualizar = false;
				default -> System.out.println("Ops, opção inválida!");
			}
		}while(retornoMenuAtualizar);
		
	}

	public void adicionar() {
		ContatoDto dto = new ContatoDto();
		Boolean retornoAdiciona = true;
		do {
			Boolean verificaContato = this.menuDadosPessoais(dto);
			if(verificaContato) {
				List<Endereco> menuEndereco = this.menuEndereco(dto, new ArrayList<Endereco>());
				List<Telefone> menuTelefone = this.menuTelefone(dto, new ArrayList<Telefone>());
				Contato adicionarContato = dto.adicionarContato(menuEndereco, menuTelefone);
				new ContatoController().adicionar(adicionarContato);
				
				retornoAdiciona=false;
			}else {
				System.out.println("Contato com nome "+dto.getNome()+" " +dto.getSobrenome()+" já está cadastrado");
				System.out.println("Deseja cadastrar outro contato?");
				System.out.println("1-Sim");
				System.out.println("Qualquer outro valor para voltar ao Menu Principal");
				String nextLine = scanner.nextLine();
				if(!nextLine.equals("1")) {
					retornoAdiciona=false;
				}
			}
		}while(retornoAdiciona);
	}
	
	public void listar() {
		ContatoController controller = new ContatoController();
		List<Contato> listar = controller.listar();
		if(listar.isEmpty()) {
			System.out.println("Você não possui contatos em sua lista");
		}else {
			for(int i = 0; i < listar.size(); i++) {
				System.out.println("\n"+(i+1)+"º Contato\n"+"Nome: "+listar.get(i).getNomeCompleto());
				for(int j = 0; j < listar.get(i).getTelefones().size(); j++) {
					System.out.println((j+1)+"º Telefone: "+listar.get(i).getTelefoneCompleto(j));
				}
				for(int j = 0; j < listar.get(i).getEnderecos().size();j++) {
					System.out.println((j+1)+"º Endereço: "+listar.get(i).getEnderecos().get(j).getLogradouro());
				}
			}
		}
		System.out.println("\n");
	}

	public void buscarPorNome() {
		ContatoDto dto = new ContatoDto();

		System.out.println("Digite o termo pelo qual deseja buscar: ");
		String pesquisa = scanner.nextLine();
		dto.setNome(pesquisa);

		List<Contato> busca = contatoController.buscar(dto);
		if(busca.isEmpty()) {
			System.out.println("Ops, não há contatos correspondentes ao termo buscado.");
		} else {
			busca.forEach(contato -> System.out.println(contato.toString()));
		}
	}
	
	public void removerPorNome() {
		ContatoDto dto = new ContatoDto();
		System.out.println("Digite o nome");
		dto.setNome(scanner.nextLine());
		System.out.println("Digite o sobrenome");
		dto.setSobrenome(scanner.nextLine());
		if(!contatoController.verificaContato(dto)) {
			Contato retornaContato = contatoController.retornaContato(dto);
			System.out.println("O contato "+retornaContato .getNome()+ " "+retornaContato .getSobrenome()+" foi localizado!");
			System.out.println("Deseja realmente excluir esse contato?");
			System.out.println("1-Sim");
			System.out.println("Qualquer outro valor para voltar ao Menu Principal");
			String nextLine = scanner.nextLine();
			if(nextLine.equals("1")) {
				contatoController.excluir(retornaContato);
				System.out.println("Usuário removido com sucesso!");
			}
		}else {
			System.out.println("Usuário não localizado no sistema");
		}
		
	}
	
	public void removerTodosContatos() {
		System.out.println("Você deseja realmente excluir todos os contatos da sua lista?");
		System.out.println("1-Sim");
		System.out.println("Qualquer outro valor para voltar ao Menu Principal");
		String nextLine = scanner.nextLine();
		if(nextLine.equals("1")) {
			contatoController.removerTodos();
			System.out.println("Todos os usuários foram removidos com sucesso!");
		}
	}

	public void adicionarTelefone(){
		System.out.println("....::ADIÇÃO DE TELEFONE(S):....");
		System.out.println("Insira o índice do contato que deseja adicionar telefone(s):");
		Integer indexContato = scanner.nextInt();

		ContatoDto dto = new ContatoDto();

		scanner.nextLine();
		Contato contato = contatoController.getContato(indexContato);

		contato.setTelefones(this.menuTelefone(dto, contato.getTelefones()));

		contatoController.editar(indexContato, contato);

	}

	public void removerTelefone(){
		System.out.println("Nos informe o id do contato que você deseja remover um número de telefone:");
		int indexContato = scanner.nextInt();
		scanner.nextLine();

		Contato contato = contatoController.getContato(indexContato);

		// Printar lista de telefones desse contato para pessoa escolher

		System.out.println("Nos informe o id do nº de telefone que você deseja excluir:");
		int indexTelefone = scanner.nextInt();
		scanner.nextLine();

		String telefoneExcluido = contatoController.getContato(indexContato).getTelefoneCompleto(indexTelefone);

		contatoController.removerTelefone(indexContato, indexTelefone);

		System.out.printf("Telefone '%s' excluído com sucesso!\n", telefoneExcluido);
	}
	public void adicionarEndereco(){
		System.out.println("....::ADIÇÃO DE ENDEREÇO(S):....");
		System.out.println("Insira o índice do contato que deseja adicionar endereço(s):");
		Integer indexContato = scanner.nextInt();

		ContatoDto dto = new ContatoDto();

		scanner.nextLine();
		Contato contato = contatoController.getContato(indexContato);

		contato.setEnderecos(this.menuEndereco(dto, contato.getEnderecos()));

		contatoController.editar(indexContato, contato);

	}

	public void removerEndereco(){
		System.out.println("Nos informe o id do contato que você deseja remover o endereço:");
		int indexContato = scanner.nextInt();
		scanner.nextLine();

		Contato contato = contatoController.getContato(indexContato);

		// Printar lista de endereços desse contato para pessoa escolher qual excluir

		System.out.println("Nos informe o id do endereço que você deseja excluir:");
		int indexEndereco = scanner.nextInt();
		scanner.nextLine();

		String enderecoExcluido =
				contatoController.getContato(indexContato).getEnderecos().get(indexEndereco).toString();

		contatoController.removerEndereco(indexContato, indexEndereco);

		System.out.printf("Endereço '%s' excluído com sucesso!\n", enderecoExcluido);
	}
	
	public Boolean menuDadosPessoais(ContatoDto dto) {
		System.out.println("....::DADOS PESSOAIS:....");
		System.out.println("Digite o nome: ");
		dto.setNome(scanner.nextLine());
		System.out.println("Digite o sobrenome: ");
		dto.setSobrenome(scanner.nextLine());
		return contatoController.verificaContato(dto);
	}
	
	public List<Endereco>  menuEndereco(ContatoDto dto, List<Endereco> listaEndereco) {
		boolean done;
		String nextLine;
		do {
			System.out.println("Deseja adicionar um endereço?");
			System.out.println("1-Sim");
			System.out.println("Qualquer outro valor para voltar ao Menu Principal");
			nextLine = scanner.nextLine();

			done = false;
			try{
				if(nextLine.equals("1")) {
					System.out.println("....::ENDEREÇOS:....");
					System.out.println("Digite o logradouro: ");
					dto.setLogradouro(scanner.nextLine());
					System.out.println("Digite o número: ");
					dto.setNumeroEndereco(scanner.nextLine());
					System.out.println("Digite o CEP: ");
					dto.setCep(scanner.nextLine());
					System.out.println("Digite a cidade: ");
					dto.setCidade(scanner.nextLine());
					System.out.println("Digite o estado: ");
					dto.setEstado(scanner.nextLine());
					listaEndereco.add(dto.adicionaEndereco());
				}
				done=true;
			} catch (Exception ex){
				System.out.printf("[ERRO] %s\n", ex.getMessage());
			}
		}while(nextLine.equals("1") || !done);
		
		return listaEndereco;
	}
	
	public List<Telefone> menuTelefone(ContatoDto dto, List<Telefone> listaTelefones){
		boolean done;

		String nextLine;
		do {
			System.out.println("Deseja adicionar um telefone?");
			System.out.println("1-Sim");
			System.out.println("Qualquer outro valor para voltar ao Menu Principal");
			nextLine = scanner.nextLine();

			done = false;
			try{
				if(nextLine.equals("1")) {
					System.out.println("....::TELEFONES:....");
					System.out.println("Digite DDD: ");
					dto.setDdd(scanner.nextLine());
					System.out.println("Digite o telefone: ");
					dto.setNumeroTelefone(scanner.nextLine());
					listaTelefones.add(dto.adicionaTelefone());
				}
				done = true;
			} catch (Exception  ex){
				System.out.printf("[ERRO] %s\n", ex.getMessage());
			}
			
		}while(nextLine.equals("1") || !done);
		return listaTelefones;
	}

}
