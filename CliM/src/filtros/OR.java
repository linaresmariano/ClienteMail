package filtros;

public class OR extends OperadorBool {

	@Override
	public boolean evaluar(boolean a, boolean b) {
		return (a || b);
	}

}
