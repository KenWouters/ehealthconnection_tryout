package be;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class Tester {

    public void tryConnect() throws TechnicalConnectorException {
        final String randomPassword = "faros is supertof bedrijf";
        ConfigFactory.setConfigLocation("classpath:ehealth_ken.properties");
        SessionManager sessionManager = Session.getInstance();

        if (!sessionManager.hasValidSession()) {
            sessionManager.createSession(randomPassword, randomPassword);
        } else {
            sessionManager.unloadSession();
            sessionManager.createSession(randomPassword, randomPassword);
        }
    }
}