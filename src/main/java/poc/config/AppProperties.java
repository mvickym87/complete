package poc.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

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
