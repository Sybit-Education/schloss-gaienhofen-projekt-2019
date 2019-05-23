package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.data.SelectOption;
import de.schlossgaienhofen.project2019.entity.ActivityGroup;
import de.schlossgaienhofen.project2019.entity.State;

import java.util.List;

public interface StateService {

  /**
   *
   * @return
   */
  List<State> getAllStates();

  /**
   *
   * @param activityGroup
   * @return
   */
  List<SelectOption> getSelectOptionFactory(ActivityGroup activityGroup);

}
