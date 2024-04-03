package bank.service.impl;

import bank.dao.AccountDao;
import bank.dao.impl.AccountDaoImpl;
import bank.exceptions.MoneyNotEnoughException;
import bank.exceptions.TransferException;
import bank.pojo.Account;
import bank.service.AccountService;
import bank.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.transaction.TransactionException;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao = new AccountDaoImpl();

    @Override
    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, TransferException {
        // 添加事务控制代码
        SqlSession sqlSession = SqlSessionUtil.openSession();

        // 1.判断转出账户的余额是否充足（select）
        Account fromAct = accountDao.selectByActno(fromActno);
        if (fromAct.getBalance() < money){
            // 2.如果转出账户余额不足，提示用户
            throw new MoneyNotEnoughException("转出账户余额不足");
        }
        // 3.如果转出账户余额充足，修改转出账户的余额(update)
        // 先更新内存中的java对象account的余额
        Account toAct = accountDao.selectByActno(toActno);
        fromAct.setBalance(fromAct.getBalance() - money);
        toAct.setBalance(toAct.getBalance() + money);
        // 再更新数据库中的java对象account的余额
        int count = accountDao.updateByActno(fromAct);

        // 4.更新转出账户的余额(update)
        count += accountDao.updateByActno(toAct);
        if (count != 2){
            throw new TransferException("转账异常,未知原因");
        }

        // 5.提交事务
        sqlSession.commit();

        // 6.关闭事务
        SqlSessionUtil.closeSession(sqlSession);
    }
}
