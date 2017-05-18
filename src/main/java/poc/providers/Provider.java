package poc.providers;

import poc.enums.ProviderType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Provider {

    @Value("${app.provider.url}")
    private String path;

    private ProviderType providerType;

    public Provider() {
        this.providerType = ProviderType.REST;
    }

    public String getRequestWithQueryParams(final String resource, final HashMap<String, String> params){
        String endpoint =  path.concat(resource);
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

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public ProviderType getProviderType() {
        return providerType;
    }

    public void setProviderType(ProviderType providerType) {
        this.providerType = providerType;
    }
}
