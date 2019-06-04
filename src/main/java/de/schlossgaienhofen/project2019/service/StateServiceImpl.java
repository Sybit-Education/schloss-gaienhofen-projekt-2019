package de.schlossgaienhofen.project2019.service;

import de.schlossgaienhofen.project2019.data.SelectOption;
import de.schlossgaienhofen.project2019.data.SelectOptionFactory;
import de.schlossgaienhofen.project2019.entity.Event;
import de.schlossgaienhofen.project2019.entity.State;
import de.schlossgaienhofen.project2019.repository.StateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateServiceImpl implements StateService {
  private static final Logger LOGGER = LoggerFactory.getLogger(StateServiceImpl.class);

  @Autowired
  StateRepository stateRepository;

  @Override
  public List<State> getAllStates() {
    return stateRepository.findAll();
  }

  @Override
  public List<SelectOption> getSelectOptionFactory(Event event) {
    LOGGER.debug("--> getSelectOptionFactory");
    SelectOptionFactory<State> factory = new SelectOptionFactory<>(getAllStates());
    LOGGER.debug("<-- getSelectOptionFactory");
    return factory.getSelectOptions(
      event.getEventState(),
      e -> SelectOption.create("" + e.getStateName(), e.getStateName()));
  }

}
