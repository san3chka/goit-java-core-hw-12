package solution.task1;

import java.time.Duration;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * второе решение в котором, как мне показалось, я не смог выполнить тз задачи, поэтому снова её переделывал
 */
public class Task1V2 {
    private Instant startTime;

    public void start() {
        startTime = Instant.now();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                long elapsedSeconds = Duration.between(startTime, Instant.now()).getSeconds();
                System.out.println("с момента запуска программы прошло: " + elapsedSeconds + ", поток:" + Thread.currentThread().getName());
            }
        }, 0, 1000);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            System.out.println("Прошло 5 секунд, поток:" + Thread.currentThread().getName());
        }, 5, 5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        new Task1V2().start();
    }
}

