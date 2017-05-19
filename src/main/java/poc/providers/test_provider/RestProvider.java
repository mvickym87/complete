package poc.providers.test_provider;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import poc.config.RestProviderConfig;
import poc.providers.Provider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component("restProvider")
public class RestProvider extends Provider{
    @Value("${app.provider.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestProviderConfig restProviderConfig;

    public String getRequestWithQueryParams(final String resource, final HashMap<String, String> params){
        String endpoint =  url.concat(resource);
        if(params != null) {
            for(Map.Entry<String, String> entry : params.entrySet()){
                if(!endpoint.contains("?")){
                    endpoint.concat("?");
                }
                endpoint.concat(entry.getKey()).concat("=").concat(entry.getValue());
            }
        }
        return endpoint;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String sendGet(final String resource, final HashMap<String, String> params) throws IOException {
        return this.restTemplate.getForObject(getRequestWithQueryParams(resource, params), String.class);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RestProviderConfig getRestProviderConfig() {
        return restProviderConfig;
    }

    public void setRestProviderConfig(RestProviderConfig restProviderConfig) {
        this.restProviderConfig = restProviderConfig;
    }
}
