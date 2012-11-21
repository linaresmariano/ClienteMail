package usuario;

public abstract class EstrategiaAcceso {

	public abstract void login();
	
	public abstract String getCuerpo(Mail mail);
	
	public abstract AdjuntoMail getAdjunto(Mail mail);
	
}

public class POP3 extends EstrategiaAcceso {

	private boolean dejarCopia;
	
	@Override
	public void login() {
		
		
	}
	
	public String getCuerpo(Mail mail) {
		
		return mail.getCuerpo();
	}
	
	public AdjuntoMail getAdjuntos(Mail mail){
		
		return mail.getAdjuntos();
	}
	
}

public class IMAP extends EstrategiaAcceso {
	
	@Override
	public void login() {
		
		
	}
	
	public String getCuerpo(Mail mail) {
		
		return mail.getCuerpo();
	}
	
	public AdjuntoMail getAdjuntos(Mail mail){
		
		return mail.getAdjuntos();
	}
	
}