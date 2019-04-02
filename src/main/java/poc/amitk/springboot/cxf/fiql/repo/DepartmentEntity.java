package poc.amitk.springboot.cxf.fiql.repo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author amitkapps
 */
@Entity
@Table(name = "DEPARTMENT")
public class DepartmentEntity {

    @Id
    @Column(name = "DEPT_CD")
    private String departmentCode;

    @Column(name = "DEPT_NAME")
    private String departmentName;

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "DepartmentEntity{" +
                "departmentCode=" + departmentCode +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEntity that = (DepartmentEntity) o;
        return getDepartmentCode().equals(that.getDepartmentCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDepartmentCode());
    }
}
