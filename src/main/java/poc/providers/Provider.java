package poc.providers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public abstract class Provider {

    public abstract String sendGet(final String resource, final HashMap<String, String> params) throws IOException;

    public String convertXmlToJson(final String xml) throws IOException {
        return xml;
    }
}
