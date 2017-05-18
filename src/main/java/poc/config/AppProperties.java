package poc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String soapUri;

    public String getSoapUri() {
        return soapUri;
    }

    public void setSoapUri(String soapUri) {
        this.soapUri = soapUri;
    }
}
