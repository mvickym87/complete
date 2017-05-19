package poc.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PocControllerTest {

    @Autowired
    private PocController pocController;

    @Test
    public void getSoapEndpointTest(){
        ResponseEntity responseEntity = pocController.getSoapQuote();
        responseEntity.getBody();
    }
}
