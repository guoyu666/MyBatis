package bank.service;

import bank.exceptions.MoneyNotEnoughException;
import bank.exceptions.TransferException;

/**
 * 注意：业务类当中的业务方法的名字起名的时候，最好见名知意，能够体现出具体的业务是做什么
 * 账户业务类
 * @author GuoYu
 * @version 1.0
 * @since 1.0
 */
public interface AccountService {
    /**
     * 账户转账业务
     * @param fromActno
     * @param toActno
     * @param money
     */
    void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, TransferException;

}
