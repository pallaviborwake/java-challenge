package jp.co.axa.apidemo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="EMPLOYEE")
public class Employee  implements Serializable {

    private static final long serialVersionUID = 1L;

	@Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @ApiModelProperty(required = false,hidden=false) 

    private Long id;

    @Getter
    @Setter
    @Column(name="EMPLOYEE_NAME")
    private String name;

    @Getter
    @Setter
    @Column(name="EMPLOYEE_SALARY")
    private Integer salary;

    @Getter
    @Setter
    @Column(name="DEPARTMENT")
    private String department;



}
