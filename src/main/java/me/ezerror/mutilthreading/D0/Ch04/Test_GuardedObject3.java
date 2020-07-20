package me.ezerror.mutilthreading.D0.Ch04;


import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "g2")
public class Test_GuardedObject3 {
    public static void main(String[] args) throws InterruptedException {
        // 收信人
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                GuardedObject guardedObject = MailBoxes.createGuardedObject();
                log.debug("等待");
                Object response = guardedObject.getResponse(5000);
                log.debug("收到回应 id:{}, 内容:{}", guardedObject.getId(), response);
            }, "收信人" + i).start();
        }
        TimeUnit.SECONDS.sleep(2);
        MailBoxes.getIds().forEach((id) -> {
            new Thread(() -> {
                GuardedObject guardedObject = MailBoxes.getGuardedObject(id);
                log.debug("送信 id:{}, 内容:{}", id, "回应" + id);
                guardedObject.setResponse("回应" + id);
            }, "邮递员" + id).start();
        });

    }
}

@Slf4j(topic = "GuardedObject")
class GuardedObject {
    private Integer id;
    private Object response;

    GuardedObject(Integer id) {
        this.id = id;
    }

    public synchronized Object getResponse(long mills) {
        // 1、 记录最初时间
        long begin = System.currentTimeMillis();
        // 2、 已经经历的时间
        long passTime = 0;
        while (response == null) {
            // 4、 假设 millis 是 1000，结果在 400 时唤醒了，那么还有 600 要等
            long waitTime = mills - passTime;
            if (waitTime <= 0) {
                log.debug("break!");
                break;
            }
            try {
                this.wait(waitTime);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            // 3、 如果提前被唤醒，这时已经经历的时间假设为 400
            passTime = System.currentTimeMillis() - begin;
//            log.debug("timePassed: {}, object is null {}",
//                    passTime, response == null);
        }
        return response;
    }

    public synchronized void setResponse(Object response) {
        this.response = response;
        this.notifyAll();
    }

    public Integer getId() {
        return id;
    }
}

class MailBoxes {
    private static Hashtable<Integer, GuardedObject> boxes = new Hashtable<>();

    static Integer id = 1;

    private static Integer generateId() {
        return id++;
    }

    public static GuardedObject getGuardedObject(int id) {
        return boxes.remove(id);
    }

    public static GuardedObject createGuardedObject() {
        GuardedObject go = new GuardedObject(generateId());
        boxes.put(go.getId(), go);
        return go;
    }

    public static Set<Integer> getIds() {
        return boxes.keySet();
    }
}
