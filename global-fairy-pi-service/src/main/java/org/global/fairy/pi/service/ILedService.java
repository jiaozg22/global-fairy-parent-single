package org.global.fairy.pi.service;


import org.global.fairy.modules.action.LedBlingForm;

/**
 * led灯显示控制
 * @author jiao_zg22@163.com
 * @date 2020-3-15
 */
public interface ILedService {
    String bling(LedBlingForm ledBlingForm) throws InterruptedException;
}
