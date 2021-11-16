package com.simpleness.account.entity;

/**
 * ---------------------------
 * If this comment is removed the program will blow up
 * ---------------------------
 *
 * @author make
 * @date 2021/11/16 18:21
 */
public class AccountEntity {

    private String label;
    private Long acountMoney;
    private Long totalMoney;
    private String accountMsg;

    public AccountEntity() {

    }

    public AccountEntity(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getAcountMoney() {
        return acountMoney;
    }

    public void setAcountMoney(Long acountMoney) {
        this.acountMoney = acountMoney;
    }

    public Long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getAccountMsg() {
        return accountMsg;
    }

    public void setAccountMsg(String accountMsg) {
        this.accountMsg = accountMsg;
    }
}
