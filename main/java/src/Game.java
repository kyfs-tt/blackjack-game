import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Game
 */
public class Game {

  private Deck deck;
  // private Set<Player> players;
  // private List<Pair<Player, Boolean>> activePlayers;
  private Map<Player, Boolean> players;
  // private int round;
  private List<GameRound> rounds;
  private Boolean hasEnded;
  private Player winner;

  public Game(Deck deck, Set<Player> players) {
    this.rounds = new ArrayList<>();
    this.deck = deck;
    this.hasEnded = false;
    this.players = new HashMap<>();
    for(var p : players) {
      this.players.put(p, true);
    }
  }
  
  public void playRound() {
    /* assume game has not ended */
    /* and assume there are more cards */
    var round = new GameRound();

    if(this.rounds.isEmpty()) {
      // deal 2 cards to each player for first round
      this.players.forEach((p, status) -> {
        if(Boolean.TRUE.equals(status)) {
          round.addAction(
            this.playAction(p, GameActionType.HIT)
          );
          round.addAction(
            this.playAction(p, GameActionType.HIT)
          );
        }
      });
    } else {
      // deal one card to each player if valid
      // for subsequent rounds
      this.players.forEach((p, status) -> {
        if(s) {
          // hit if score is less than 17
          if(p.getScore() < 17) {
            round.addAction(
              this.playAction(p, GameActionType.HIT)
            );
          } else if(p.getScore() > 17 && p.getScore() < 21) {
            // player sticks
            round.addAction(
              this.playAction(p, GameActionType.STICK)
            );
          } else {
            // player goes bust (is ejected from game)
            round.addAction(
              this.playAction(p, GameActionType.GO_BUST)
            );
          }
        }
      });
    }

    // register the round
    this.rounds.add(round);
  }

  public bool checkIfGameHasEnded() {
    // check if game has started
    if(this.rounds.isEmpty()) {
      this.hasEnded = false;
      return false;
    }

    // check if all players stuck in the last round
    var lastRound = this.rounds.get(this.rounds.size() - 1);
    var allStuck = true;
    for (var action : lastRound.getActions()) {
      if(!action.getType().equals(GameActionType.STICK)) {
        allStuck = false;
      }
    }
    if(allStuck) {
      this.hasEnded = true;
      this.deduceWinner();
      return true;
    }

    // check if any player has hit exactly 21
    this.players.forEach((p, status) -> {
      if(p.getScore() == 21) {
        this.hasEnded = true;
        this.deduceWinner();
        // this.winner = p;
        return true;
      }
    });

    // check if only one player is left
    Object wrapper = new Object() { 
      int activePlayerCount = 0;
    };
    this.players.forEach((p, status) -> {
      if(status) {
        wrapper.activePlayerCount++;
      }
    });
    if(wrapper.activePlayerCount == 1) {
      this.hasEnded = true;
      this.deduceWinner();
      return true;
    }
  }

  private void deduceWinner() {
    // TODO: implementation
    // * First check exactly 21
    // * Then check for closest to, but under 21
  }

  private GameAction playAction(Player p, GameActionType t) {
    switch(t) {
      case HIT:
        p.receiveCard(this.deck.dealCard());
        break;
      case STICK:
        break;
      case GO_BUST:
        this.players.put(p, false);
        break;
      default:
        // TODO: use custom exception
        throw new Exception("Invalid game action: " + t);
    }
    return new GameAction(
      t,
      "[R" + (this.rounds.size() + 1) + "]: " + p + t.name()
    );
  }

}
