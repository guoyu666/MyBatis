package bank.pojo;

import java.util.Objects;

/**
 * 账户类，封装账户数据
 * @author GuoYu
 * @version 1.0
 * @since 1.0
 */
public class Account {
    private Long id;
    private String name;
    private Double balance;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(name, account.name) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, balance);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Account(Long id, String name, Double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public Account() {
    }
}
