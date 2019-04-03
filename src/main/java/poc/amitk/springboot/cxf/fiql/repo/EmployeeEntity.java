package poc.amitk.springboot.cxf.fiql.repo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author amitkapps
 */
@Entity
@Table(name = "EMPLOYEE")
public class EmployeeEntity {

    @Id
    @Column(name = "EMP_ID")
    private Long employeeId;

    @Column(name = "EMP_FIRST_NAME")
    private String firstName;

    @Column(name = "EMP_LAST_NAME")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "dept_cd")
    private DepartmentEntity department;

    @Column(name = "JOIN_DATE")
    private LocalDate joiningDate;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department=" + department +
                ", joiningDate=" + joiningDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return getEmployeeId().equals(that.getEmployeeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeId());
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }
}
