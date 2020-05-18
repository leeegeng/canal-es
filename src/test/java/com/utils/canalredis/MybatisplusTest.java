package com.utils.canalredis;

import com.utils.canalredis.entity.User;
import com.utils.canalredis.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisplusTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        //Assert.assertEquals(4, userList.size());
        System.out.println(userList.size());
        userList.forEach(System.out::println);
    }

//    @Test
//    public void aInsert() {
//        User user = new User();
//        user.setName("small");
//        user.setAge(3);
//        user.setEmail("abc@mp.com");
//        assertThat(userMapper.insert(user)).isGreaterThan(0);
//        // 成功直接拿会写的 ID
//        assertThat(user.getId()).isNotNull();
//    }

}
