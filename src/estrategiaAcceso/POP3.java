package estrategiaAcceso;

public class POP3 extends EstrategiaAcceso {

	private boolean dejarCopia;
	
	@Override
	public void login() {
		
		
	}
	
	@Override
	public String getCuerpo(Mail mail) {
		
		return mail.getCuerpo();
	}
	
}