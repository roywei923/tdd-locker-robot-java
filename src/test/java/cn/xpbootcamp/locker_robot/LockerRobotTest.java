package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.entity.Package;
import cn.xpbootcamp.locker_robot.entity.Ticket;
import cn.xpbootcamp.locker_robot.exception.AllLockersFullException;
import cn.xpbootcamp.locker_robot.exception.TicketInvalidException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class LockerRobotTest {

  @Test
  void should_get_ticket_and_stored_in_locker1_when_store_package_given_locker1_is_not_full() {
    // given:
    Package pack = new Package();
    Locker locker1 = new Locker(5);
    LockerRobot lockerRobot = new LockerRobot(Arrays.asList(locker1, new Locker(5)));

    // when:
    Ticket ticket = lockerRobot.store(pack);

    // then:
    assertTrue(locker1.isPackageAvailable(ticket));
    assertNotNull(ticket);
  }

  @Test
  void should_get_ticket_and_stored_in_lockerN_when_store_package_given_one_locker_has_space() {
    // given:
    Package pack = new Package();
    ArrayList<Locker> lockers = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      Locker locker = new Locker(1);
      locker.store(new Package());
      lockers.add(locker);
    }
    lockers.add(new Locker(5));

    LockerRobot lockerRobot = new LockerRobot(lockers);

    // when:
    Ticket ticket = lockerRobot.store(pack);

    // then:
    assertTrue(lockers.get(lockers.size() - 1).isPackageAvailable(ticket));
    assertNotNull(ticket);
  }

  @Test
  void should_store_package_failed_when_store_package_given_all_lockers_are_full() {
    // given:
    Locker locker = new Locker(5);
    for (int i = 0; i < 5; i++) {
      Package pack = new Package();
      locker.store(pack);
    }
    LockerRobot lockerRobot = new LockerRobot(Collections.singletonList(locker));

    // when, then:
    assertThrows(AllLockersFullException.class, () -> lockerRobot.store(new Package()));
  }

  @Test
  void should_get_package_successfully_when_get_package_given_ticket_is_valid() {
    // given:
    Package storedPack = new Package();
    Locker locker1 = new Locker(5);
    LockerRobot lockerRobot = new LockerRobot(Arrays.asList(locker1, new Locker(5)));
    Ticket ticket = lockerRobot.store(storedPack);

    // when:
    Package pack = lockerRobot.get(ticket);

    // then:
    assertEquals(storedPack, pack);
  }

  @Test
  void should_throw_ticket_invalid_exception_when_get_package_given_ticket_is_invalid() {
    // given:
    Package storedPack = new Package();
    Locker locker1 = new Locker(5);
    LockerRobot lockerRobot = new LockerRobot(Arrays.asList(locker1, new Locker(5)));
    Ticket ticket = lockerRobot.store(storedPack);
    lockerRobot.get(ticket);

    // when, then:
    assertThrows(TicketInvalidException.class, () -> lockerRobot.get(ticket));
  }
}