package com.simpleness.account.controller;

import com.simpleness.account.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ---------------------------
 * If this comment is removed the program will blow up
 * ---------------------------
 *
 * @author make
 * @date 2021/11/16 11:18
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping("/showmenu")
    public String showMenu() {

        accountService.manageAccount();
        return "请在工作台操作";
    }


}
