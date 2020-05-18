package com.utils.canalredis;

import com.utils.canalredis.entity.UserRole;
import com.utils.canalredis.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestUsers {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testGetUserRoles(){
        List<UserRole> userRoleList = userService.getUserRoles("70234e42af9311e68659c598a67a71c6");
        System.out.println("userroles size="+userRoleList.size());
        System.out.println(userRoleList.stream().findFirst().toString());
    }

    @Test
    public void testGetUserDevRoles(){
        List<String> roleList = userService.getUserDevRoles("2c9486c071271ae901712ff3a54f379d");
        System.out.println("userdevroles size =" + roleList.size());
        roleList.forEach(System.out::println);

        //List<String> userdevRoles = userService.get
    }

    @Test
    public void  testGetUserDevRolesSql(){
        List<String> roleList = userService.getUserDevRolesSql("2c9486c071271ae901712ff3a54f379d");

        System.out.println("userdevrolesSql size =" + roleList.size());
        roleList.forEach(System.out::println);
    }

    @Test
    public void testGetUserGroups(){
        List<String> groupList = userService.getUserGroups("2c9486c071271ae901712ff3a54f379d");
        System.out.println("userGroups size="+groupList.size());
        groupList.forEach(System.out::println);
    }

}
