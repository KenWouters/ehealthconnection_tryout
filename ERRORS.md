# Errors we got and how we solved them

## General Error: Could not load decryption keys
Because we configured our `KEYSTORE_DIR` property wrong. It should contain both leading AND trailing slashes!!

`KEYSTORE_DIR=/keystore/` and not `KEYSTORE_DIR=keystore`.

## No Valid config.
```
SEVERE: Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is be.ehealth.technicalconnector.exception.TechnicalConnectorException: General Error: Could not load decryption keys] with root cause
be.ehealth.technicalconnector.exception.ConfigurationException: No Valid config. Reason[Configuration could not be validated : Could not find properties. [sessionmanager.samlattribute][sessionmanager.samlattributedesignator]]
```

Make 100% sure that the property file is correctly referenced in the `be.Tester` class!

The exception messages are very non-descriptive.

## no protocol: /keystore/blabla.p12
```
Jun 13, 2018 2:21:04 PM org.apache.catalina.core.StandardWrapperValve invoke
SEVERE: Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is be.ehealth.technicalconnector.exception.TechnicalConnectorException: General Error: Could not load decryption keys] with root cause
java.net.MalformedURLException: no protocol: /keystore/emmastore.p12
	at java.base/java.net.URL.<init>(URL.java:627)
	at java.base/java.net.URL.<init>(URL.java:523)
	at java.base/java.net.URL.<init>(URL.java:470)
	at be.ehealth.technicalconnector.utils.ConnectorIOUtils.getResourceAsStream(ConnectorIOUtils.java:386)
	at be.ehealth.technicalconnector.utils.ConnectorIOUtils.getResourceAsStream(ConnectorIOUtils.java:344)
	at be.ehealth.technicalconnector.utils.KeyStoreManager.getKeyStore(KeyStoreManager.java:85)
	at be.ehealth.technicalconnector.utils.KeyStoreManager.<init>(KeyStoreManager.java:53)
	at be.ehealth.technicalconnector.service.sts.security.impl.KeyStoreCredential.<init>(KeyStoreCredential.java:54)
	at be.ehealth.technicalconnector.session.impl.SessionManagerImpl.loadIdentificationKeys(SessionManagerImpl.java:283)
	at be.ehealth.technicalconnector.session.impl.SessionManagerImpl.createFallbackSession(SessionManagerImpl.java:201)
	at be.Tester.tryConnect(Tester.java:19)
	at be.EhealthController.tryConnect(EhealthController.java:23)
```

On *NIX systems (MacOS, Ubuntu, ...) it's important that there is **NO** leading slash in the `KEYSTORE_DIR` property!
