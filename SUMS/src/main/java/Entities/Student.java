/**
 * This file was generated by the JPA Modeler
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author a
 */
@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String StudentNumber;

    @OneToOne(targetEntity = Person.class)
    private Person person;

    @OneToOne(targetEntity = Project.class)
    private Project project;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentNumber() {
        return this.StudentNumber;
    }

    public void setStudentNumber(String StudentNumber) {
        this.StudentNumber = StudentNumber;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}