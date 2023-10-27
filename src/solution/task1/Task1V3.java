package solution.task1;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


/**
 * конечная форма моего решения этой задачи, оно больше всего приближено к тз
 */
public class Task1V3 {
    private AtomicLong time = new AtomicLong(0);
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

    public void start() {
        scheduler.scheduleAtFixedRate(this::incrementTime, 0, 1, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(this::sessionTime, 0, 1, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(this::checkTimeEqualsFive, 0, 1, TimeUnit.SECONDS);

    }

    private void incrementTime() {
        time.getAndIncrement();
    }

    private void checkTimeEqualsFive() {
        if (time.get() % 5 == 0) {
            System.out.println("Прошло 5 секунд" + ", поток:" + Thread.currentThread().getName());
        }
    }

    private void sessionTime() {
        System.out.println("прошло времени с начала сессии: " + time.get() + " секунд" + ", поток:" + Thread.currentThread().getName());
    }
}
