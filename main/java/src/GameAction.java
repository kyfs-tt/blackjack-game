/**
 * GameAction
 */
public class GameAction {

  GameActionType type;
  String desc;

  public GameAction(GameActionType type, String desc) {
    this.type = type;
    this.desc = desc;
  }

  public String getDesc() {
    return this.desc;
  }

  public GameActionType getType() {
    return this.type;
  }
}
