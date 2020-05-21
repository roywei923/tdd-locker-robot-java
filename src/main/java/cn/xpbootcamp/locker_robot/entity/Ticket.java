package cn.xpbootcamp.locker_robot.entity;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Ticket {
  private UUID packageId;

  public Ticket() {
    this.packageId = UUID.randomUUID();
  }
}
