package de.schlossgaienhofen.project2019.repository;

import de.schlossgaienhofen.project2019.entity.EventUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<EventUser, Long> {
  EventUser findByEmail(String email);
}
