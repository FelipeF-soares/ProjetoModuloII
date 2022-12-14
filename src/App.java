import controller.ContatoDAO;
import model.Agenda;
import ui.AgendaUI;

public class App {

	public static void main(String[] args) {
		
		AgendaUI ui = new AgendaUI();

		Agenda agenda = new Agenda(ContatoDAO.pegarLista());

		System.out.println(agenda.listar());
		
		ui.menu();

	}

}
