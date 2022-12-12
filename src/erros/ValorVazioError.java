package erros;

public class ValorVazioError extends Exception {

	@Override
	public String getMessage() {
		return "O valor não Pode ser nulo ou vazio para esse paramentro!";
	}
}
