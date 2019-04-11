/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.ActivityGroup;
import de.schlossgaienhofen.project2019.entity.User;
import de.schlossgaienhofen.project2019.repository.ActivityGroupRepository;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author cwr
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityGroupServiceTest {
    
    @Autowired
    ActivityGroupService activityGroupService;
    
    @Autowired
    ActivityGroupRepository activitGroupRepository;
    
    @Test
    public void testGetAllActivityGroups(){
        
        List<ActivityGroup> activityGroups = activityGroupService.getAllActivityGroups();
        
        
        Assert.assertEquals(3, activityGroups.size());
    }
    
    @Test
    public void testGetAllActivityGroupsOfUser() {
      User user = new User();
      user.setId(Long.valueOf(1));
      
      List<ActivityGroup> activityGroups = activityGroupService.getActivityGroupsOfUser(user);
      
      Assert.assertNotNull(activityGroups);
      Assert.assertEquals(1, activityGroups.size());    
    }
}
