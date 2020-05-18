package com.utils.canalredis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.utils.canalredis.entity.DeviceChannel;
import com.utils.canalredis.service.impl.DeviceChannelServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DevchnTest {
    @Autowired
    private DeviceChannelServiceImpl devchnServiceImpl;

//    @Autowired
//    private DeviceChannelMapper deviceChannelMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<DeviceChannel> userList = devchnServiceImpl.list(new QueryWrapper<DeviceChannel>().eq("device_id", "iomsmasteragent00000000000000000"));
        //Assert.assertEquals(4, userList.size());
        System.out.println(userList.size());
        userList.forEach(System.out::println);
    }
}
