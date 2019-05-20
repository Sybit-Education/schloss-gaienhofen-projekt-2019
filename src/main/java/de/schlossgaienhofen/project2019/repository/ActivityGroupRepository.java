package de.schlossgaienhofen.project2019.repository;

import de.schlossgaienhofen.project2019.entity.ActivityGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cwr
 */
@Repository
public interface ActivityGroupRepository extends JpaRepository<ActivityGroup, Long>{
    ActivityGroup findByAgState (String agState);
}
