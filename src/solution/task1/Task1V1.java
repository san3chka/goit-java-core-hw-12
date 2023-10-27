package solution.task1;

import java.time.Duration;
import java.time.Instant;

/**
 * первое неудочное решение данной задачи, которое бесконечно пересоздавало потоки
 **/
public class Task1V1 {
        private long time;

        public void start() {
            Thread thread1 = new Thread(() -> {
                Instant start = Instant.now();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Instant finish = Instant.now();
                time += Duration.between(start, finish).toSeconds();
                System.out.println("прошло времени с начала сессии: " + time + " " + Thread.currentThread().getName());
                checkTime();
            });
            thread1.start();
        }

        private void checkTime() {
            Thread thread = new Thread(() -> {
                if (time % 5 == 0)
                    System.out.println("Прошло 5 секунд " + " " + Thread.currentThread().getName());
            });
            start();
            thread.start();
        }

    public static void main(String[] args) {
        new Task1V1().start();
    }
}
