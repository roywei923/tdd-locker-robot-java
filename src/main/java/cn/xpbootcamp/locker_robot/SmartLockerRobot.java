package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.entity.Package;
import cn.xpbootcamp.locker_robot.entity.Ticket;
import java.util.List;

public class SmartLockerRobot {

  private List<Locker> lockers;

  public SmartLockerRobot(List<Locker> lockers) {
    this.lockers = lockers;
  }

  public Ticket store(Package pack) {
    return null;
  }
}
