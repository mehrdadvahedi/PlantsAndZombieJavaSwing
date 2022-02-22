import java.awt.Color;

public class Bullet {
  private static double _speed = 0.025;
  private int _location;
  private double _lastTimeMove;
  private int _power;
  private boolean _heal;

  public Bullet(int location, int power, double clock) {
    _location = location;
    _lastTimeMove = clock;
    _power = power;
    _heal = true;
  }

  public int getLocation() {
    return _location;
  }

  public boolean getHeal() {
    return _heal;
  }

  public void kill() {
    _heal = false;
  }

  public void move(Bord[] bord) {
    _location += 1;
    if (bord[_location].getZombieType() != 0) {
      this.shot(bord[_location].getZombie());
      bord[_location].resetColor();
    }
    if (((int) (_location / 9) + 1) * 9 - 1 == _location) {
      this.kill();
    }
  }

  public void shot(Zombie zombie) {
    zombie.die(_power);
    this.kill();
  }

  public void updateView(Bord[] bord, double clock) {
    int lastLocation = _location;
    if ((Math.abs(clock - _lastTimeMove) >= _speed) && (_heal)) {
      this.move(bord);
      bord[lastLocation].resetColor();
      if (_heal) {
        bord[_location].getButton().setBackground(Color.decode("#ff5511"));
      }
      _lastTimeMove = clock;
    }
  }
}
