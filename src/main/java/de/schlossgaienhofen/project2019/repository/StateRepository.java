package de.schlossgaienhofen.project2019.repository;

import de.schlossgaienhofen.project2019.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

}
