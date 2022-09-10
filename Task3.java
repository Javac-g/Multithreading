import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

class Interactor {

  int x;

  public synchronized void serve(UnaryOperator<Integer> unaryOperator, int init) throws InterruptedException {
    this.x = unaryOperator.apply(init);
    final String message = "Serving thread %s\n";
    System.out.printf(message, "running");
    System.out.printf(message, "initializes the key");
    System.out.printf("key = %s\n", this.x);
    notify();
    wait();
    System.out.println("Serving thread resumed");
  }

  public synchronized void consume(BinaryOperator<Integer> binaryOperator, int init ) throws InterruptedException {
    wait(3000);
    final String message = "Consuming thread %s the key. key = %d\n";
    System.out.printf(message, "received", this.x);
    this.x = binaryOperator.apply(this.x, init);
    System.out.printf(message, "changed", this.x);
    notify();
  }
}
