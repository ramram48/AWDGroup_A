/**
 * This file was generated by the JPA Modeler
 */
package Entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author a
 */
@Entity
public class Milestone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String Description;

    @Basic
    private Timestamp DueDate;

    @Basic
    private Timestamp ActiveDate;

    @Basic
    private String Status;

    @Basic
    private Timestamp SubmittedDate;

    @OneToMany(targetEntity = Feedback.class)
    private List<Feedback> feedbacks;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Timestamp getDueDate() {
        return this.DueDate;
    }

    public void setDueDate(Timestamp DueDate) {
        this.DueDate = DueDate;
    }

    public Timestamp getActiveDate() {
        return this.ActiveDate;
    }

    public void setActiveDate(Timestamp ActiveDate) {
        this.ActiveDate = ActiveDate;
    }

    public String getStatus() {
        return this.Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Timestamp getSubmittedDate() {
        return this.SubmittedDate;
    }

    public void setSubmittedDate(Timestamp SubmittedDate) {
        this.SubmittedDate = SubmittedDate;
    }

    public List<Feedback> getFeedbacks() {
        return this.feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

}
