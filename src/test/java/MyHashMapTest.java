import map.HashMap;
import map.exception.ElementNotFoundException;
import map.exception.NoUsableKeyException;
import org.junit.Assert;
import org.junit.Test;

public class MyHashMapTest {

    @Test
    public void putAndGetOk() {
        HashMap hashMap = new HashMap();
        hashMap.put(0, 12);
        hashMap.put(3, 14);
        hashMap.put(7, 0);

        Assert.assertEquals("The size isn't correct.", 3, hashMap.size());

        long firstActualValue = hashMap.get(0);
        long secondActualValue = hashMap.get(3);
        long thirdActualValue = hashMap.get(7);
        Assert.assertEquals("Expected value: " + 12 + ", but was: " + firstActualValue, 12, firstActualValue);
        Assert.assertEquals("Expected value: " + 14 + ", but was: " + secondActualValue, 14, secondActualValue);
        Assert.assertEquals("Expected value: " + 0 + ", but was: " + thirdActualValue, 0, thirdActualValue);
    }

    @Test
    public void putAndGetWithCustomCapacity() {
        HashMap hashMap = new HashMap(5);
        hashMap.put(0, 12);
        hashMap.put(3, 14);
        hashMap.put(7, 0);

        Assert.assertEquals("The size isn't correct.", 3, hashMap.size());

        long firstActualValue = hashMap.get(0);
        long secondActualValue = hashMap.get(3);
        long thirdActualValue = hashMap.get(7);
        Assert.assertEquals("Expected value: " + 12 + ", but was: " + firstActualValue, 12, firstActualValue);
        Assert.assertEquals("Expected value: " + 14 + ", but was: " + secondActualValue, 14, secondActualValue);
        Assert.assertEquals("Expected value: " + 0 + ", but was: " + thirdActualValue, 0, thirdActualValue);
    }

    @Test
    public void putAndGetWithNegativeKeys() {
        HashMap hashMap = new HashMap();
        hashMap.put(-2, 12);
        hashMap.put(-3, 14);
        hashMap.put(-7, 0);

        Assert.assertEquals("The size isn't correct.", 3, hashMap.size());

        long firstActualValue = hashMap.get(-2);
        long secondActualValue = hashMap.get(-3);
        long thirdActualValue = hashMap.get(-7);
        Assert.assertEquals("Expected value: " + 12 + ", but was: " + firstActualValue, 12, firstActualValue);
        Assert.assertEquals("Expected value: " + 14 + ", but was: " + secondActualValue, 14, secondActualValue);
        Assert.assertEquals("Expected value: " + 0 + ", but was: " + thirdActualValue, 0, thirdActualValue);
    }

    @Test
    public void putAndGetOverridingElementsByKey() {
        HashMap hashMap = new HashMap();
        hashMap.put(0, 12);
        hashMap.put(3, 14);
        hashMap.put(7, 0);
        hashMap.put(0, 15);
        long firstActualValue = hashMap.get(0);
        long secondActualValue = hashMap.get(3);
        long thirdActualValue = hashMap.get(7);
        Assert.assertEquals("Expected value: " + 15 + ", but was: " + firstActualValue, 15, firstActualValue);
        Assert.assertEquals("Expected value: " + 14 + ", but was: " + secondActualValue, 14, secondActualValue);
        Assert.assertEquals("Expected value: " + 0 + ", but was: " + thirdActualValue, 0, thirdActualValue);
    }

    @Test(expected = NoUsableKeyException.class)
    public void putMinusOneKey() {
        HashMap hashMap = new HashMap();
        hashMap.put(-1, 10);
    }

    @Test(expected = NoUsableKeyException.class)
    public void getMinusOneKey() {
        HashMap hashMap = new HashMap();
        hashMap.get(-1);
    }

    @Test
    public void putAndGetWithCollision() {
        HashMap hashMap = new HashMap();
        hashMap.put(16, 12);
        hashMap.put(3, 14);
        hashMap.put(7, 0);
        hashMap.put(32, 15);
        long firstActualValue = hashMap.get(16);
        long secondActualValue = hashMap.get(3);
        long thirdActualValue = hashMap.get(7);
        long forthActualValue = hashMap.get(32);
        Assert.assertEquals("Expected value: " + 12 + ", but was: " + firstActualValue, 12, firstActualValue);
        Assert.assertEquals("Expected value: " + 14 + ", but was: " + secondActualValue, 14, secondActualValue);
        Assert.assertEquals("Expected value: " + 0 + ", but was: " + thirdActualValue, 0, thirdActualValue);
        Assert.assertEquals("Expected value: " + 15 + ", but was: " + forthActualValue, 15, forthActualValue);
    }

    @Test(expected = ElementNotFoundException.class)
    public void getByNonExistedKey() {
        HashMap hashMap = new HashMap();
        long failedValue = hashMap.get(32);
    }

    @Test(expected = ElementNotFoundException.class)
    public void getByNonExistedKeyWithCollision() {
        HashMap hashMap = new HashMap();
        hashMap.put(16, 12);
        hashMap.put(3, 14);
        hashMap.put(7, 0);
        long failedValue = hashMap.get(32);
    }

    @Test
    public void getSizeOfEmptyHashMap() {
        HashMap hashMap = new HashMap();
        Assert.assertEquals("The size isn't correct. Expected: " + 0 + ", but was: " + hashMap.size(), 0, hashMap.size());
    }

    @Test
    public void getSizeAfterResize() {
        HashMap hashMap = new HashMap();
        for (int i = 1; i <= 100; i++) {
            hashMap.put(i, 10);
        }
        Assert.assertEquals("The size isn't correct.", 100, hashMap.size());
    }

    @Test
    public void putAndGetWithBigCapacity() {
        HashMap hashMap = new HashMap();
        for (int i = 1; i <= 9000000; i++) {
            hashMap.put(i, i);
        }

        long actualValue = hashMap.get(9000000);
        Assert.assertEquals("Expected value: " + 9000000 + ", but was: " + actualValue, 9000000, actualValue);
    }
}
