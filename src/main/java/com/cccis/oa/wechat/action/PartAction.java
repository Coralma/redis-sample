package com.cccis.oa.wechat.action;

import java.util.List;

import com.cccis.oa.wechat.service.PerformanceTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cccis.oa.wechat.model.PartModel;

/**
 * Created by CCC on 2016/10/25.
 */
@Controller
@RequestMapping("/part")
public class PartAction {

    @Autowired
    private PerformanceTestService service;
    private static List<PartModel> partModels;
    private static String key = "29";

    public PartAction() {
    }

    @RequestMapping(value = "/apply", method = RequestMethod.GET)
    public @ResponseBody
    String apply(@RequestParam final Integer number) {
        long s = System.currentTimeMillis();
        service.takeTimeFive();
        long t = System.currentTimeMillis() - s;
        return "Decrypt total take time " + t + "(ms)";
    }

}