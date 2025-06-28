package task2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Tester;

class Task2Test {
    final private static String ACCOUNTANT = "task2.Accountant";

    @DisplayName("Check that Accountant class is present")
    @Test
    void isTypeClassAccountant() {
        assertTrue(Tester.isTypeClass(ACCOUNTANT), "There is no class  Accountant");
    }

    @DisplayName("Check that method sum is present")
    @Test
    void hasSumDeclaredMethod() {
        assertTrue(Tester.hasTypeDeclaredMethod(ACCOUNTANT, "sum",
                new Class[]{int.class, int.class}), "There is no sum method");
    }

    @DisplayName("Checks if sum method is public in class Accountant")
    @Test
    public void isMethodPublic() {
        assertTrue(Tester.isMethodPublic(ACCOUNTANT, "sum", new Class[]{int.class, int.class}),
                "The method sum in class Accountant is not public");
    }

    @DisplayName("Checks if sum method between 3 and 5 correct")
    @Test
    void isResultOp1Correct() {
       assertEquals( 8, Accountant.sum(3, 5),
                "Result of sum op1 3 and op2 5 is not correct");

    }

    @DisplayName("Checks if sum method between 115 and 2095 correct")
    @Test
    void isResultOp2Correct() {
        assertEquals(2210, Accountant.sum(115, 2095),
                "Result of sum 115 and 2095 is not correct");
    }
}
