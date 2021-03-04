import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

public class Player {

  private String name;
  Set<Card> cards;

  public Player(String name) {
    this.name = name;
    this.cards = new HashSet<>();
  }

  public String getName() {
    return this.name;
  }

  public void receiveCard(Card card) {
    this.cards.add(card);
  }

  public int getScore() {
    int total = 0;

    for(var c : this.cards) {
      total += c.getValue();
    }

    return total;

    // stream implementation
    // return this.cards.values().stream()
    //   .reduce(0, Integer::sum);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Player player = (Player) o;
    return Objects.equals(this.name, player.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name);
  }

  @Override
  public String toString() {
    return this.name;
  }

}
