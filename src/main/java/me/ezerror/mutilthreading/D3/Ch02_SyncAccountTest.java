package me.ezerror.mutilthreading.D3;

import me.ezerror.mutilthreading.D3.i.IAccount;

/**
 * 不安全的，会出现不正确的结果
 */
public class Ch02_SyncAccountTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            IAccount account = new SyncAccount(10000);
            IAccount.demo(account);
        }
    }
}

class SyncAccount implements IAccount {
    private Integer balance;

    SyncAccount(Integer balance) {
        this.balance = balance;
    }

    @Override
    public synchronized Integer getBalance() {
        return this.balance;
    }

    @Override
    public synchronized void withdraw(Integer amount) {
        this.balance -= amount;
    }
}
