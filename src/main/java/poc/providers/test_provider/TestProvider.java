package poc.providers.test_provider;


import org.springframework.web.client.RestTemplate;
import poc.providers.test_provider.models.Quote;

public class TestProvider {

    public String getConsume(RestTemplate restTemplate) throws Exception {

        Quote quote = restTemplate.getForObject("http://localhost:9999/hello", Quote.class);
        System.out.println(quote.toString());
        return quote.toString();
    }
}
