package com.utils.canalredis;

import com.utils.canalredis.entity.Group;
import com.utils.canalredis.service.impl.TreeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestTreeGroups {
    @Autowired
    private TreeServiceImpl treeService;

    @Test
    public void testGetChildren(){
        List<Group> groupList = treeService.getChildren("defaulttreeid0000000000000000000", "defaulttreerootgroupid0000000000");
        System.out.println("groupsize:"+groupList.size());
        groupList.forEach(System.out::println);
    }

    @Test
    public void testGetDescendantsAndMe(){
        List<Group> groupList = treeService.getDescendantsAndMe("defaulttreeid0000000000000000000", "defaulttreerootgroupid0000000000");
        System.out.println("DesendantAndMe size="+groupList.size());
        //System.out.println(groupList.toString());
        groupList.forEach(System.out::println);
    }
}
