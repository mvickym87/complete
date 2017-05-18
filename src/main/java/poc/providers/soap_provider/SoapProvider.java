package poc.providers.soap_provider;

import poc.enums.ProviderType;
import poc.providers.Provider;
import org.springframework.beans.factory.annotation.Value;

public class SoapProvider extends Provider {

    @Value("http://www.webservicex.com/stockquote.asmx")
    private String path;

    private ProviderType providerType;

    public SoapProvider() {
        this.providerType = ProviderType.SOAP;
    }
}
