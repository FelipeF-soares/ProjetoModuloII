package erros;

public class ValorVazioError extends Exception {

	@Override
	public String getMessage() {
		return "O valor não pode ser nulo ou vazio para esse parâmentro!";
	}
}
