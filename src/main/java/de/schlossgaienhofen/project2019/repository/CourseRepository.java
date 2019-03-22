package de.schlossgaienhofen.project2019.repository;

import de.schlossgaienhofen.project2019.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
