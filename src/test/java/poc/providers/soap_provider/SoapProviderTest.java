package poc.providers.soap_provider;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import poc.providers.Provider;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SoapProviderTest extends Assertions {
    @Autowired
    @Qualifier("soapProvider")
    private Provider soapProvider;

    @Test
    public void convertXmlToJsonTest() throws IOException {
        String xml = "<StockQuotes><Stock><Symbol>MSFT</Symbol><Last>67.80</Last>" +
                "<Date>5/18/2017</Date><Time>3:51pm</Time><Change>+0.32</Change><Open>67.37</Open>" +
                "<High>68.13</High><Low>67.14</Low><Volume>1609381</Volume><MktCap>523.45B</MktCap>" +
                "<PreviousClose>67.48</PreviousClose><PercentageChange>+0.47%</PercentageChange>" +
                "<AnnRange>48.04 - 69.71</AnnRange><Earns>2.27</Earns><P-E>29.92</P-E><Name>Microsoft Corporation</Name>" +
                "</Stock></StockQuotes>";
        String json = soapProvider.convertXmlToJson(xml);
        assertThat(json).isNotBlank();
    }
}
