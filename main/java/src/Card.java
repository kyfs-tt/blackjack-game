import java.util.Objects;

public class Card {

  private int value;
  private String name;

  public Card(String name, int value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return this.name;
  }

  public int getValue() {
    return this.value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Card card = (Card) o;
    return Objects.equals(this.name, card.name) &&
      Objects.equals(this.value, card.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name + this.value);
  }
}
