package poc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import poc.providers.Provider;
import poc.providers.test_provider.models.Quote;
import poc.wsdl.GetQuoteResponse;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/poc")
public class PocController {

    @Autowired
    @Qualifier("restProvider")
    private Provider restProvider;

    @Autowired
    @Qualifier("soapProvider")
    private Provider soapProvider;

    @RequestMapping(value =  "/rest", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<Quote> getQueueMessage() {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<Quote> responseEntity;
        Quote providerResponse = null;
        try {
            providerResponse = objectMapper.readValue(restProvider.sendGet("/hello", null), Quote.class);
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
    public ResponseEntity getSoapQuote() {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity responseEntity;
        GetQuoteResponse providerResponse = null;
        HashMap<String, String> params = new HashMap<>();
        try {
            params.put("ticker", "MSFT");
            providerResponse = objectMapper.readValue(soapProvider.sendGet("http://www.webserviceX.NET/GetQuote", params),GetQuoteResponse.class);
            if(providerResponse != null && !providerResponse.getGetQuoteResult().equals("exception")){
                responseEntity = new ResponseEntity(soapProvider.convertXmlToJson(providerResponse.getGetQuoteResult()), HttpStatus.OK);
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
