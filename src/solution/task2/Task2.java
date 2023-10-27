package solution.task2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Task2 {
    private int aim;
    private AtomicLong number = new AtomicLong(0);
    private boolean finished = false;
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
    private ScheduledFuture<?> incrementTask;
    private ScheduledFuture<?> numberTask;
    private ScheduledFuture<?> fizzTask;
    private ScheduledFuture<?> buzzTask;
    private ScheduledFuture<?> fizzbuzzTask;

    public Task2(int aim) {
        this.aim = aim;
    }

    public void start() {
        incrementTask = scheduler.scheduleAtFixedRate(this::incrementNumber, 0, 1, TimeUnit.SECONDS);
        numberTask = scheduler.scheduleAtFixedRate(this::number, 0, 1, TimeUnit.SECONDS);
        fizzTask = scheduler.scheduleAtFixedRate(this::fizz, 0, 1, TimeUnit.SECONDS);
        buzzTask = scheduler.scheduleAtFixedRate(this::buzz, 0, 1, TimeUnit.SECONDS);
        fizzbuzzTask = scheduler.scheduleAtFixedRate(this::fizzbuzz, 0, 1, TimeUnit.SECONDS);

    }

    private void incrementNumber() {
        number.getAndIncrement();
    }

    /**
     * выводит числа на экран и закрывает потоки при достижении *цели*
     */
    private void number() {
        boolean isLast = number.get() == aim;
        if(number.get() % 3 != 0 && number.get() % 5 != 0) {
            System.out.print(number + (isLast ? "" : ", "));
        }
        if (isLast) {
            cancelTasks();
        }
    }

    private void fizz() {
        boolean isLast = number.get() == aim;
         if(number.get() % 3 == 0 && number.get() % 5 != 0) {
            System.out.print("fizz" + (isLast ? "" : ", "));
        }
    }

    private void buzz() {
        boolean isLast = number.get() == aim;
        if(number.get() % 3 != 0 && number.get() % 5 == 0) {
            System.out.print("buzz" + (isLast ? "" : ", "));
        }
    }

    private void fizzbuzz() {
        boolean isLast = number.get() == aim;
        if(number.get() % 3 == 0 && number.get() % 5 == 0) {
            System.out.print("fizzbuzz" + (isLast ? "" : ", "));
        }
    }

    /**
     * метод который закрывает все потоки.
     * переменная boolean гарантирует, что они закроются после выполнения всех задач,
     * добавил её из-за проблемы вывода последнего элемента в консоль, когда поток завершал
     * работу программы раньше, чем выводился последний элемент
     */
    private void cancelTasks() {
        if (finished) {
            return;
        }
        incrementTask.cancel(false);
        numberTask.cancel(false);
        fizzTask.cancel(false);
        buzzTask.cancel(false);
        fizzbuzzTask.cancel(false);

        finished = true;

        scheduler.shutdown();
    }

}
