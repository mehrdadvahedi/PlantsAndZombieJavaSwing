import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class Bord {
  private JButton _button;
  private int _location;
  private int _zombieType;
  private Zombie _zombie;
  private int _plantType;
  private Plant _plant;

  public Bord(int location, JButton button) {
    _location = location;
    _button = button;
    _zombieType = 0;
    _zombie = null;
    _plantType = 0;
    _plant = null;
  }

  public JButton getButton() {
    return _button;
  }

  public int getLocation() {
    return _location;
  }

  public Zombie getZombie() {
    return _zombie;
  }

  public int getZombieType() {
    return _zombieType;
  }

  public Plant getPlant() {
    return _plant;
  }

  public int getPlantType() {
    return _plantType;
  }

  public void setZombie(Zombie zombie) {
    _zombie = zombie;
    _zombieType = zombie.getType();
    // System.out.println("setZ "+_zombie.getType());
  }

  public void setZombieNull() {
    _zombie = null;
    _zombieType = 0;
    _button.setIcon(null);
  }

  public void setZombieType(int zombieType, double clock) {
    if (zombieType != 0) {
      _zombieType = zombieType;
      this.setZombie(new Zombie(_zombieType, _location, clock));
      _button.setIcon(new ImageIcon("z" + (_zombieType) + ".png"));
    }
  }

  public void setZombieType(Zombie zombie, double clock) {
    if (zombie != null) {
      _zombieType = zombie.getType();
      this.setZombie(zombie);
      zombie.resetClock(clock);
      _button.setIcon(new ImageIcon("z" + (_zombieType) + ".png"));
    }
  }

  public void setPlantIconNull() {
    _button.setIcon(null);
    _button.setText(null);
  }

  public void setPlant(Plant plant) {
    _plant = plant;
  }

  public void setPlantNull() {
    this.setPlantIconNull();
    _plantType = 0;
    _plant = null;
  }

  public void setPlantType(int plantType, double clock) {
    if (plantType != 0 && _plantType == 0 && _zombieType == 0) {
      _plantType = plantType;
      this.setPlant(new Plant(_plantType, _location, clock));
      _button.setIcon(new ImageIcon("f" + (_plantType) + ".png"));
    }
  }

  public void updateView(Bord[] bord, double clock) {
    if (_zombieType != 0) {
      if (_zombie.getHeal() <= 0) {
        this.setZombieNull();
        return;
      }
      if (_zombie.getLocation() != _location) {
        bord[_zombie.getLocation()].setZombieType(_zombie, clock);
        this.setZombieNull();
      }
      _button.setIcon(new ImageIcon("z" + (_zombieType) + ".png"));
      if (_zombieType != 0) {
        _button.setFont(new Font("Arial", Font.PLAIN, 15));
        _button.setForeground(Color.decode("#ff0000"));
        _button.setText(_zombie.getHeal() + "");
      }
    } else if (_plantType != 0) {
      if (_plant.getHeal() <= 0) {
        this.setPlantNull();
        return;
      }
      _button.setIcon(new ImageIcon("f" + (_plantType) + ".png"));
      _button.setFont(new Font("Arial", Font.PLAIN, 15));
      _button.setForeground(Color.decode("#ff0000"));
      this.getButton().setText(_plant.getHeal() + "");
    } else {
      _button.setText("");
    }
  }

  public void resetColor() {
    // System.out.println("reser color");
    if (_location % 2 == 0) {
      _button.setBackground(Color.decode("#5EAB60"));
    } else {
      _button.setBackground(Color.decode("#74DF76"));
    }
  }

  public static boolean allZombieDead(Bord[] bord) {
    for (int i = 0; i < 45; i++) {
      if (bord[i].getZombieType() != 0) {
        return false;
      }
    }
    return true;
  }
}