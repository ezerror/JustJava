package me.ezerror.mutilthreading.D3;

import me.ezerror.mutilthreading.D3.i.IAccount;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不安全的，会出现不正确的结果
 */
public class Ch03_CasAccountTest {
    public static void main(String[] args) {
        IAccount account = new CasAccount(10000);
        IAccount.demo(account);
    }
}

class CasAccount implements IAccount {
    private AtomicInteger balance = new AtomicInteger();

    CasAccount(Integer balance) {
        this.balance.set(balance);
    }

    @Override
    public Integer getBalance() {
        return this.balance.get();
    }

    @Override
    public void withdraw(Integer amount) {
        while (true) {
            int now = balance.get();
            int after = now - amount;
            if (balance.compareAndSet(now, after)) {
                break;
            }
        }
    }
}
