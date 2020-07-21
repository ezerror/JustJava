package me.ezerror.mutilthreading.D0.Ch04;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

@Slf4j(topic = "pac")
public class Test_ProducerAndConsumer {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(3);
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                queue.put("内容" + finalI);
            }, "producer" + i).start();
        }
        for (int j = 0; j < 5; j++) {
            int finalJ = j;
            new Thread(() -> {
                String take = queue.take();
                log.debug(finalJ + "取到了" + take);
            }, "consumer" + j).start();
        }

    }
}

@Slf4j(topic = "MessageQueue")
class MessageQueue {
    private LinkedList<String> messageQueue;
    private int capacity;

    MessageQueue(int capacity) {
        this.capacity = capacity;
        messageQueue = new LinkedList<>();
    }

    public String take() {
        synchronized (messageQueue) {
            while (messageQueue.isEmpty()) {
                log.debug("没货了, wait");
                try {
                    messageQueue.wait();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String message = messageQueue.removeFirst();
            messageQueue.notifyAll();
            return message;
        }
    }

    public void put(String msg) {
        synchronized (messageQueue) {
            while (messageQueue.size() == capacity) {
                log.debug("库存满了!");
                try {
                    messageQueue.wait();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            messageQueue.addLast(msg);
            messageQueue.notifyAll();
        }
    }

}