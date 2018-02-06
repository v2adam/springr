package com.springr.first.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "employees")
@NamedQueries({
        @NamedQuery(name = "Employee.myNamedQ", query = "SELECT u FROM Employee u"),
        @NamedQuery(name = "Employee.findByNamedQ", query = "SELECT u FROM Employee u WHERE u.name = :name"),
})
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String status;

    public Employee() {
    }

    public Employee(String name, String status) {
        this.name = name;
        this.status = status;
    }

}
