package filtros;

public class AND extends OperadorBool {

	@Override
	public boolean evaluar(boolean a, boolean b) {
		return (a && b);
	}

}
