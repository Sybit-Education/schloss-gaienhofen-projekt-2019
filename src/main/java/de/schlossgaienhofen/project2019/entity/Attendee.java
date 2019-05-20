package de.schlossgaienhofen.project2019.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author ssr
 */
@Entity
@Table(name = "Attendee")
public class Attendee implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "attendee_id")
  private User attendee;

  @ManyToOne
  @JoinColumn(name = "ag_id")
  private ActivityGroup activityGroup;

  @CreatedDate
  private LocalDate assignemtDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getAttendee() {
    return attendee;
  }

  public void setAttendee(User attendee) {
    this.attendee = attendee;
  }

  public ActivityGroup getActivityGroup() {
    return activityGroup;
  }

  public void setActivityGroup(ActivityGroup activityGroup) {
    this.activityGroup = activityGroup;
  }


  public LocalDate getAssignemtDate() {
    return assignemtDate;
  }

  public void setAssignemtDate(LocalDate assignemtDate) {
    this.assignemtDate = assignemtDate;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 53 * hash + Objects.hashCode(this.id);
    hash = 53 * hash + Objects.hashCode(this.attendee);
    hash = 53 * hash + Objects.hashCode(this.activityGroup);
    hash = 53 * hash + Objects.hashCode(this.assignemtDate);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Attendee other = (Attendee) obj;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    if (!Objects.equals(this.attendee, other.attendee)) {
      return false;
    }
    if (!Objects.equals(this.activityGroup, other.activityGroup)) {
      return false;
    }
    if (!Objects.equals(this.assignemtDate, other.assignemtDate)) {
      return false;
    }
    return true;
  }


  @Override
  public String toString() {
    return "de.schlossgaienhofen.project2019.entity.Attendee[ id=" + id + " ]";
  }

}
