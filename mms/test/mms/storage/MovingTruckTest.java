package mms.storage;

import mms.exceptions.PackingException;
import mms.exceptions.PackingOrderException;
import mms.exceptions.StorageFullException;
import mms.furniture.Furniture;
import mms.furniture.FurnitureType;
import mms.personal.Laptop;
import mms.utility.Packable;
import mms.utility.Size;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MovingTruckTest {

    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsIllegalArgumentExceptionForSmallLength() {
        MovingTruck movingTruck = new MovingTruck(500, 500, 500);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithSizeThrowsIllegalArgumentExceptionForSmallLength() {
        MovingTruck movingTruck = new MovingTruck(500, 500, 500, Size.LARGE);
    }

    @Test(expected = StorageFullException.class)
    public void packThrowsStorageFullExceptionForTooManyItems() throws PackingException {
        MovingTruck mt = new MovingTruck(3000, 3000, 3000, Size.SMALL);
        Laptop test = new Laptop("Test", 2);
        for (int i = 0; i <= mt.getCapacity() + 1; i++) {
            mt.pack(test);
        }
    }

    @Test(expected = StorageFullException.class)
    public void packThrowsStorageFullExceptionForExceedingDimension() throws PackingException {
        MovingTruck mt = new MovingTruck(1000, 1000, 1500, Size.SMALL);
        Furniture test = new Furniture(FurnitureType.TABLE);
        mt.pack(test);
        mt.pack(test);
        mt.pack(test);
        mt.pack(test);
    }

    @Test(expected = PackingOrderException.class)
    public void packThrowsPackingOrderExceptionWhenAddingPersonalAfterFurniture() throws PackingException {
        MovingTruck mt = new MovingTruck(10000, 10000, 10000, Size.SMALL);
        Furniture test = new Furniture(FurnitureType.BED);
        Laptop test2 = new Laptop("Jones", 3);
        mt.pack(test);
        mt.pack(test2);
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
    public void unpack() throws PackingException {
        List<Packable> expected = new ArrayList<>();
        Furniture f1 = new Furniture(FurnitureType.TABLE);
        Laptop p1 = new Laptop("Jones", 3);
        Furniture f2 = new Furniture(FurnitureType.BED);
        Laptop p2 = new Laptop("Jones", 3);
        Furniture f3 = new Furniture(FurnitureType.CHAIR);
        Laptop p3 = new Laptop("Jones", 3);
        MovingTruck testTruck = new MovingTruck(2000, 2000, 2000);
        testTruck.pack(p1);
        testTruck.pack(p2);
        testTruck.pack(p3);
        testTruck.pack(f1);
        testTruck.pack(f2);
        testTruck.pack(f3);


        //Making expected after 4 unpacks
        expected.add(p1);
        expected.add(p2);

        // Unpacking
        for (int i = 0; i < 4; i++) {
            testTruck.unpack();
        }
        assertTrue(testTruck.getElements().get(0) == expected.get(0));
        assertTrue(testTruck.getElements().get(1) == expected.get(1));

    }

    @Test
    public void unpackReturnNullWhenEmpty() {
        MovingTruck testTruck = new MovingTruck(2000, 2000, 2000);
        assertTrue(testTruck.unpack() == null);

    }

    @Test
    public void getVolume() {
        MovingTruck mt = new MovingTruck(1000, 1000, 1501, Size.SMALL);
        assertEquals(mt.getVolume(), 1000000, 0.1);
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