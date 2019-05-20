package de.schlossgaienhofen.project2019.repository;

import de.schlossgaienhofen.project2019.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author cwr
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
  Event findByAgState(String agState);
}
