package de.schlossgaienhofen.project2019.data;

public class SelectOption {

  private String value;
  private String label;
  private Boolean selected;

  public SelectOption(String value, boolean selected) {
    this.value = value;
    this.label = value;
    this.selected = selected;
  }

  public SelectOption(String value, String label, boolean selected) {
    this.value = value;
    this.label = label;
    this.selected = selected;
  }

  public static SelectOption create(String value, String label) {
    return new SelectOption(value, label, false);
  }

  public static SelectOption create(String value, boolean selected) {
    return new SelectOption(value, value, selected);
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }
}
