package poc.amitk.springboot.cxf.fiql.resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author amitkapps
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class EmployeeResourceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void test_getAllEmployees_success(){

        String uri = "/services/employees";
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(uri, String.class);
        logger.info("uri: {}, response: {}", uri, responseEntity);
        logger.debug("/authorization response: {}", responseEntity.getBody());
        assert responseEntity.getStatusCode().equals(HttpStatus.OK);

    }
}
