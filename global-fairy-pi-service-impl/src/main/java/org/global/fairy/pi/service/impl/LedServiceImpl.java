package org.global.fairy.pi.service.impl;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.wiringpi.Gpio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.global.fairy.modules.action.LedBlingForm;

import org.global.fairy.pi.service.ILedService;
import org.springframework.stereotype.Service;


/**
 * jiao_zg22@163.com
 * 2020-03-15
 */
@Service
public class LedServiceImpl implements ILedService {
    private static final Logger logger = LogManager.getLogger(LedServiceImpl.class);

    @Override
    public String bling(LedBlingForm ledBlingForm) throws InterruptedException {
        logger.info("LedServiceImpl::bling::开始设置led灯");
        GpioPinDigitalOutput gpioPinDigitalOutput = null;
//        Future<V> future = gpioPinDigitalOutput.blink(100);
        gpioPinDigitalOutput.setShutdownOptions(true, PinState.LOW);
        Thread.sleep(5000);

        gpioPinDigitalOutput.low();
        Thread.sleep(5000);
        for(int i = 0 ;i <=ledBlingForm.getTime() ;i++){
            Thread.sleep(1000);

            // turn off gpio pin #01
            gpioPinDigitalOutput.high();
            Thread.sleep(1000);
            gpioPinDigitalOutput.low();
            logger.info("LedServiceImpl::bling::循环闪烁{}次",i);
        }

        logger.info("LedServiceImpl::bling::设置led灯结束");
        return "true";
    }
}
