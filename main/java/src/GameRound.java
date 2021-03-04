import java.util.ArrayList;
import java.util.List;

/**
 * GameRound
 */
public class GameRound {

  private List<GameAction> actions;

  public GameRound() {
    this.actions = new ArrayList<>();
  }
  
  public GameRound(List<GameAction> actions) {
    this.actions = actions;
  }

  public void addAction(GameAction action) {
    this.actions.add(action);
  }

  public List<GameAction> getActions() {
    return this.actions;
  }
}
