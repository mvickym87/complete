package poc.providers;

import java.util.HashMap;

public class Resources {

    private String path;
    private HashMap<String, String> queryparams;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HashMap<String, String> getQueryparams() {
        return queryparams;
    }

    public void setQueryparams(HashMap<String, String> queryparams) {
        this.queryparams = queryparams;
    }
}
