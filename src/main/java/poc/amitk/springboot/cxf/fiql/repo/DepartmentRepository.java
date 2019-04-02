package poc.amitk.springboot.cxf.fiql.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 *
 * @author amitkapps
 *
 */
@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Serializable> {
}
