/**
 * @author phucle-compass
 */
package String;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {

  private Long id;
  private String name;
  private Integer age;
  private Double salary;
  private LocalDate joiningDate;

  public Employee(Long id, String name, Integer age, Double salary, LocalDate joiningDate) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.salary = salary;
    this.joiningDate = joiningDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Double getSalary() {
    return salary;
  }

  public void setSalary(Double salary) {
    this.salary = salary;
  }

  public LocalDate getJoiningDate() {
    return joiningDate;
  }

  public void setJoiningDate(LocalDate joiningDate) {
    this.joiningDate = joiningDate;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {return false;}
    Employee employee = (Employee)o;
    return Objects.equals(id, employee.id) &&
        Objects.equals(name, employee.name) &&
        Objects.equals(age, employee.age) &&
        Objects.equals(salary, employee.salary) &&
        Objects.equals(joiningDate, employee.joiningDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, age, salary, joiningDate);
  }

  @Override
  public String toString() {
    return "Employee{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", age=" + age +
        ", salary=" + salary +
        ", joiningDate=" + joiningDate +
        '}' + '\n';
  }
}
