import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionManager;

public class Tester {

    public static void main(String[] args) throws TechnicalConnectorException {
        String hokPassword = "password of Holder-Of-Key eHealth Certificate Key Store";
        String persPassword = "password of Personal eHealth Certificate Key Store ";

        SessionManager sessionManager = Session.getInstance();

        if (!sessionManager.hasValidSession()) {
            sessionManager.createSession(hokPassword, persPassword);
        }else{
            sessionManager.unloadSession();
            sessionManager.createSession(hokPassword, persPassword);
        }
    }
}
