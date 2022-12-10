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
            System.out.println("Digite uma opçõe: ");
            System.out.println("1- Adicionar contato");
            System.out.println("2- Listar todos os contatos");
            System.out.println("3- Buscar contato por nome");
            System.out.println("4- Remover contato por nome");
            System.out.println("5- Remover Todos os Contatos");
            System.out.println("0- Sair do sistema");

            switch (scanner.nextLine()) {
                case "1" -> {
                    this.adicionar();
                }
                case "2" -> {
                    this.listar();
                }
                case "3" ->{
                	this.buscarPorNome();
                }
                case "4" ->{
                	this.removerPorNome();;
                }
                case "5" ->{
                	this.removerTodosContatos();;
                }
                case "6" ->{
                	this.menuAtualizar();;
                }
                case "0" ->{
                	System.out.println("Obrigado por utilizar nosso sistema");
                	retornoMenu = false;
                }
                default -> System.out.println("Ops, opção inválida!");
            }
			
		}while(retornoMenu);
	}
	
	private void menuAtualizar() {
		do {
			System.out.println(".:: MEN U PARA EDITAR CONTATO::.");
            System.out.println("Digite uma opçõe: ");
            System.out.println("1- Adicionar Telefone para Contato");
            System.out.println("2- Adicionar Endereço para Contato");
            System.out.println("3- Remover Telefone para Contato");
            System.out.println("4- Remover Endereço para Contato");
            System.out.println("0- Retornar ao menu principal");

            switch (scanner.nextLine()) {
                case "1" -> {
                    this.adicionar();
                }
                case "2" -> {
                    this.listar();
                }
                case "3" ->{
                	this.buscarPorNome();
                }
                case "4" ->{
                	this.removerPorNome();;
                }
                case "5" ->{
                	this.removerTodosContatos();;
                }
                case "6" ->{
                	this.menuAtualizar();;
                }
                case "0" ->{
                	System.out.println("Obrigado por utilizar nosso sistema");
                	retornoMenu = false;
                }
                default -> System.out.println("Ops, opção inválida!");
            }
			
		}while(retornoMenu);
		
	}

	public void adicionar() {
		ContatoDto dto = new ContatoDto();
		Boolean retornoAdiciona = true;
		do {
			Boolean verificaContato = this.menuDadosPessoais(dto);
			if(verificaContato) {
				List<Endereco> menuEndereco = this.menuEndereco(dto);
				List<Telefone> menuTelefone = this.menuTelefone(dto);
				Contato adicionarContato = dto.adicionarContato(menuEndereco, menuTelefone);
				new ContatoController().adicionar(adicionarContato);
				
				retornoAdiciona=false;
			}else {
				System.out.println("Contato Com Nome "+dto.getNome()+" " +dto.getSobrenome()+" já está cadastrado");
				System.out.println("Deseja Cadastrar outro Contato?");
				System.out.println("1-Sim");
				System.out.println("Qualquer outro valor para voltar ao Menu Pricipal");
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
			System.out.println("Você não Possui Contatos em sua Lista");
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
		System.out.println("Digite o nome");
		dto.setNome(scanner.nextLine());
		System.out.println("Digite o sobrenome");
		dto.setSobrenome(scanner.nextLine());
		System.out.println(contatoController.buscar(dto));
	}
	
	public void removerPorNome() {
		ContatoDto dto = new ContatoDto();
		System.out.println("Digite o nome");
		dto.setNome(scanner.nextLine());
		System.out.println("Digite o sobrenome");
		dto.setSobrenome(scanner.nextLine());
		if(!contatoController.verificaContato(dto)) {
			Contato retornaContato = contatoController.retornaContato(dto);
			System.out.println("O Contato "+retornaContato .getNome()+ " "+retornaContato .getSobrenome()+" Foi localizado!");
			System.out.println("Deseja Realmente excluir esse conatato?");
			System.out.println("1-Sim");
			System.out.println("Qualquer outro valor para voltar ao Menu Principal");
			String nextLine = scanner.nextLine();
			if(nextLine.equals("1")) {
				contatoController.excluir(retornaContato);
				System.out.println("Usuário Removido Com Sucesso!");
			}
		}else {
			System.out.println("Usuário Não Localizado No Sistema");
		}
		
	}
	
	public void removerTodosContatos() {
		System.out.println("Você deseja realmente excluir todos os contatos da sua lista?");
		System.out.println("1-Sim");
		System.out.println("Qualquer outro valor para voltar ao Menu Pricipal");
		String nextLine = scanner.nextLine();
		if(nextLine.equals("1")) {
			contatoController.removerTodos();
			System.out.println("Todos os Usuários  foram Removidos Com Sucesso!");
		}
	}
	
	public Boolean menuDadosPessoais(ContatoDto dto) {
		System.out.println("....::DADOS PESSOAIS:....");
		System.out.println("Digite o nome");
		dto.setNome(scanner.nextLine());
		System.out.println("Digite o sobrenome");
		dto.setSobrenome(scanner.nextLine());
		Boolean verificaContato = contatoController.verificaContato(dto);
		return verificaContato;
	}
	
	public List<Endereco>  menuEndereco(ContatoDto dto) {
		List<Endereco> listaEndereco = new ArrayList<Endereco>();
		String nextLine;
		do {
			System.out.println("Deseja adicionar um Endereço?");
			System.out.println("1-Sim");
			System.out.println("Qualquer outro valor para voltar ao Menu Pricipal");
			nextLine = scanner.nextLine();
			if(nextLine.equals("1")) {
				System.out.println("....::ENDEREÇOS:....");
				System.out.println("Digite o Logradouro");
				dto.setLogradouro(scanner.nextLine());
				System.out.println("Digite o Numero");
				dto.setNumeroEndereco(scanner.nextLine());
				System.out.println("Digite o CEP");
				dto.setCep(scanner.nextLine());
				System.out.println("Digite a Cidade");
				dto.setCidade(scanner.nextLine());
				System.out.println("Digite o Estado:");
				dto.setEstado(scanner.nextLine());
				listaEndereco.add(dto.adicionaEndereco());
			}
		}while(nextLine.equals("1"));
		
		return listaEndereco;
	}
	
	public List<Telefone> menuTelefone(ContatoDto dto){
		List<Telefone> listaTelefones = new ArrayList<Telefone>();
		String nextLine;
		do {
			System.out.println("Deseja adicionar um Telefone?");
			System.out.println("1-Sim");
			System.out.println("Qualquer outro valor para voltar ao Menu Pricipal");
			nextLine = scanner.nextLine();
			if(nextLine.equals("1")) {
				System.out.println("....::TELEFONES:....");
				System.out.println("Digite DDD da sua cidade:");
				dto.setDdd(scanner.nextLine());
				System.out.println("Digite o seu Telefone:");
				dto.setNumeroTelefone(scanner.nextLine());
				listaTelefones.add(dto.adicionaTelefone());
			}
			
		}while(nextLine.equals("1"));
		return listaTelefones;
	}

}
