package task3;


import nl.altindag.console.ConsoleCaptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Tester;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.*;

public class Task3Test {
    final private static String INTERACTOR = "task3.Interactor";

    @DisplayName("Check that Interactor class is present")
    @Test
    void isTypeClassInteractor() {
        assertTrue(Tester.isTypeClass(INTERACTOR), "There is no class Interactor");
    }
    @DisplayName("Check that method serve is present")
    @Test
    void hasServeDeclaredMethod() {
        assertTrue(Tester.hasTypeDeclaredMethod(INTERACTOR, "serve",
                new Class[]{UnaryOperator.class, int.class}), "   ");//TODO
    }
    @DisplayName("Check that method consume is present")
    @Test
    void hasConsumeDeclaredMethod() {
        assertTrue(Tester.hasTypeDeclaredMethod(INTERACTOR, "consume",
                new Class[]{BinaryOperator.class, int.class}), "   ");//TODO
    }
    //Test 2,3 TODO
    @DisplayName("Check that consuming and producing threads work correctly")
    @Test
    void threadRun() {
        final Interactor interactor = new Interactor();
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();
        final Thread thread = new Thread(() -> {
            try {
                interactor.serve(n2 -> -n2, 5);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        final Thread thread2 = new Thread(() -> {
            try {
                interactor.consume((n, n3) -> n + n3, 10);
            } catch (InterruptedException ex3) {
            }

        });
        try {
            thread2.start();
            Thread.sleep(500L);
            thread.start();
            thread2.join();
            thread.join();
        } catch (InterruptedException ex2) {
            ex2.printStackTrace();
        }
        List<String> standardOutput = consoleCaptor.getStandardOutput();
        assertTrue(standardOutput.contains("Serving thread running"));
        assertTrue(standardOutput.contains("Serving thread initializes the key"));
        assertTrue(standardOutput.contains("key = -5"));
        assertTrue(standardOutput.contains("Consuming thread received the key. key = -5"));
        assertTrue(standardOutput.contains("Consuming thread changed the key. key = 5"));
        assertTrue(standardOutput.contains("Serving thread resumed"));
        consoleCaptor.close();
    }
    @DisplayName("Check that consuming and producing threads work correctly")
    @Test
    void threadRun2() {
        final Interactor interactor = new Interactor();
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();
        final Thread thread = new Thread(() -> {
            try {
                interactor.serve(x -> 3 + x, 6);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        final Thread thread2 = new Thread(() -> {
            try {
                interactor.consume((a, b) -> a * b, 8);
            } catch (InterruptedException ex3) {
            }

        });
        try {
            thread2.start();
            Thread.sleep(500L);
            thread.start();
            thread2.join();
            thread.join();
        } catch (InterruptedException ex2) {
            ex2.printStackTrace();
        }
        List<String> standardOutput = consoleCaptor.getStandardOutput();
        assertTrue(standardOutput.contains("Serving thread running"));
        assertTrue(standardOutput.contains("Serving thread initializes the key"));
        assertTrue(standardOutput.contains("key = 9"));
        assertTrue(standardOutput.contains("Consuming thread received the key. key = 9"));
        assertTrue(standardOutput.contains("Consuming thread changed the key. key = 72"));
        assertTrue(standardOutput.contains("Serving thread resumed"));
        consoleCaptor.close();
    }
    @DisplayName("Check that consuming thread works correctly")
    @Test
    void runConsumeOnly() {
        final Interactor interactor = new Interactor();
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();
        final Thread thread = new Thread(() -> {
            try {
                interactor.consume((n, n2) -> n + n2, 10);
            } catch (InterruptedException ex2) {
            }
        });
        try {
            thread.start();
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        List<String> standardOutput = consoleCaptor.getStandardOutput();
        assertTrue(standardOutput.contains("Consuming thread received the key. key = 0"));
        assertTrue(standardOutput.contains("Consuming thread changed the key. key = 10"));
        consoleCaptor.close();
    }


}
