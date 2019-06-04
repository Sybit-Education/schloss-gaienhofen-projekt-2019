package de.schlossgaienhofen.project2019.data;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SelectOptionFactory<T> {

  /**
   * Factory to generate SelectOption Lists for web view.
   *
   * @param <T> given VO to be converted to SelectOption objects.
   */

  private final List<T> valueList;

  public SelectOptionFactory(List<T> valueList) {
    this.valueList = valueList;
  }

  /**
   * Get List of {code}SelectOption{code} objects from &lt;T&gt;.
   *
   * @param selectedValue marks the selected value.
   * @param mapper        mapper of params.
   * @return List of SelectOption objects.
   */
  public List<SelectOption> getSelectOptions(String selectedValue, Function<T, SelectOption> mapper) {

    final List<SelectOption> selectOptionList = valueList.stream().map(mapper).collect(Collectors.toList());

    if (selectedValue != null) {
      for (SelectOption value : selectOptionList) {
        boolean selected = value.getValue().equals(selectedValue);
        value.setSelected(selected);
      }
    }
    return selectOptionList;
  }

}
