package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.entity.Package;
import cn.xpbootcamp.locker_robot.entity.Ticket;
import java.util.List;
import java.util.PriorityQueue;

public class SmartLockerRobot {

  private PriorityQueue<Locker> lockers;

  public SmartLockerRobot(List<Locker> lockers) {
    this.lockers = new PriorityQueue<>(
        (locker1, locker2) -> locker2.getAvailableSpace() - locker1.getAvailableSpace());
    lockers.forEach(locker -> this.lockers.offer(locker));
  }

  public Ticket store(Package pack) {
    Locker locker = lockers.peek();
    if(!locker.isFull()) {
      return locker.store(pack);
    }

    return null;
  }
}
