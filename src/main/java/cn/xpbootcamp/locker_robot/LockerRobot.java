package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.entity.Package;
import cn.xpbootcamp.locker_robot.entity.Ticket;
import cn.xpbootcamp.locker_robot.exception.AllLockersFullException;
import cn.xpbootcamp.locker_robot.exception.TicketInvalidException;

import java.util.List;

public class LockerRobot {

  private List<Locker> lockerList;

  public LockerRobot(List<Locker> lockerList) {
    this.lockerList = lockerList;
  }

  public Ticket store(Package pack) {
    return lockerList.stream()
        .filter(locker -> !locker.isFull())
        .findFirst().map(locker -> locker.store(pack))
        .orElseThrow(AllLockersFullException::new);
  }

  public Package get(Ticket ticket) {
    return lockerList.stream()
        .filter(locker -> locker.isPackageAvailable(ticket))
        .findFirst().map(locker -> locker.get(ticket))
        .orElseThrow(TicketInvalidException::new);
  }
}
