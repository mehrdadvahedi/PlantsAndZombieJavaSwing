public class Zombie {
  private static double _speed = 3;
  public static int _coolDown = 2;
  private int _type;
  private int _heal;
  private int _location;
  private int _cost;
  private double _lastMoveTime;
  private double _lastWorkTime;
  private boolean _isWorking;

  public Zombie(int type, int location, double clock) {
    _type = type;
    _location = location;
    _lastMoveTime = clock;
    _lastWorkTime = clock;
    _isWorking = false;
    switch (_type) {
      case 1: {
        _heal = 100;
        _cost = 50;
        break;
      }
      case 2: {
        _heal = 200;
        _cost = 150;
        break;
      }
    }
  }

  public Zombie(Zombie zombie, double clock) {
    _type = zombie.getType();
    _heal = zombie.getHeal();
    _location = zombie.getLocation();
    _cost = zombie.getCost();
    _coolDown = zombie.getCoolDown();
    _lastMoveTime = clock;
    _lastWorkTime = clock;
    _isWorking = zombie.getIsWorking();
  }

  public int getType() {
    return _type;
  }

  public int getHeal() {
    return _heal;
  }

  public int getLocation() {
    return _location;
  }

  public int getCost() {
    return _cost;
  }

  public int getCoolDown() {
    return _coolDown;
  }

  public double getLastMoveTime() {
    return _lastMoveTime;
  }

  public double getLastWorkTime() {
    return _lastWorkTime;
  }

  public boolean getIsWorking() {
    return _isWorking;
  }

  public void die(int bang) {
    _heal -= bang;
  }

  public void kill() {
    _heal = 0;
  }

  public void resetClock(double clock) {
    _lastMoveTime = clock;
    _lastWorkTime = clock;
  }

  public void startWorking() {
    _isWorking = true;
  }

  public void stopWorking() {
    _isWorking = false;
  }

  public void move(double clock) {
    if (_isWorking) {
      _lastMoveTime = clock;
    }
    if (Math.abs(clock - _lastMoveTime) >= _speed) {
      _location -= 1;
    }
  }

  public void work(Bord[] bord, double clock) {
    if (_location != ((int) (_location / 9)) * 9) {
      if (!_isWorking &&
          bord[_location - 1].getPlantType() != 0 &&
          bord[_location - 1].getPlantType() != 4) {
        this.startWorking();
        return;
      } else if (_isWorking &&
          (bord[_location - 1].getPlantType() == 0 ||
              bord[_location - 1].getPlantType() == 4)) {
        this.stopWorking();
      }
    }
    if (_isWorking) {
      if (Math.abs(clock - _lastWorkTime) >= 1) {
        bord[_location - 1].getPlant().die();
        _lastWorkTime = clock;
      }
    }
  }
}
