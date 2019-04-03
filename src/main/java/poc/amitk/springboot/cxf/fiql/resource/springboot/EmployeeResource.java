package poc.amitk.springboot.cxf.fiql.resource.springboot;

import org.apache.cxf.jaxrs.ext.search.SearchCondition;
import org.apache.cxf.jaxrs.ext.search.SearchConditionVisitor;
import org.apache.cxf.jaxrs.ext.search.fiql.FiqlParser;
import org.apache.cxf.jaxrs.ext.search.jpa.JPATypedQueryVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import poc.amitk.springboot.cxf.fiql.repo.EmployeeEntity;
import poc.amitk.springboot.cxf.fiql.repo.EmployeeRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author amitkapps
 */
@RestController("springEmployeeResource")
@RequestMapping("/api/employees")
public class EmployeeResource {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    final EmployeeRepository employeeRepository;
    private final EntityManager entityManager;

    @Autowired
    public EmployeeResource(EmployeeRepository employeeRepository, EntityManager entityManager) {
        this.employeeRepository = employeeRepository;
        this.entityManager = entityManager;
    }

    @GetMapping
    public List<EmployeeEntity> findEmployeedById(){
        logger.info("find all employees");
        return employeeRepository.findAll();
    }

    @GetMapping(params = "search")
    public List<EmployeeEntity> searchEmployeesByFiql(@RequestParam("search") String fiqlSearchExpression){
        logger.info("find all employees by criteria: {}", fiqlSearchExpression);

        SearchCondition<EmployeeEntity> searchCondition = new FiqlParser<EmployeeEntity>(EmployeeEntity.class).parse(fiqlSearchExpression);

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
