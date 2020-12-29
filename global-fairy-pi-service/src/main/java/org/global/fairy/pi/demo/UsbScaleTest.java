package org.global.fairy.pi.demo;


import com.sun.istack.internal.logging.Logger;


import javax.usb.*;
import javax.usb.event.UsbPipeDataEvent;
import javax.usb.event.UsbPipeErrorEvent;
import javax.usb.event.UsbPipeListener;
import java.util.List;
import java.util.logging.Level;

/**
 * 测试usb
 */
public class UsbScaleTest implements UsbPipeListener {
    //usb 设备
    private static UsbDevice device ;
    //usb设备内核控制接口
    private static UsbInterface iface;
    //usb数据传输通道
    private static UsbPipe pipe;
    //一次传输的数据（6位字节数组）
    private byte[] data;

    public UsbScaleTest(UsbDevice device){
        this.device = device;
    }


    public static void main(String[] args) throws UsbException{
        UsbScaleTest scale = UsbScaleTest.findScale();
        scale.open();
        try {
            for (int i = 0 ;i<60;i++){
                scale.syncSubmit();
            }
        }finally {
            scale.close();
        }

    }

    public static UsbScaleTest findScale() throws UsbException{
        UsbServices services = UsbHostManager.getUsbServices();
        UsbHub rootHub = services.getRootUsbHub();
        //Dymo M10电子秤
        UsbDevice device = findDevice(rootHub,(short) 0x0922,(short) 0x800);
        //Dymo M25电子秤
        if(device == null){
            device = findDevice(rootHub,(short) 0x0922,(short) 0x8004);
        }
        if(device == null){
            return null;
        }
        return new UsbScaleTest(device);

    }

    /**
     * 定位设备
     * @param hub
     * @param vendorId
     * @param productId
     * @return
     */
    private static UsbDevice findDevice(UsbHub hub,short vendorId,short productId){
        for(UsbDevice device:(List<UsbDevice>) hub.getAttachedUsbDevices()){
            UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
            if(desc.idVendor() == vendorId && desc.idProduct() == productId){
                return device;
            }
            if(device.isUsbHub()){
                device = findDevice((UsbHub) device,vendorId,productId);
                if (device != null){
                    return device;
                }
            }

        }
        return null;
    }

    private void open() throws UsbException{
        UsbConfiguration configuration = device.getActiveUsbConfiguration();
        iface = configuration.getUsbInterface((byte) 0);
        //这样可以从内核中获取接口控制权
        iface.claim(usbInterface -> true);//claim方法，强制内核释放对usb接口的控制
        final List<UsbEndpoint> endpoints = iface.getUsbEndpoints();
        pipe = endpoints.get(0).getUsbPipe();//只有一个节点
        pipe.addUsbPipeListener(this);
        pipe.open();
    }

    @Override
    public void errorEventOccurred(UsbPipeErrorEvent usbPipeErrorEvent) {
        //出现错误记日志
        Logger.getLogger(UsbScaleTest.class).log(Level.SEVERE,"Scale ERROR",usbPipeErrorEvent);
    }

    //回调函数
    @Override
    public void dataEventOccurred(UsbPipeDataEvent usbPipeDataEvent) {
        boolean empty = data[1] == 2;
        boolean overweight = data[1] == 6;
        boolean negative = data[1] == 5;
        boolean grams = data[2] == 2;

        int scalingFactor = data[3];
        int weight = (data[4] & 0xFF + (data[5] << 8));
        if(empty){
            System.out.println("重量为空");
        }else if (overweight){
            System.out.println("超重");
        }else if(negative){
            System.out.println("重量为负");//？什么情况出现
        }else {
            //由于printf 在远程执行时会产生问题，因此使用String.format
            System.out.println(String.format("重量=%,.1f%s",scaleWeight(weight,scalingFactor),grams ? "g":"oz"));
        }

    }

    //将重量比返回参数进行比例调整
    private Object scaleWeight(int weight, int scalingFactor) {
        return weight * Math.pow(10, scalingFactor);
    }

    //计重器发送的数据都是6个值的简单字节数组（6位字节的数组）
    //6位字节含义如下：
    // 字节0 ： 未使用
    // 字节1 ：特殊标志：空 = 2，超重 = 6，负 = 5
    // 字节2： 度量单位：克=2 ，盎司 = 11
    // 字节3： 重量比
    // 字节4   基本重量的低位字节（一个字节8位）
    // 字节5： 基本重量的高位字节（）两个字节共16位，可以支持的最大重量：2^16次方=32kg 或者32盎司，无千克
    private void syncSubmit() throws UsbException{
        pipe.syncSubmit(data);
    }

    public void close() throws UsbException{
        pipe.close();
        iface.release();
    }
}

