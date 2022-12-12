package controller;

import dto.ContatoDto;
import model.Contato;
import model.Endereco;
import model.Telefone;
import ui.AgendaUI;

import java.util.ArrayList;
import java.util.List;

public class AgendaController {

    private AgendaUI agendaUi;
    private ContatoController contatoController;


    public AgendaController(){
        agendaUi = new AgendaUI();
        contatoController = new ContatoController();
    }

    public void menu(){

        boolean continua = true;

        while(continua){
            String opcao = agendaUi.menu();

            switch (opcao) {
                case "1" -> this.adicionar();
                case "2" -> this.listar();
                case "3" -> this.buscarPorNome();
                case "4" -> this.removerPorNome();
                case "5" -> this.removerTodosContatos();
                case "6" -> agendaUi.menuAtualizar();
                case "0" ->{
                    System.out.println("Obrigado por utilizar nosso sistema :)");
                    continua = false;
                }
                default -> System.out.println("Ops, opção inválida!");
            }

        }

    }

    public void adicionar() {
        ContatoDto dto = new ContatoDto();
        Boolean retornoAdiciona = true;
        do {
            Boolean verificaContato = agendaUi.menuDadosPessoais(dto);
            if(verificaContato) {
                List<Endereco> menuEndereco = agendaUi.menuEndereco(dto, new ArrayList<>());
                List<Telefone> menuTelefone = agendaUi.menuTelefone(dto, new ArrayList<>());
                Contato adicionarContato = dto.adicionarContato(menuEndereco, menuTelefone);
                contatoController.adicionar(adicionarContato);

                retornoAdiciona=false;
            }else {
                String retorno = agendaUi.contatoCadastrado(dto.getNome(), dto.getSobrenome());
                if(!retorno.equals("1")) {
                    retornoAdiciona=false;
                }
            }
        }while(retornoAdiciona);
    }

    public void listar() {
        ContatoController controller = new ContatoController();
        List<Contato> contatos= controller.listar();
        agendaUi.listar(contatos);

    }

    public void buscarPorNome() {
        String pesquisa = agendaUi.pegarNome();

        ContatoDto dto = new ContatoDto();
        dto.setNome(pesquisa);

        List<Contato> busca = contatoController.buscar(dto);

        agendaUi.imprimirBusca(busca);

    }

    public void removerPorNome() {
        ContatoDto dto = new ContatoDto();
        boolean verificaContato = agendaUi.menuDadosPessoais(dto);

        if(!verificaContato) {
            Contato retornaContato = contatoController.retornaContato(dto);
            String confirma = agendaUi.confirmarRemoverContato(retornaContato);

            if(confirma.equals("1")) {
                contatoController.excluir(retornaContato);
                System.out.println("Usuário removido com sucesso!");
                System.out.println();
            }else{
                System.out.println("Operação Cancelada Usuario não removido");
                System.out.println();
            }
        }else {
            System.out.println("Usuário não localizado no sistema");
            System.out.println();
        }

    }

    public void removerTodosContatos() {

        String confirmar = agendaUi.confirmarLimparListaContato();

        agendaUi.removerTudo(confirmar);

    }








}
