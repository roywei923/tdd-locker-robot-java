package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.entity.Package;
import cn.xpbootcamp.locker_robot.entity.Ticket;
import cn.xpbootcamp.locker_robot.exception.LockerFullException;
import cn.xpbootcamp.locker_robot.exception.TicketInvalidException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Locker {

  private int capacity;
  private Map<UUID, Package> storedPacks = new HashMap<>();

  public Locker(int capacity) {
    this.capacity = capacity;
  }

  public Ticket store(Package pack) {
    if (!isFull()) {
      Ticket ticket = new Ticket();
      storedPacks.put(ticket.getPackageId(), pack);
      return ticket;
    }
    throw new LockerFullException();
  }


  public Package get(Ticket ticket) {
    if (storedPacks.containsKey(ticket.getPackageId())) {
      Package pack = storedPacks.get(ticket.getPackageId());
      storedPacks.remove(ticket.getPackageId());
      return pack;
    }
    throw new TicketInvalidException();
  }

  public boolean isFull() {
    return storedPacks.size() >= capacity;
  }

  public boolean isPackageAvailable(Ticket ticket) {
    return storedPacks.containsKey(ticket.getPackageId());
  }
}
