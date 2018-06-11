package be;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class EhealthController {

    private final Tester tester;

    public EhealthController(final Tester tester) {
        this.tester = tester;
    }

    @GetMapping("/try")
    public ResponseEntity<Object> tryConnect() throws TechnicalConnectorException {
        tester.tryConnect();
        return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
    }
}
