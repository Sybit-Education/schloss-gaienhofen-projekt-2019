/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
}
