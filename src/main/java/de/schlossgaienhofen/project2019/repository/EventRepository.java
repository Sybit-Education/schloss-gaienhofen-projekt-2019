package de.schlossgaienhofen.project2019.repository;

import de.schlossgaienhofen.project2019.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
