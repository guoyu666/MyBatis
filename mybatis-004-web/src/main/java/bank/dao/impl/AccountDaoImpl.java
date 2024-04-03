package bank.dao.impl;

import bank.dao.AccountDao;
import bank.pojo.Account;
import bank.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class AccountDaoImpl implements AccountDao {
    @Override
    public Account selectByActno(String actno) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Account account = (Account) sqlSession.selectOne("account.selectByActno", actno);
        return account;
    }

    @Override
    public int updateByActno(Account act) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        int count = sqlSession.update("account.updateByActno", act);
        sqlSession.commit();
        return count;
    }
}
