package pkg;

import java.util.*;
import java.util.concurrent.LinkedTransferQueue;

public class CarQueue {
    private Queue<Integer> queue = new LinkedTransferQueue<>();
    private Random direction;

    CarQueue() {
        direction = new Random();
        for (int i = 0; i < 10; ++i)
            queue.add(direction.nextInt(4));
    }

    public void addToQueue() {
        class QueueRunner implements Runnable {
            @Override
            public void run() {
                try {

                    for (int i = 0; i < 3; ++i) {
                        queue.add(direction.nextInt(4));
                        System.out.println(queue.size());
                        Thread.sleep(500);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Runnable runner = new QueueRunner();
        Thread thrd = new Thread(runner);
        thrd.start();
    }



    public int deleteQueue() {
        if (queue.size() == 1)
            addToQueue();
        try {
            return queue.remove();
        } catch (NoSuchElementException e) {

            System.err.println(queue.size());
            new Scanner(System.in).next();
        }
        return 0;
    }
}
