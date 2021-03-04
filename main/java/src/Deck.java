import java.util.Set;
import java.util.Random;

public class Deck {

  private Set<Card> cards;

  public Deck(Set<Card> cards) {
    this.cards = cards;
  }

  public void addCard(Card card) {
    this.cards.add(card);
  }

  public void removeCard(Card card) {
    this.cards.remove(card);
  }

  public Set<Card> getCards() {
    return this.cards;
  }

  public Card dealCard() {
    int size = this.cards.size();
    int item = new Random().nextInt(size);
    int i = 0;
    for(Object obj : this.cards) {
      if(i == item) {
        this.cards.remove(obj);
        return obj;
      }
      i++;
    }
    return null;
  }

  public void shuffle(/* ShuffleMethod sm */) {
    // shuffle cards based on method passed
  }
  

}
