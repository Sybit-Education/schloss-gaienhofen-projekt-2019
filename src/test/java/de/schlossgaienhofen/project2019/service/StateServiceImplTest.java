package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.entity.State;
import de.schlossgaienhofen.project2019.repository.StateRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StateServiceImplTest {

  @Autowired
  StateService stateService;

  @Test
  public void testGetAllStates(){

    List<State> allStates = stateService.getAllStates();

    Assert.assertNotNull(allStates);
    Assert.assertEquals("online", allStates.get(0).getStateName());
    Assert.assertEquals("offline", allStates.get(1).getStateName());

  }

}
