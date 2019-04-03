package poc.amitk.springboot.cxf.fiql.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author amitkapps
 */
@ActiveProfiles(value = "test")
@RunWith(SpringRunner.class)
@DataJpaTest // starts only the data tier, rollsback at the end of the test
@AutoConfigureTestDatabase
public class EmployeeRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void findEmployeeById(){

        EmployeeEntity employee = employeeRepository.findById(1000L).get();
        logger.info("found one employee: {}", employee);
        assertThat(employee).isNotNull();
        assertThat(employee.getDepartment()).isNotNull();

    }

}
