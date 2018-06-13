# Emma's eHealth Logbook

This logbook serves to document the adventure of trying to connect to eHealth. More specifically _MyVSBNet_.

## Log

### 12/06/2018 - Working flow getting a SAMLToken
Today we managed to actually retrieve a SAMLToken Response. We haven't yet tried calling a follow up webservice call, e.g. `determineInsurability`.

We documented everything in the [HOWTO](HOWTO.md), but here's the gist of what we did today.

We connected to a conference call and were told to use `SessionManager.createFallbackSession(pw,pw,pw)` instead of `SessionManager.createSession(pw,pw)` to create a session, this resolved our problems.

We also had to change the default properties for the keystore configurations to the location in our project.

And eventually got stuck again on a SoapFaultException saying the request was invalid or malformed. But we **were** able to send a soap request.

During the conf call, the accepting server's serverlog was investigated to find out what happened. 
It appears the endpoint url was deprecated, we were still using an old URL, which we got from the `connector-packaging-persphysiotherapist` zip file. 
The package maintainers were going to correct this.

The last step was to exclude some properties in the example property file, who were specific to the persphysiotherapist connector, and are meaningless to MyVSBNet.

## History

### Trying to use SoapUI to get a SAMLToken
We documented everything concerning using SoapUI to get a SAMLToken [over here](SPIKE.md).

### Separate Technical project replicating Emma's architecture
Started from a SpringBoot with Gradle project.

Imported physical therapists business connector into this standalone springboot gradle project.

Excluded a bunch of dependencies that were throwing exceptions:
* connector-technical-smartcardio
* be-fedict-common-eid
* ch.qos.logback (to have embedded tomcat deployed)
* com.sun.security.pkcs11
* connector-utilities-timestamping

Then we were getting NoClassDefFoundError's at runtime, and started to import proper dependencies. 

Only depended on connector-technical-core.

Imported be.fedict.commons-eid-bom:0.8.0 to replace be-fedict-common-eid and connector-technical-smartcardio dependencies.

Imported be.fgov.ehealth.etee:etee-crypto-master:2.1.2 dependency, because building a request was throwing exceptions on missing classes

connector-utilties-timestamping that was supposed to come with the connector-technical-core:3.15.1 did not have a 3.15.1 version, so we had to manually choose a lower version: 3.14.2

Added javax.activation, javax.xml.soap, javax.xml.bind, javax.xml.ws and com.sun.xml.messaging.saaj dependencies because we're running on Java 9, not 8.

Then we needed to plug in our eID cardreader and our eID, to progress to the next step. It does seem weird that the connector seems to **always** require an eID.

Some keystore files were not properly created or configured:

We got `rejects tag type 99` in an error, this we fixed by first configuring the correct Keystore properties, according to the eHealth platform Services Connectores V3 guidelines, section 3.3 _Keystore configuration_.
The errors we were getting were really unclear in which file or which configuration was erroneous.

Then we got a security exception: be.ehealth.technicalconnector.exception.TechnicalConnectorException: An error occurred while instantiating the webservice security handler: unable to insert security header 