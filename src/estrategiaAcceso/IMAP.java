package estrategiaAcceso;

public class IMAP extends EstrategiaAcceso {
	
	@Override
	public void login() {
		
		
	}
	
	@Override
	public String getCuerpo(Mail mail) {
		
		return mail.getCuerpo();
	}
	
}