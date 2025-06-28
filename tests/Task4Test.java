package task4;

import nl.altindag.console.ConsoleCaptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Tester;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Task4Test {
    final private static String MY_THREADS = "task4.MyThreads";

    @BeforeEach
    void setUp() {
        MyThreads.m = 0;
        MyThreads.n = 0;
    }

    @DisplayName("Check that MyThreads class is present")
    @Test
    void isTypeClassMyThreads() {
        assertTrue(Tester.isTypeClass(MY_THREADS), "There is no class MyThreads");
    }

    @DisplayName("Check that order of operations for 2 threads is correct")
    @Test
    void startThreads() throws InterruptedException {
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();
        int j = 0;
        Thread thread1 = new Thread(() -> MyThreads.t1.run());
        Thread thread2 = new Thread(() -> MyThreads.t2.run());

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        List<String> standardOutput = consoleCaptor.getStandardOutput();
        for (int i = 0; i < 5; i++, j++) {
            String s = standardOutput.get(j);
            assertEquals("Thread1 n = " + i, s);
        }
        for (int i = 0; i < 5; i++, j++) {
            String s = standardOutput.get(j);
            assertEquals("Thread2 m = " + i, s);
        }
        for (int i = 5; i < 10; i++, j++) {
            String s = standardOutput.get(j);
            assertEquals("Thread2 n = " + i, s);
        }
        assertEquals("Thread2 success!", standardOutput.get(j++));

        for (int i = 5; i < 10; i++, j++) {
            String s = standardOutput.get(j);
            assertEquals("Thread1 m = " + i, s);
        }
        assertEquals("Thread1 success!", standardOutput.get(j));
        consoleCaptor.close();
    }

    @DisplayName("Check that order of operations for first thread is correct")
    @Test
    void startThreads1() throws InterruptedException {
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();
        consoleCaptor.clearOutput();
        int j = 0;
        Thread thread = new Thread(() -> MyThreads.t1.run());
        thread.start();
        thread.join();
        List<String> standardOutput = consoleCaptor.getStandardOutput();
        for (int i = 0; i < 5; i++, j++) {
            String s = standardOutput.get(j);
            assertEquals("Thread1 n = " + i, s);
        }
        for (int i = 0; i < 5; i++, j++) {
            String s = standardOutput.get(j);
            assertEquals("Thread1 m = " + i, s);
        }
        assertEquals("Thread1 success!", standardOutput.get(j));
        consoleCaptor.close();
    }

    @DisplayName("Check that order of operations for second thread is correct")
    @Test
    void startThreads2() throws InterruptedException {
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();
        int j = 0;

        Thread thread = new Thread(() -> MyThreads.t2.run());
        thread.start();
        thread.join();
        List<String> standardOutput = consoleCaptor.getStandardOutput();
        for (int i = 0; i < 5; i++, j++) {
            String s = standardOutput.get(j);
            assertEquals("Thread2 m = " + i, s);
        }
        for (int i = 0; i < 5; i++, j++) {
            String s = standardOutput.get(j);
            assertEquals("Thread2 n = " + i, s);
        }
        assertEquals("Thread2 success!", standardOutput.get(j));
        consoleCaptor.close();
    }
}
