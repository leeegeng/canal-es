package com.utils.canalredis.controller;

import com.utils.canalredis.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;
    @Autowired
    //private RedisTemplate<String, Serializable> testRedisTemplate;
    private RedisTemplate<String, Object> serializableRedisTemplate;

    @Autowired
    private RedisUtils redisOpe;

//    @Autowired
//    private UserService userService;

    @RequestMapping(value = "/hello")
    public String index() {
        return "this is website!";
    }


    @GetMapping("/redis")
    public String redis(){

        //字符串
        stringRedisTemplate.opsForValue().set("rediskey","redisvalue");
        String rediskey = stringRedisTemplate.opsForValue().get("rediskey");
        System.out.println(rediskey);

        return rediskey;
    }

    @GetMapping("/ser")
    public String testSerializable() {
//        User user=new User();
//        user.setUserId(22);
//        user.setUserName("test");
//        user.setPassword("test123");
//        user.setPhone("13524442222");
//        userService.addUser(user);
//
//        serializableRedisTemplate.opsForValue().set("user", user);
//        User user2 = (User) serializableRedisTemplate.opsForValue().get("user");
//        String strInfo = "user:"+user2.getUserId()+","+user2.getUserName();
//        System.out.println(strInfo);
//
//        redisOpe.set("hello", "world");
//        redisOpe.hset("deviceInfo", "id", "32000000000");
//        Map<String, Object> mapGrpInfo = new HashMap<String, Object>();
//        mapGrpInfo.put("grp1", "sh1");
//        mapGrpInfo.put("grp2", "sh2");
//
//        System.out.println("mapinfo：" + mapGrpInfo.toString());
//        redisOpe.hmset("groupInfo", mapGrpInfo);

        return "hello";
    }
}

