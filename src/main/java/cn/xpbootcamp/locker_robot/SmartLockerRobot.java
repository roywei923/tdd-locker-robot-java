package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.entity.Package;
import cn.xpbootcamp.locker_robot.entity.Ticket;
import cn.xpbootcamp.locker_robot.exception.AllLockersFullException;
import cn.xpbootcamp.locker_robot.exception.TicketInvalidException;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.UUID;

public class SmartLockerRobot {

  private PriorityQueue<Locker> lockers;

  private HashMap<UUID, Locker> packageLockerMapping;

  public SmartLockerRobot(List<Locker> lockers) {
    packageLockerMapping = new HashMap<>(100);
    this.lockers = new PriorityQueue<>(
        (locker1, locker2) -> locker2.getAvailableSpace() - locker1.getAvailableSpace());
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

  public Package get(Ticket ticket) {
    if(!packageLockerMapping.containsKey(ticket.getPackageId())) {
      throw new TicketInvalidException();
    }
    Locker locker = packageLockerMapping.get(ticket.getPackageId());
    packageLockerMapping.remove(ticket.getPackageId());
    lockers.remove(locker);
    Package pack = locker.get(ticket);
    lockers.offer(locker);

    return pack;
  }
}
