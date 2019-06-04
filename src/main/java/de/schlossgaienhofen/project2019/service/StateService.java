package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.data.SelectOption;
import de.schlossgaienhofen.project2019.entity.Event;
import de.schlossgaienhofen.project2019.entity.State;

import java.util.List;

public interface StateService {

  /**
   * Returns the states
   * @return
   */
  List<State> getAllStates();

  /**
   * Returns the select option factory
   * @param event
   * @return
   */
  List<SelectOption> getSelectOptionFactory(Event event);

}
