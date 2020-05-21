package cn.xpbootcamp.locker_robot;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cn.xpbootcamp.locker_robot.entity.Package;
import cn.xpbootcamp.locker_robot.entity.Ticket;
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

  private Locker createLocker(int capacity, int availableSpace) {
    Locker locker = new Locker(capacity);
    for(int i = 0; i < capacity - availableSpace; i++) {
      locker.store(new Package());
    }

    return locker;
  }
}
