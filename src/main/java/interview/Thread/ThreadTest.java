/**
 * @author phucle-compass
 */
package Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {

  public static void main(String[] args) {

    //initFixThreadPool();
    //initFixThreadPoolWithFuture();
    //initCompletableFutureWithCustomThreadPool();
    //initCompletableFutureToExecuteMutilpleAPIsConcurrently();
    //initCompletableFutureWithCombineResult();
    //initCompletableFutureWithHandlingException();
    //initCompletableFutureWithTimeout();
    //initCompletableFutureWithCustomThreadPoolExecutor();
    //initCompletableFutureWithRealMicroserviceAggregator();
    //initProducerConsumer();
    //initLock();
    //initReentrantLock();
    //resolveDeadlock();

    // 1. How do you create a deadlock scenario programmatically in Java?

    // 2. Implement a thread-safe singleton class.

    // 3. Implement a producer-consumer problem using wait() and notify() methods.

    // 4. Use Java’s ExecutorService to execute a list of tasks concurrently.

    // 5. Implement a thread-safe cache using ConcurrentHashMap.

    // 6. Write a program to demonstrate the use of atomic variables for thread-safe operations.

    // 7. Implement a deadlock scenario and explain how to avoid it.

    // 8. Use Java’s Lock interface to implement a custom lock for thread synchronization.

    // 9. Implement a thread-safe queue using BlockingQueue.

    // 10.Write a program to demonstrate the use of CompletableFuture for asynchronous programming.

    // 11.
  }

  private static void initFixThreadPool() {
    ExecutorService executor = Executors.newFixedThreadPool(5);
    for (int i = 0; i < 100; i++) {
      int id = i;
      executor.submit(() -> System.out.println(Thread.currentThread() + ": " + id));
    }

    executor.shutdown();
  }

  static void sleep(long ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  static String runUser() {
    sleep(2000);
    return "User";
  }

  static String runOrder() {
    sleep(1000);
    return "Order";
  }

  static String runPayment() {
    sleep(3000);
    return "Payment";
  }

  private static void initFixThreadPoolWithFuture() {
    ExecutorService executor = Executors.newFixedThreadPool(5);
    List<Future<Integer>> result = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      int num = i;
      result.add(executor.submit(() -> num * num));
    }

    result.stream()
        .forEach(f -> {
          try {
            System.out.println(f.get());
          } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
          }
        });

    executor.shutdown();
  }

  private static void initCompletableFutureWithCustomThreadPool() {
    ExecutorService executor = Executors.newFixedThreadPool(3);
    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
      System.out.println(Thread.currentThread().getName());
      sleep(2000);
      return "Hello Java";
    }, executor);

    System.out.println("Doing other task ...");
    String result = future.join();
    //future.orTimeout(2, TimeUnit.SECONDS).join();
    System.out.println("Waiting future is complete");
    System.out.println(result);
    executor.shutdown();
  }

  private static void initCompletableFutureToExecuteMutilpleAPIsConcurrently() {
    ExecutorService executors = Executors.newFixedThreadPool(3);

    CompletableFuture<String> user = CompletableFuture.supplyAsync(
        ThreadTest::runUser, executors);
    CompletableFuture<String> order = CompletableFuture.supplyAsync(
        ThreadTest::runOrder, executors);
    CompletableFuture<String> payment = CompletableFuture.supplyAsync(
        ThreadTest::runPayment, executors);

    CompletableFuture.allOf(user, order, payment).join();
    System.out.println("Main waiting");

    System.out.println(user.join());
    System.out.println(order.join());
    System.out.println(payment.join());

    executors.shutdown();
  }

  private static void initCompletableFutureWithCombineResult() {

    ExecutorService executors = Executors.newFixedThreadPool(3);

    CompletableFuture<String> user = CompletableFuture.supplyAsync(() -> "Tony",
        executors);
    CompletableFuture<Integer> age = CompletableFuture.supplyAsync(() -> 30,
        executors);

    CompletableFuture<String> combine = user.thenCombine(age,
        (name, year) -> name + ": " + year);

    System.out.println(combine.join());
    executors.shutdown();
  }

  private static void initCompletableFutureWithHandlingException() {

    ExecutorService executors = Executors.newFixedThreadPool(2);
    CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
          int x = 10 / 0;
          return x;
        }, executors)
        .exceptionally(e -> {
          System.out.println(e.getMessage());
          return -1;
        });

    System.out.println(future.join());
    executors.shutdown();
  }

  private static void initCompletableFutureWithTimeout() {

    ExecutorService executors = Executors.newFixedThreadPool(2);

    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
          sleep(3000);
          return "Done!";
        }, executors)
        .orTimeout(2, TimeUnit.SECONDS);

    try {
      System.out.println(future.join());
    } catch (Exception e) {
      System.out.println("Timeout!");
    }

    executors.shutdown();
  }


  /*
    ** Advantages
    **** Bounded queue
    **** Prevents OutOfMemoryError
    **** Rejection policy
    **** Better production control
   */
  private static void initCompletableFutureWithCustomThreadPoolExecutor() {

    ThreadPoolExecutor executor = new ThreadPoolExecutor(
        4,
        8,
        60,
        TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(100),
        Executors.defaultThreadFactory(),
        new ThreadPoolExecutor.CallerRunsPolicy()
    );

    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
      sleep(1000);
      return "Done!";
    }, executor);

    System.out.println(future.join());
    executor.shutdown();
  }

  private static void initCompletableFutureWithRealMicroserviceAggregator() {

    ExecutorService executor = Executors.newFixedThreadPool(3);
    CompletableFuture<User1> userFuture = CompletableFuture.supplyAsync(
        () -> new User1("Tony"),
        executor);

    CompletableFuture<Order1> orderFuture = CompletableFuture.supplyAsync(
        () -> new Order1(100),
        executor);

    CompletableFuture<Payment1> paymentFuture = CompletableFuture.supplyAsync(
        () -> new Payment1(2000),
        executor);

    Dashboard dashboard = CompletableFuture.allOf(
            userFuture,
            orderFuture,
            paymentFuture)
        .thenApply(v ->
            new Dashboard(
                userFuture.join(),
                orderFuture.join(),
                paymentFuture.join()
            )
        ).join();

    System.out.println(dashboard);
    executor.shutdown();
  }
  private static void initProducerConsumer() {
    LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    Thread producer = new Thread(() -> {
      for (int i = 1 ; i < 10; i++) {
        try {
          sleep(2000);
          queue.put(i);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    Thread consumer = new Thread(() -> {
      while (true) {
        try {
          System.out.println(queue.poll(5, TimeUnit.SECONDS)); // No BLOCK thread, return null if queue is empty
          System.out.println("Timeout!");
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }

      /*while (true) {
        try {
          //System.out.println(queue.take()); // BLOCK the thread until another thread inserts an element
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }*/
    });

    producer.start();
    consumer.start();
  }

  private static void initLock() {

    WaitNotification waitNotification = new WaitNotification();

    new Thread(() -> {
      try {
        waitNotification.printA();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }).start();

    new Thread(() -> {
      try {
        waitNotification.printB();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }).start();

    new Thread(() -> {
      try {
        waitNotification.printC();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }).start();
  }

  private static void initReentrantLock() {

    ConditionLockNotification lock = new ConditionLockNotification();

    new Thread(() -> {
      try {
        lock.printA();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }).start();

    new Thread(() -> {
      try {
        lock.printB();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }).start();

    new Thread(() -> {
      try {
        lock.printC();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }).start();
  }

  private static void resolveDeadlock() {

    ReentrantLock lockA = new ReentrantLock();
    ReentrantLock lockB = new ReentrantLock();

    new Thread(() -> {
      while (true) {

        if (lockA.tryLock()) {
          try {
            if (lockB.tryLock()) {
              try {
                System.out.println("t1 is working");
                break;
              } finally {
                lockB.unlock();
              }
            }
          } finally {
            lockA.unlock();
          }
        }

        Thread.yield();
      }
    }).start();

    new Thread(() -> {
      while (true) {

        if (lockA.tryLock()) {
          try {
            if (lockB.tryLock()) {
              try {
                System.out.println("t2 is working");
                break;
              } finally {
                lockB.unlock();
              }
            }
          } finally {
            lockA.unlock();
          }
        }

        Thread.yield();
      }
    }).start();
  }
}

record User1(String name) {}

record Order1(int count) {}

record Payment1(double balance) {}

record Dashboard(
    User1 user,
    Order1 order,
    Payment1 payment1
) {}

class Counter {
  private final AtomicInteger count = new AtomicInteger();

  void increment() {
    count.incrementAndGet();
  }

  int get() {
    return count.get();
  }
}

class WaitNotification {
  private final Object lock = new Object();
  private int state = 0;
  private final int retry = 3;

  public void printA() throws InterruptedException {

    for (int i = 1; i <= retry; i++) {
      synchronized (lock) {
        while (state != 0) {
          lock.wait();
        }

        System.out.println("A --- Round " + i + " --- " + Thread.currentThread().getName());
        state = 1;
        lock.notifyAll();
      }
    }
  }

  public void printB() throws InterruptedException {

    for (int i = 1; i <= retry; i++) {
      synchronized (lock) {
        while (state != 1) {
          lock.wait();
        }

        System.out.println("B --- Round " + i + " --- " + Thread.currentThread().getName());
        state = 2;
        lock.notifyAll();
      }
    }
  }

  public void printC() throws InterruptedException {

    for (int i = 1; i <= retry; i++) {
      synchronized (lock) {
        while (state != 2) {
          lock.wait();
        }

        System.out.println("C --- Round " + i + " --- " + Thread.currentThread().getName());
        state = 0;
        lock.notifyAll();
      }
    }
  }
}

class ConditionLockNotification {

  private final ReentrantLock lock = new ReentrantLock();
  private final Condition condition = lock.newCondition();
  private int state = 0;
  private final int retry = 3;

  void printA() throws InterruptedException {

      for (int i = 1; i <= retry; i++) {
        lock.lock();
        try {
            while (state != 0) {
              condition.await();
            }

            System.out.println("A --- Round " + i + " --- " + Thread.currentThread().getName());
            state = 1;
            condition.signalAll();
        } finally {
          lock.unlock();
        }
      }
  }

  void printB() throws InterruptedException {

    for (int i = 1; i <= retry; i++) {
      lock.lock();
      try {
        while (state != 1) {
          condition.await();
        }

        System.out.println("B --- Round " + i + " --- " + Thread.currentThread().getName());
        state = 2;
        condition.signalAll();
      } finally {
        lock.unlock();
      }
    }
  }

  void printC() throws InterruptedException {

    for (int i = 1; i <= retry; i++) {
      lock.lock();
      try {
        while (state != 2) {
          condition.await();
        }

        System.out.println("C --- Round " + i + " --- " + Thread.currentThread().getName());
        state = 0;
        condition.signalAll();
      } finally {
        lock.unlock();
      }
    }
  }
}