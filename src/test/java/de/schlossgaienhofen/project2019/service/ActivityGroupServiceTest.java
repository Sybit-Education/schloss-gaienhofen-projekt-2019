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

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityGroupServiceTest {

  @Autowired
  ActivityGroupService activityGroupService;

  @Autowired
  ActivityGroupRepository activitGroupRepository;

  @Test
  public void testGetAllActivityGroups() {

    List<ActivityGroup> activityGroups = activityGroupService.getAllActivityGroups();

    Assert.assertNotNull(activityGroups);
    Assert.assertTrue(activityGroups.size() > 2);
    Assert.assertNotNull(activityGroups.get(0));
  }

  @Test
  public void testCreateActivityGroup() {
    ActivityGroup ag = new ActivityGroup();
    ag.setTitle("Titel");
    ag.setAgLeader("Leader");

    int sizeBefore = activityGroupService.getAllActivityGroups().size();

    Assert.assertNull(ag.getId());
    ag = activityGroupService.create(ag);

    Assert.assertNotNull(ag);
    Assert.assertNotNull(ag.getId());

    int sizeAfter = activityGroupService.getAllActivityGroups().size();

    Assert.assertEquals(sizeBefore + 1, sizeAfter);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateActivityGroup_withId() {
    ActivityGroup ag = new ActivityGroup();
    ag.setId(Long.valueOf(12L));
    ag.setTitle("Titel");
    ag.setAgLeader("Leader");
    activityGroupService.create(ag);
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
