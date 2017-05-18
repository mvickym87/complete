package poc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import poc.providers.Provider;
import poc.providers.SenderClient;
import poc.providers.soap_provider.QuoteClient;
import poc.wsdl.GetQuoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import poc.providers.test_provider.models.Quote;

import java.io.IOException;

@RestController
@RequestMapping("/poc")
public class PocController {

    @Autowired
    private SenderClient senderClient;

    @Autowired
    private Provider provider;

    @Autowired
    private QuoteClient quoteClient;

    @RequestMapping(value =  "/rest", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<Quote> getQueueMessage() {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<Quote> responseEntity;
        Quote providerResponse = null;
        try {
            providerResponse = objectMapper.readValue(senderClient.callGetThridParty(provider, "/hello", null), Quote.class);
            if(providerResponse != null){
                responseEntity = new ResponseEntity<Quote>(providerResponse, HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value =  "/soap", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<GetQuoteResponse> getSoapQuote() {
        ResponseEntity<GetQuoteResponse> responseEntity;
        GetQuoteResponse providerResponse = null;
        try {
            providerResponse = quoteClient.getQuote("MSFT");
            if(providerResponse != null){
                responseEntity = new ResponseEntity<GetQuoteResponse>(providerResponse, HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
