package net.rakip.ems.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Employees")
public class Employee {
    @Id
    @SequenceGenerator(name = "employee_id_generator", sequenceName = "emp_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_generator")
    private Long id;

    @Column(name = "first_name", columnDefinition = "VARCHAR2(25)")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "VARCHAR2(25)")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
}
