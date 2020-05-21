package cn.xpbootcamp.locker_robot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cn.xpbootcamp.locker_robot.entity.Package;
import cn.xpbootcamp.locker_robot.entity.Ticket;
import cn.xpbootcamp.locker_robot.exception.AllLockersFullException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SmartLockerRobotTest {

  @Test
  void should_store_package_in_locker3_and_return_ticket_when_store_package_given_locker123_has_available_spaces_123() {
    // Arrange
    List<Locker> lockers = Arrays.asList(
        createLocker(5, 1),
        createLocker(5, 2),
        createLocker(5, 3));
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockers);
    Package pack = new Package();

    // Act
    Ticket ticket = smartLockerRobot.store(pack);

    // Assert
    assertNotNull(ticket);
    assertTrue(lockers.get(2).isPackageAvailable(ticket));
  }

  @Test
  void should_store_package_in_locker1_and_return_ticket_when_store_package_given_locker123_has_available_spaces_321() {
    // Arrange
    List<Locker> lockers = Arrays.asList(
        createLocker(5, 3),
        createLocker(5, 2),
        createLocker(5, 1));
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockers);
    Package pack = new Package();

    // Act
    Ticket ticket = smartLockerRobot.store(pack);

    // Assert
    assertNotNull(ticket);
    assertTrue(lockers.get(0).isPackageAvailable(ticket));
  }

  @Test
  void should_store_package_in_locker2_and_return_ticket_when_store_package_given_locker123_has_available_spaces_010() {
    // Arrange
    List<Locker> lockers = Arrays.asList(
        createLocker(5, 0),
        createLocker(5, 1),
        createLocker(5, 0));
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockers);
    Package pack = new Package();

    // Act
    Ticket ticket = smartLockerRobot.store(pack);

    // Assert
    assertNotNull(ticket);
    assertTrue(lockers.get(1).isPackageAvailable(ticket));
  }

  @Test
  void should_store_package_successfully_and_return_ticket_when_store_package_given_locker123_has_available_spaces_333() {
    // Arrange
    List<Locker> lockers = Arrays.asList(
        createLocker(5, 3),
        createLocker(5, 3),
        createLocker(5, 3));
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockers);
    Package pack = new Package();

    // Act
    Ticket ticket = smartLockerRobot.store(pack);

    // Assert
    assertNotNull(ticket);
  }

  @Test
  void should_throw_AllLockersFullException_when_store_package_given_all_lockers_are_full() {
    // Arrange
    List<Locker> lockers = Arrays.asList(
        createLocker(3, 0),
        createLocker(3, 0),
        createLocker(3, 0));
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockers);

    // Act, Assert
    assertThrows(AllLockersFullException.class, () -> smartLockerRobot.store(new Package()));
  }

  @Test
  void should_get_the_right_package_when_get_package_given_ticket_is_valid() {
    // Arrange
    List<Locker> lockers = Arrays.asList(
        createLocker(3, 1),
        createLocker(3, 2),
        createLocker(3, 3));
    SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockers);
    Package pack = new Package();
    Ticket ticket = smartLockerRobot.store(pack);

    // Act
    Package actualPack = smartLockerRobot.get(ticket);

    // Assert
    assertEquals(pack, actualPack);
  }

  private Locker createLocker(int capacity, int availableSpace) {
    Locker locker = new Locker(capacity);
    for(int i = 0; i < capacity - availableSpace; i++) {
      locker.store(new Package());
    }

    return locker;
  }
}
