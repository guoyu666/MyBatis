package bank.web;

import bank.exceptions.MoneyNotEnoughException;
import bank.exceptions.TransferException;
import bank.service.AccountService;
import bank.service.impl.AccountServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet({"/transfer"})
public class AccountServlet extends HttpServlet {

    // 为了让这个对象在其他方法中也可以用，声明为实例变量
    private AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取表单数据
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        double money = Double.parseDouble(request.getParameter("money"));
        try {
            // 调用service的转账方法安成转账（调业务层）
            accountService.transfer(fromActno, toActno, money);
            // 程序能够走到这里，表示转账一定成功了
            // 调用View视图展示结果
            response.sendRedirect(request.getContextPath() + "/success.html");
        } catch (MoneyNotEnoughException e) {
            response.sendRedirect(request.getContextPath() + "/error1.html");
        } catch (TransferException e) {
            response.sendRedirect(request.getContextPath() + "/error2.html");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/error2.html");
        }

    }
}
