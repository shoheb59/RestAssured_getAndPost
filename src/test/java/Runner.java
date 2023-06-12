import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.Test;

import java.io.IOException;

public class Runner {

    @Test
    public void getUserData() throws IOException {
        GetApi apiGet = new GetApi();
        apiGet.getApi();
    }
    @Test
    public void postRequ() throws IOException, ConfigurationException {
        GetApi apiPost = new GetApi();
        apiPost.postApi();
    }
}
