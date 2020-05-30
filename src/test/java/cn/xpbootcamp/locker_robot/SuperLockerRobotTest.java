package cn.xpbootcamp.locker_robot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cn.xpbootcamp.locker_robot.entity.Package;
import cn.xpbootcamp.locker_robot.entity.Ticket;
import cn.xpbootcamp.locker_robot.exception.AllLockersFullException;
import cn.xpbootcamp.locker_robot.exception.TicketInvalidException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SuperLockerRobotTest {

  @Test
  void should_store_package_in_locker1_and_return_ticket_when_store_package_given_locker1_has_the_highest_vacancy_rate() {
    // Arrange
    List<Locker> lockers = Arrays.asList(
        createLocker(5, 3),
        createLocker(3, 2),
        createLocker(2, 1));
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(lockers);
    Package pack = new Package();

    // Act
    Ticket ticket = superLockerRobot.store(pack);

    // Assert
    assertNotNull(ticket);
    assertTrue(lockers.get(1).isPackageAvailable(ticket));
  }

  @Test
  void should_store_package_in_locker2_and_return_ticket_when_store_package_given_locker2_has_1_space_and_locker_1and3_are_full() {
    // Arrange
    List<Locker> lockers = Arrays.asList(
        createLocker(5, 0),
        createLocker(3, 1),
        createLocker(3, 0));
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(lockers);
    Package pack = new Package();

    // Act
    Ticket ticket = superLockerRobot.store(pack);

    // Assert
    assertNotNull(ticket);
    assertTrue(lockers.get(1).isPackageAvailable(ticket));
  }

  @Test
  void should_store_package_in_locker_2or3_and_return_ticket_when_store_package_given_locker1_is_full_and_locker_2and3_have_same_vacancy_rate() {
    // Arrange
    List<Locker> lockers = Arrays.asList(
        createLocker(5, 0),
        createLocker(3, 2),
        createLocker(6, 4));
    SuperLockerRobot superLockerRobot = new SuperLockerRobot(lockers);
    Package pack = new Package();

    // Act
    Ticket ticket = superLockerRobot.store(pack);

    // Assert
    assertNotNull(ticket);
    assertTrue(lockers.get(1).isPackageAvailable(ticket) || lockers.get(2).isPackageAvailable(ticket));
  }

  private Locker createLocker(int capacity, int availableSpace) {
    Locker locker = new Locker(capacity);
    for(int i = 0; i < capacity - availableSpace; i++) {
      locker.store(new Package());
    }

    return locker;
  }
}
