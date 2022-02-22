public class Player {
  public int _sun = 50;
  public double _lastTimeZombie;

  public Player(int sun) {
    _sun = sun;
    _lastTimeZombie = 0;
  }

  public int getSun() {
    return _sun;
  }

  public double getLastTimeZombie() {
    return _lastTimeZombie;
  }

  public void pay(int[] cost, int index) {
    _sun -= cost[index];
  }

  public void addSun(int val) {
    _sun += val;
  }
}
