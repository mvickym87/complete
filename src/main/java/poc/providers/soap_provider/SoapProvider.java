package poc.providers.soap_provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import poc.providers.Provider;

import java.io.IOException;
import java.util.HashMap;

@Component("soapProvider")
public class SoapProvider extends Provider {

    @Autowired
    private QuoteClient quoteClient;

    public QuoteClient getQuoteClient() {
        return quoteClient;
    }

    public void setQuoteClient(QuoteClient quoteClient) {
        this.quoteClient = quoteClient;
    }

    @Override
    public String sendGet(final String resource, final HashMap<String, String> params) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(quoteClient.sendGet(resource, params));
    }

    @Override
    public String convertXmlToJson(final String xml) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode node = xmlMapper.readTree(xml.getBytes());
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.writeValueAsString(node);
    }
}
