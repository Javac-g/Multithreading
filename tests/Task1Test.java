package task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Tester;
import static org.junit.jupiter.api.Assertions.*;

class Task1Test {
    final private static String PARALLEL_CALCULATOR = "task1.ParallelCalculator";

    @DisplayName("Check that ParallelCalculator class is present")
    @Test
    void isTypeClassParallelCalculator() {
        assertTrue(Tester.isTypeClass(PARALLEL_CALCULATOR), "There is no class ParallelCalculator");
    }

    @DisplayName("Check that a class implements an interface")
    @Test
    void implementsTypeInterface() {
        assertTrue(Tester.implementsTypeInterface("java.lang.Runnable", PARALLEL_CALCULATOR),
                "There is no such interface");
    }

    @DisplayName("Check that ParallelCalculator class has 'result' field")
    @Test
    void hasTypeDeclaredField() {
        assertTrue(Tester.hasTypeDeclaredField(PARALLEL_CALCULATOR, "result"),
                "There is no 'result' field");

    }

    @DisplayName("Check that type of 'result' field is int")
    @Test
    void hasFieldType() {
        assertTrue(Tester.hasFieldType(PARALLEL_CALCULATOR, "result", int.class),
                "There is no 'result' field type int");
    }

    @DisplayName("Check that 'result' field isn't private")
    @Test
    void isFieldPrivate() {
        assertFalse(Tester.isFieldPrivate(PARALLEL_CALCULATOR, "result"),
                "The 'result' field isn't private");
    }
    @DisplayName("Check that result of operation sum method is correct ")
    @Test
    void isResultOp1Correct() throws InterruptedException {

        final ParallelCalculator target = new ParallelCalculator(Integer::sum, 3, 5);
        final Thread thread = new Thread(target);
        thread.start();
        thread.join();
        assertEquals(8, target.result, "Result of sum n1 + n2 isn't correct");
    }
    @DisplayName("Check that result of operation subtract method is correct")
    @Test
    void isResultOp2Correct() throws InterruptedException {
        final ParallelCalculator target = new ParallelCalculator((n1, n2) -> n1 - n2, 18, 3);
        final Thread thread = new Thread(target);
        thread.start();
        thread.join();
        assertEquals(15, target.result, "Result of subtract n1 - n2 isn't correct");
    }
}
