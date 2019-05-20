package de.schlossgaienhofen.project2019.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ActivityGroup")
public class ActivityGroup implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  private String title;

  private String agLeader;

  private String summary;
  private String description;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate startDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate endDate;

  private short maxParticipants;

  @OneToMany(mappedBy = "id")
  private Set<Attendee> attendees;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAgLeader() {
    return agLeader;
  }

  public void setAgLeader(String agLeader) {
    this.agLeader = agLeader;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public short getMaxParticipants() {
    return maxParticipants;
  }

  public void setMaxParticipants(short maxParticipants) {
    this.maxParticipants = maxParticipants;
  }

  public Set<Attendee> getAttendees() {
    return attendees;
  }

  public void setAttendees(Set<Attendee> attendees) {
    this.attendees = attendees;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + Objects.hashCode(this.id);
    hash = 97 * hash + Objects.hashCode(this.title);
    hash = 97 * hash + Objects.hashCode(this.agLeader);
    hash = 97 * hash + Objects.hashCode(this.summary);
    hash = 97 * hash + Objects.hashCode(this.description);
    hash = 97 * hash + Objects.hashCode(this.startDate);
    hash = 97 * hash + Objects.hashCode(this.endDate);
    hash = 97 * hash + this.maxParticipants;
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
    final ActivityGroup other = (ActivityGroup) obj;
    if (this.maxParticipants != other.maxParticipants) {
      return false;
    }
    if (!Objects.equals(this.title, other.title)) {
      return false;
    }
    if (!Objects.equals(this.agLeader, other.agLeader)) {
      return false;
    }
    if (!Objects.equals(this.summary, other.summary)) {
      return false;
    }
    if (!Objects.equals(this.description, other.description)) {
      return false;
    }
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    if (!Objects.equals(this.startDate, other.startDate)) {
      return false;
    }
    if (!Objects.equals(this.endDate, other.endDate)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "ActivityGroup{" + "id=" + id + ", title=" + title + '}';
  }

}
