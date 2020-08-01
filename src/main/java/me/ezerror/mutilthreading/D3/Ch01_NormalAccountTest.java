package me.ezerror.mutilthreading.D3;

import me.ezerror.mutilthreading.D3.i.IAccount;

/**
 * 不安全的，会出现不正确的结果
 */
public class Ch01_NormalAccountTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            IAccount account = new NormalAccount(10000);
            IAccount.demo(account);
        }
    }
}

class NormalAccount implements IAccount {
    private Integer balance;

    NormalAccount(Integer balance) {
        this.balance = balance;
    }

    @Override
    public Integer getBalance() {
        return this.balance;
    }

    @Override
    public void withdraw(Integer amount) {
        this.balance -= amount;
    }
}
