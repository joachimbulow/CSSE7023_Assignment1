package mms.storage;

import jdk.jshell.spi.ExecutionControlProvider;
import mms.exceptions.BadItemException;
import mms.exceptions.PackingException;
import mms.exceptions.StorageFullException;
import mms.furniture.Furniture;
import mms.furniture.FurnitureType;
import mms.personal.Laptop;
import mms.personal.Personal;
import mms.utility.Size;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

public class MovingTruckTest {

    @Test
    public void constructorThrowsIllegalArgumentExceptionForSmallLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            MovingTruck movingTruck = new MovingTruck(500, 500, 500);
        });
    }

    @Test
    public void constructorWithSizeThrowsIllegalArgumentExceptionForSmallLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            MovingTruck movingTruck = new MovingTruck(500, 500, 500, Size.LARGE);
        });
    }

    @Test
    public void packThrowsStorageFullExceptionForTooManyItems() throws PackingException {
        MovingTruck mt = new MovingTruck(3000, 3000, 3000, Size.SMALL);
        Laptop test = new Laptop("Test", 2);
        assertThrows(StorageFullException.class, () -> {
            for (int i = 0; i <= mt.getCapacity() + 1; i++) {
                mt.pack(test);
            }
        });
    }

    @Test
    public void packThrowsStorageFullExceptionForExceedingDimension() throws PackingException {
        MovingTruck mt = new MovingTruck(1000, 1000, 1500, Size.SMALL);
        Furniture test = new Furniture(FurnitureType.TABLE);
        assertThrows(StorageFullException.class, () -> {
            mt.pack(test);
            mt.pack(test);
            mt.pack(test);
            mt.pack(test);

        });
    }

    @Test
    public void packThrowsBadItemExceptionWhenAddingPersonalAfterFurniture() {
        MovingTruck mt = new MovingTruck(10000, 10000, 10000, Size.SMALL);
        Furniture test = new Furniture(FurnitureType.BED);
        Laptop test2 = new Laptop("Jones", 3);
        assertThrows(BadItemException.class, () -> {
                mt.pack(test);
                mt.pack(test2);
        });
    }

    @Test
    public void packActuallyPacksItem() {
        MovingTruck mt = new MovingTruck(10000, 10000, 10000, Size.SMALL);
        Laptop test = new Laptop("Jones", 3);
        try {
            assertTrue(mt.getOccupiedCapacity() == 0);
            mt.pack(test);
            assertTrue(mt.getOccupiedCapacity() == 1);
        }
        catch (Exception e) {
            fail();
        }
    }

    @Test
    public void getMultiplier() {
        MovingTruck mt = new MovingTruck(10000, 10000, 10000, Size.SMALL);
        assertEquals(mt.getMultiplier(), 4);
    }

    @Test
    public void unpack() {

    }

    @Test
    public void getVolume() {
        MovingTruck mt = new MovingTruck(10000, 10000, 10000, Size.SMALL);
        assertTrue(mt.getVolume() == mt.getHeight() * mt.getWidth() * mt.getLength());
    }

    @Test
    public void testToStringWithoutItems() {
        MovingTruck mt = new MovingTruck(10000, 10000, 10000, Size.SMALL);
        assertTrue(mt.toString().equals("MovingTruck (0/" + mt.getCapacity() + ")"));
    }

    @Test
    public void testToStringWithItems() {
        MovingTruck mt = new MovingTruck(10000, 10000, 10000, Size.SMALL);
        Laptop test = new Laptop("Jones", 3);
        try {
            mt.pack(test);
            String gg = mt.toString();
            assertTrue(mt.toString().equals("MovingTruck (1/" + mt.getCapacity() + ")"));
        }
        catch (Exception e) {
            fail();
        }
    }
}