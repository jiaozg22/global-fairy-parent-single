package org.global.fairy.pi.utils;

import com.pi4j.io.gpio.*;

/**
 * jiao_zg22@163.com
 */
public class GpioUtil {
    private static final GpioController gpioController = GpioFactory.getInstance();

    private static final GpioPinDigitalOutput gpioPinDigitalOutput =gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_01,"LEDBling", PinState.HIGH);

    public static GpioController getGpioController()
    {
        return gpioController;
    }

    public static GpioPinDigitalOutput getGpioPinDigitalOutput(){
        return gpioPinDigitalOutput;
    }
}
