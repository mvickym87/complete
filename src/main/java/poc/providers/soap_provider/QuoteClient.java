
package poc.providers.soap_provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import poc.wsdl.GetQuote;
import poc.wsdl.GetQuoteResponse;

import java.util.HashMap;

@Component
public class QuoteClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(QuoteClient.class);

	@Value("${app.soapUri}")
	private String soapUri;

	public GetQuoteResponse sendGet(String resource, final HashMap<String, String> params) {

		GetQuote request = new GetQuote();
		request.setSymbol(params.get("ticker"));

		log.info("Requesting quote for " + params.get("ticker"));

		GetQuoteResponse response = (GetQuoteResponse) getWebServiceTemplate()
				.marshalSendAndReceive(soapUri,
						request,
						new SoapActionCallback(resource));

		return response;
	}

}
