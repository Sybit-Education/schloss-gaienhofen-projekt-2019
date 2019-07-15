package de.schlossgaienhofen.project2019.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

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
  private EventUser attendee;

  @ManyToOne
  @JoinColumn(name = "ag_id")
  private Event event;

  @CreatedDate
  private LocalDate assignmentDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EventUser getAttendee() {
    return attendee;
  }

  public void setAttendee(EventUser attendee) {
    this.attendee = attendee;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }


  public LocalDate getAssignmentDate() {
    return assignmentDate;
  }

  public void setAssignmentDate(LocalDate assignmentDate) {
    this.assignmentDate = assignmentDate;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 53 * hash + Objects.hashCode(this.id);
    hash = 53 * hash + Objects.hashCode(this.attendee);
    hash = 53 * hash + Objects.hashCode(this.event);
    hash = 53 * hash + Objects.hashCode(this.assignmentDate);
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
    if (!Objects.equals(this.event, other.event)) {
      return false;
    }
    if (!Objects.equals(this.assignmentDate, other.assignmentDate)) {
      return false;
    }
    return true;
  }


  @Override
  public String toString() {
    return "de.schlossgaienhofen.project2019.entity.Attendee[ id=" + id + " ]";
  }

}
