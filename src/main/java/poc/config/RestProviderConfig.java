package poc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import poc.providers.Resources;

import java.util.HashMap;
import java.util.List;

@Component
@ConfigurationProperties
@PropertySource("classpath:resources/provider-config.yml")
public class RestProviderConfig {

    private String url;
    private List<HashMap<String, Resources>> resources;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<HashMap<String, Resources>> getResources() {
        return resources;
    }

    public void setResources(List<HashMap<String, Resources>> resources) {
        this.resources = resources;
    }
}
