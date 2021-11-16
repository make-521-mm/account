package com.simpleness.account.service;

public interface AccountService {

    public void manageAccount();

    /** 显示菜单 */
    public String showMenu();

    /** 显示账单明细 */
    public String showAccountDtl();

    /** 添加账单 */
    public long addAccount();

    /** 减账单 */
    public long subtractAccount();

    /** 获取输入的金额 */
    public long inputMenoy();

    /** 获取输入的说明 */
    public String inputStr();

    /** 退出 */
    public boolean exitMenu();

    /** 选择错误 */
    public void selectError();

}
