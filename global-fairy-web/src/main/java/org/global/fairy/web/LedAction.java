package org.global.fairy.web;


import com.alibaba.dubbo.common.json.JSONObject;
import org.global.fairy.modules.action.LedBlingForm;
import org.global.fairy.pi.service.ILedService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/led")
public class LedAction {

    @Resource
    private ILedService ledService;

    @RequestMapping(value = "/bling", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody()
    public String loginByPost2(@RequestBody LedBlingForm ledBlingForm) throws InterruptedException {
        String result = ledService.bling(ledBlingForm);
        return "";
    }

}
