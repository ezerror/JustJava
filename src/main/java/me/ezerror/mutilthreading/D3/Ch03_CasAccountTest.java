package me.ezerror.mutilthreading.D3;

import me.ezerror.mutilthreading.D3.i.IAccount;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不安全的，会出现不正确的结果
 */
public class Ch03_CasAccountTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            IAccount account = new CasAccount(10000);
            IAccount.demo(account);
        }
    }
}

class CasAccount implements IAccount {
    private final AtomicInteger balance = new AtomicInteger();

    CasAccount(Integer balance) {
        this.balance.set(balance);
    }

    @Override
    public Integer getBalance() {
        return this.balance.get();
    }

    @Override
    public void withdraw(Integer amount) {
        balance.getAndAdd(-amount);
    }
}
