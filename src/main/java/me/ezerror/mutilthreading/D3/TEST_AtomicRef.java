package me.ezerror.mutilthreading.D3;

import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

public class TEST_AtomicRef {
    public static void main(String[] args) {
        Person raw = new Person("sad");
        AtomicReference<Person> p = new AtomicReference<>(raw);
        Person newOne = new Person("张三");;
        p.compareAndSet(raw, newOne);
        System.out.println(p.get());

    }
}

@Data
class Person {
    private String name;

    Person(String name) {
        this.name = name;
    }

}
