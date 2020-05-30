package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.entity.Ticket;
import cn.xpbootcamp.locker_robot.entity.Package;
import cn.xpbootcamp.locker_robot.exception.AllLockersFullException;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.UUID;

public class SuperLockerRobot {

  private PriorityQueue<Locker> lockers;

  private HashMap<UUID, Locker> packageLockerMapping;

  public SuperLockerRobot(List<Locker> lockers) {
    packageLockerMapping = new HashMap<>(100);
    this.lockers = new PriorityQueue<>(
        (locker1, locker2) -> (int)(calculateVacancyPercentage(locker2) - calculateVacancyPercentage(locker1)));
    lockers.forEach(locker -> this.lockers.offer(locker));
  }

  public Ticket store(Package pack) {
    Locker locker = lockers.poll();
    if(locker.isFull()) {
      throw new AllLockersFullException();
    }

    Ticket ticket = locker.store(pack);
    packageLockerMapping.put(ticket.getPackageId(), locker);
    lockers.offer(locker);

    return ticket;
  }

  private double calculateVacancyPercentage(Locker locker) {
    return locker.getAvailableSpace() * 100.0 / locker.getCapacity();
  }
}
