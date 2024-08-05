package bank.controller;

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
import java.io.PrintWriter;

@WebServlet("/transfer")
public class AccountController extends HttpServlet {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取响应流
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 获取账户信息
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        double money = Integer.parseInt(request.getParameter("amount"));
        // 调用业务方法完成转账
        try {
            accountService.transfer(fromActno, toActno, money);
        } catch (MoneyNotEnoughException e) {
            out.print(e.getMessage());
        } catch (TransferException e) {
            out.println(e.getMessage());
        }

    }
}
