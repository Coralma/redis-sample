package com.cccis.oa.wechat.action;

import com.cccis.oa.wechat.model.CoralModel;
import com.cccis.oa.wechat.utils.JavaSerializeUtil;
import com.cccis.oa.wechat.utils.KryoSerializeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("/serialize")
public class RedisAction {

    Jedis jedis = new Jedis("192.168.200.80", 6390);

    @RequestMapping(value = "/javaInit", method = RequestMethod.GET)
    public @ResponseBody
    String javaSerialize(@RequestParam final Integer num) {
        CoralModel coralModel = createCoralModel(num);
        long s = System.currentTimeMillis();
        jedis.set(("Coral:"+num).getBytes(), JavaSerializeUtil.serialize(coralModel));
        return "Java init take time: " + (System.currentTimeMillis() - s);
    }

    @RequestMapping(value = "/javaGet", method = RequestMethod.GET)
    public @ResponseBody
    String javaGet(@RequestParam final Integer num) {
        long s = System.currentTimeMillis();
        CoralModel coralModel = (CoralModel)JavaSerializeUtil.unserialize(jedis.get(("Coral:"+num).getBytes()));
        return "Java get take time: " + (System.currentTimeMillis() - s);
    }

    @RequestMapping(value = "/kryoInit", method = RequestMethod.GET)
    public @ResponseBody
    String kryoSerialize(@RequestParam final Integer num) {
        CoralModel coralModel = createCoralModel(num);
        long s = System.currentTimeMillis();
        jedis.set(("CoralKryo:"+num).getBytes(), KryoSerializeUtil.serialize(coralModel));
        return "Kryo init take time: " + (System.currentTimeMillis() - s);
    }

    @RequestMapping(value = "/kryoGet", method = RequestMethod.GET)
    public @ResponseBody
    String kryoGet(@RequestParam final Integer num) {
        long s = System.currentTimeMillis();
        CoralModel coralModel = (CoralModel)KryoSerializeUtil.unserialize(jedis.get(("CoralKryo:"+num).getBytes()));
        return "Kryo get take time: " + (System.currentTimeMillis() - s);
    }

    private CoralModel createCoralModel(Integer num) {
        CoralModel coralModel = new CoralModel();
        coralModel.setPolicy("Policy_" + num);
        coralModel.setDesc("Desc_" + num);
        for(int i= num; i<100000;i++) {
            coralModel.addInsuredMap("key_" + i, "value_" + i);
        }
        return coralModel;
    }
}
