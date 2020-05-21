package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.entity.Package;
import cn.xpbootcamp.locker_robot.entity.Ticket;
import cn.xpbootcamp.locker_robot.exception.LockerFullException;
import cn.xpbootcamp.locker_robot.exception.TicketInvalidException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LockerTest {

  @Test
  void should_store_package_successfully_when_store_package_given_locker_is_not_full() {
    // given:
    Package pack = new Package();
    Locker locker = new Locker(5);

    // when:
    Ticket ticket = locker.store(pack);

    // then:
    assertNotNull(ticket);
  }

  @Test
  void should_store_package_failed_when_store_package_given_locker_is_full() {
    // given:
    Locker locker = new Locker(5);
    for (int i = 0; i < 5; i++) {
      Package pack = new Package();
      locker.store(pack);
    }

    // when, then:
    assertThrows(LockerFullException.class, () -> locker.store(new Package()));
  }

  @Test
  void should_get_package_successfully_when_store_ticket_given_ticket_is_valid() {
    // given:
    Locker locker = new Locker(5);
    Package storedPack = new Package();
    Ticket ticket = locker.store(storedPack);

    // when:
    Package pack = locker.get(ticket);

    // then:
    assertEquals(storedPack, pack);
  }

  @Test
  void should_get_package_failed_when_store_ticket_given_ticket_is_invalid() {
    // given:
    Locker locker = new Locker(5);
    Ticket invalidTicket = new Ticket();

    // when, then:
    assertThrows(TicketInvalidException.class, () -> locker.get(invalidTicket));
  }
}