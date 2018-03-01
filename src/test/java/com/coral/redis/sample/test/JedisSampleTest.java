package com.coral.redis.sample.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.List;

public class JedisSampleTest {

    Jedis jedis = new Jedis("192.168.200.80", 6390);

    @Test
    public void redisObj() {
        // write
        Person person = new Person(100, "alan");
        jedis.set("person:100".getBytes(), SerializeUtil.serialize(person));
        person = new Person(101, "bruce");
        jedis.set("person:101".getBytes(), SerializeUtil.serialize(person));
        // read
        Person p = (Person) SerializeUtil.unserialize(jedis.get(("person:100").getBytes()));
        System.out.println("Id: " +p.getId() + " , name: " + p.getName());
    }

    @Test
    public void redisList() {
        //存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }
    }

    @Test
    public void redisString() {
        //设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
    }

    @Test
    public void hello() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.200.80", 6390);
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
    }

}
