package poc.amitk.springboot.cxf.fiql.resource.cxf;

import io.micrometer.core.instrument.search.Search;
import org.apache.cxf.jaxrs.ext.search.*;
import org.apache.cxf.jaxrs.ext.search.fiql.FiqlParser;
import org.apache.cxf.jaxrs.ext.search.jpa.JPATypedQueryVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import poc.amitk.springboot.cxf.fiql.repo.EmployeeEntity;
import poc.amitk.springboot.cxf.fiql.repo.EmployeeRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
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
    private final EntityManager entityManager;

    @Autowired
    public EmployeeResource(EmployeeRepository employeeRepository, EntityManager entityManager) {
        this.employeeRepository = employeeRepository;
        this.entityManager = entityManager;
    }

/*
    @GET
    public List<EmployeeEntity> findAll() {
        logger.info("querying all employees");
        List<EmployeeEntity> allEmployees = employeeRepository.findAll();
        logger.info("found {} employee records", allEmployees.size());
        return allEmployees;
    }
*/

/*
    @Context
    SearchContext searchContext;
*/

    //    @Path("/search")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeEntity> searchEmployees(@Context SearchContext searchContext) {

        logger.info("search context: {}", searchContext.getSearchExpression());
        SearchCondition<EmployeeEntity> searchCondition = null;

        if (null == searchContext.getSearchExpression()){
            logger.info("no search condition supplied, returning all employees");
            return employeeRepository.findAll();
        }


        searchCondition = searchContext.getCondition(EmployeeEntity.class);

        logger.info("Search condition: {}", searchCondition.getStatement());

        SearchConditionVisitor<EmployeeEntity, TypedQuery<EmployeeEntity>> visitor =
                new JPATypedQueryVisitor<>(entityManager, EmployeeEntity.class);
        searchCondition.accept(visitor);

        TypedQuery<EmployeeEntity> typedQuery = visitor.getQuery();
        logger.info("Typed query: {}", typedQuery);
        List<EmployeeEntity> employees = typedQuery.getResultList();
        logger.info("found {} employees", employees.size());
        return employees;
    }
}
