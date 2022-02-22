import java.util.ArrayList;

public class Plant {
  private int _type;

  private int _location;

  private int _power;

  private int _cost;

  private double _coolDown;

  private int _heal;

  public double _lastWorkTime;

  public Plant(int type, int location, double clock) {
    _type = type;
    _location = location;
    _heal = _type == 4 ? 1 : 3;
    _lastWorkTime = clock;
    switch (type) {
      case 1: {
        _power = 20;
        _cost = 100;
        _coolDown = 2;
        break;
      }
      case 2: {
        _power = 0;
        _cost = 50;
        _coolDown = 2;
        break;
      }
      case 3: {
        _power = 20;
        _cost = 200;
        _coolDown = 2;
        break;
      }
      case 4: {
        _power = 100;
        _cost = 50;
        _coolDown = 2;
        break;
      }
    }
  }

  public int getType() {
    return _type;
  }

  public int getLocation() {
    return _location;
  }

  public int getPower() {
    return _power;
  }

  public int getCost() {
    return _cost;
  }

  public double getCoolDown() {
    return _coolDown;
  }

  public int getHeal() {
    return _heal;
  }

  public void die() {
    _heal -= 1;
  }

  public void kill() {
    _heal = 0;
  }

  public void work(Bord[] bord, ArrayList<Bullet> bullets, Player player, double clock) {
    if (_heal > 0) {
      switch (_type) {
        case 1: {
          if (isHereZombie(bord, _location)) {
            if (Math.abs(_lastWorkTime - clock) >= 1.5) {
              bullets.add(new Bullet(_location, _power, clock));
              _lastWorkTime = clock;
            }
          }
          break;
        }
        case 2: {
          if (Math.abs(_lastWorkTime - clock) >= 5) {
            player.addSun(50);
            _lastWorkTime = clock;
          }
          break;
        }
        case 3: {
          if (isHereZombie(bord, _location)) {
            if (Math.abs(_lastWorkTime - clock) >= 0.75) {
              bullets.add(new Bullet(_location, _power, clock));
              _lastWorkTime = clock;
            }
          }
          break;
        }
        case 4: {
          if (bord[_location].getZombieType() != 0) {
            bord[_location].getZombie().die(_power);
            this.kill();
          }
          break;
        }
      }
    }
  }

  public static boolean isHereZombie(Bord[] bord, int location) {
    for (int i = location + 1; i <= (((int) (location / 9) + 1) * 9 - 1); i++) {
      if (bord[i].getZombieType() != 0) {
        return true;
      }
    }
    return false;
  }

}
