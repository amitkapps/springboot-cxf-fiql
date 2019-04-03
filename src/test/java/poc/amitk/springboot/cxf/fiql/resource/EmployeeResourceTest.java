package poc.amitk.springboot.cxf.fiql.resource;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
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

import static com.jayway.jsonpath.matchers.JsonPathMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

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
        String json = responseEntity.getBody();
        logger.info("/employees response: {}", json);
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));

        assertThat(json, hasJsonPath("$.[*]", hasSize(2)));
        assertThat(json, isJson(withJsonPath("$.[*].firstName", containsInAnyOrder("Luke", "Darth"))));
    }


    @Test
    public void test_searchEmployees_success(){

        String uri = "/services/employees/search?_s=lastName==Vader";

        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(uri, String.class);
        logger.info("uri: {}, response: {}", uri, responseEntity);
        String json = responseEntity.getBody();
        logger.info("/employees search response: {}", json);
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));


        JSONArray jsonArray = JsonPath.read(json, "$.[*]");
        assertThat(1, equalTo(jsonArray.size()));
        assertThat(json, isJson(withJsonPath("$.[*].firstName", contains("Darth"))));
    }


}
