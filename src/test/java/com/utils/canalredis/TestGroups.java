package com.utils.canalredis;

import com.utils.canalredis.entity.Group;
import com.utils.canalredis.mapper.GroupMapper;
import com.utils.canalredis.service.impl.GroupServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestGroups {
    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private GroupServiceImpl groupService;

    @Test
    public void testSelect(){
        List<Group> groupList = groupMapper.selectList(null);
        System.out.println("groupMapper get grplist size = "+groupList.size());
        groupList.forEach(System.out::println);
    }

    @Test
    public void TestR(){
        List<Group> groupList =  groupService.list();
        System.out.println("grouplist size="+groupList.size());
        Group group = groupList.get(500);
        System.out.println("500 item:"+group.toString());

        System.out.println(groupList.stream().findFirst().toString());
    }
}
