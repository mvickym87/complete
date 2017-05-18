package poc.providers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class SenderClient {

    @Autowired
    private RestTemplate restTemplate;

    public String callGetThridParty(final Provider provider, final String resource, final HashMap<String, String> params){
        return restTemplate.getForObject(provider.getRequestWithQueryParams(resource, params), String.class);
    }
}
