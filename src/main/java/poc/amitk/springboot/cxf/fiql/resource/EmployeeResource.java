package poc.amitk.springboot.cxf.fiql.resource;

import org.apache.cxf.jaxrs.ext.search.SearchCondition;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import poc.amitk.springboot.cxf.fiql.repo.EmployeeEntity;
import poc.amitk.springboot.cxf.fiql.repo.EmployeeRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author amitkapps
 */
@Component
@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeResource(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GET
    public List<EmployeeEntity> findAll() {
        logger.info("querying all employees");
        List<EmployeeEntity> allEmployees = employeeRepository.findAll();
        logger.info("found {} employee records", allEmployees.size());
        return allEmployees;
    }

    @Path("/search")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeEntity> searchEmployees(@Context SearchContext searchContext) {
        logger.info("search context: {}", searchContext);
        SearchCondition<EmployeeEntity> condition = searchContext.getCondition(EmployeeEntity.class);
        List<EmployeeEntity> allEmployees = employeeRepository.findAll();
        logger.info("All Employees: {}", allEmployees);
        return condition.findAll(allEmployees);
    }}