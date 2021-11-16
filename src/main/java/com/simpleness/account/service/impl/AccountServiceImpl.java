package com.simpleness.account.service.impl;

import com.simpleness.account.common.Constants;
import com.simpleness.account.common.PublicUtil;
import com.simpleness.account.entity.AccountEntity;
import com.simpleness.account.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ---------------------------
 * If this comment is removed the program will blow up
 * ---------------------------
 *
 * @author make
 * @date 2021/11/16 10:49
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private ApplicationContext applicationContext;

    private static Scanner scanner = new Scanner(System.in);

    private List<AccountEntity> accountEntityList = new ArrayList<AccountEntity>();
    long acountMoney = 0L;

    @Override
    public void manageAccount() {
        char input = '1';
        boolean notExitB = true;
        boolean selectErrorB = false;
        try {

            while(notExitB){
                // 没有选择错误时，才显示菜单
                if (!selectErrorB) {
                    showMenu();
                }

                // 还原默认值
                selectErrorB = false;

                // 接收用户输入项
                input = scanner.next().charAt(0);
                logger.info("选择了{}项", input);
                // 执行对应菜单
                switch (input) {
                    case '1':
                        showAccountDtl();
                        break;
                    case '2':
                        addAccount();
                        break;
                    case '3':
                        subtractAccount();
                        break;
                    case '4':
                        // 是否退出
                        notExitB = !exitMenu();
                        break;
                    default:
                        selectError();
                        selectErrorB = true;

                }

            }

            // 退出程序
//            System.exit(0);
            SpringApplication.exit(applicationContext);

        } catch (Exception e) {
            logger.error(PublicUtil.getStackTraceInfo(e));
        }

    }

    @Override
    public String showMenu() {
        StringBuffer showSb = new StringBuffer();
        showSb.append("********************* 简单记账 ************************\r\n")
        .append("*                  1.收支明细     \r\n")
        .append("*                  2.收入一笔     \r\n")
        .append("*                  3.支出一笔     \r\n")
        .append("*                  4.退    出     \r\n")
        .append("*                  请选择（1~ 4）:");

        System.out.print(showSb);
        return showSb.toString();
    }

    @Override
    public String showAccountDtl() {

        StringBuffer outSb = new StringBuffer();
        int index = 0;

        // 标题
        outSb.append("序号").append(Constants.ACCOUNT_SEPARATOR)
                .append("类别").append(Constants.ACCOUNT_SEPARATOR)
                .append("金额").append(Constants.ACCOUNT_SEPARATOR)
                .append("说明").append("\n");

        // 详细信息
        for (AccountEntity accountEntity : accountEntityList) {
            outSb.append(index).append(Constants.ACCOUNT_SEPARATOR)
                    .append(accountEntity.getLabel()).append(Constants.ACCOUNT_SEPARATOR)
                    .append(accountEntity.getAcountMoney()).append(Constants.ACCOUNT_SEPARATOR)
                    .append(accountEntity.getAccountMsg());
            outSb.append("\n");

            index++;
        }
        System.out.println(outSb.toString());
        return outSb.toString();
    }

    @Override
    public long addAccount() {
        AccountEntity accountEntity = new AccountEntity(Constants.ACCOUNT_LABEL_ADD);

        System.out.print("请输入收入的金额：");
        long money = inputMenoy();
        accountEntity.setAcountMoney(this.acountMoney + money);

        System.out.print("本次收入说明：");
        accountEntity.setAccountMsg(inputStr());

        accountEntityList.add(accountEntity);

        System.out.println("录入成功");

        return this.acountMoney;
    }

    @Override
    public long subtractAccount() {
        AccountEntity accountEntity = new AccountEntity(Constants.ACCOUNT_LABEL_SUBTRACT);

        System.out.print("请输入支出的金额：");
        long money = inputMenoy();
        accountEntity.setAcountMoney(this.acountMoney - money);

        System.out.print("本次收入说明：");
        accountEntity.setAccountMsg(inputStr());

        accountEntityList.add(accountEntity);

        System.out.println("录入成功");

        return this.acountMoney;
    }

    @Override
    public long inputMenoy() {
        long result = 0L;

        while (true) {
            try {
                String inputStr = scanner.next();
                result = Long.parseLong(inputStr);

                // 如果转换成功，则说明符合数值
                break;
            } catch (Exception e) {
                System.out.print("请输入正确的金额：");
                logger.error(PublicUtil.getStackTraceInfo(e));
            }

        }
        return result;
    }

    @Override
    public String inputStr() {
        return scanner.next();
    }

    @Override
    public boolean exitMenu() {
        boolean resultB = false;

        System.out.print("是否确认退出（Y/N）：");
        String inputStr = scanner.next();
        if (null != inputStr) {
            if ("Y".equals(inputStr.toUpperCase())) {
                resultB = true;
            }
        }

        return resultB;
    }

    @Override
    public void selectError() {
        System.out.print("选择有误，请重复选择（1~4）：");
    }
}
