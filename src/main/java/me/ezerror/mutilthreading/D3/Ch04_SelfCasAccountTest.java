package me.ezerror.mutilthreading.D3;

import me.ezerror.mutilthreading.D3.i.IAccount;

import java.util.concurrent.atomic.AtomicInteger;

public class Ch04_SelfCasAccountTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            IAccount account = new SelfCasAccount(10000);
            IAccount.demo(account);
        }
    }
}


class SelfCasAccount implements IAccount {
    private final AtomicData balance;

    SelfCasAccount(Integer balance) {
        this.balance = new AtomicData(balance);
    }

    @Override
    public Integer getBalance() {
        return this.balance.getData();
    }

    @Override
    public void withdraw(Integer amount) {
        balance.decrease(amount);
    }
}
