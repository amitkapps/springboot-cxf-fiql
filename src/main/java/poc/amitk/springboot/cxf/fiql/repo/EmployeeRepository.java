package poc.amitk.springboot.cxf.fiql.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author amitkapps
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Serializable> {
}
