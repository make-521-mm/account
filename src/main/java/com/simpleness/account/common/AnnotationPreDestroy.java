package com.simpleness.account.common;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * ---------------------------
 * If this comment is removed the program will blow up
 * ---------------------------
 *
 * @author make
 * @date 2021/11/16 17:24
 */
@Component
public class AnnotationPreDestroy {

    @PreDestroy
    public void destory() {
        System.out.println("退出程序");
    }
}
