package com.utils.canalredis.controller;


import com.alibaba.fastjson.JSONObject;
import com.utils.canalredis.entity.Group;
import com.utils.canalredis.entity.UserRole;
import com.utils.canalredis.service.impl.TreeServiceImpl;
import com.utils.canalredis.service.impl.UserServiceImpl;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-05-09
 */
@RestController
@RequestMapping("/dc")
public class DeviceChannelController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TreeServiceImpl treeService;

    @Autowired
    private RestHighLevelClient client;

    private static String INDEX_TEST = "devicechannel";
    private static String TYPE_TEST = "doc";

    private List<String> treeGroupsList;    //  选中树分组和所有子分组列表
    private List<String> userGroupList;     //  用户拥有角色的所有分组列表
    private List<String> userrolesList;     //  用户拥有设备的角色列表

    private long spanTime;

    private void getQueryList(String treeId, String groupId, String userId){
        long begin = System.currentTimeMillis();
        // 选中分组和所有子分组
        treeGroupsList = treeService.getDescendantsAndMe(treeId, groupId).stream().map(Group::getId)
                .collect(Collectors.toList());
        userGroupList = userService.getUserGroupsDescendant(userId);
        //userrolesList = userService.getUserDevRolesSql(userId);
        userrolesList = userService.getUserRoles(userId).stream().map(UserRole::getRoleId).collect(Collectors.toList());

        spanTime = System.currentTimeMillis() - begin;
    }

    @PostMapping("/statistic")
    public String statistic(@RequestBody JSONObject jsonObject) throws IOException {
        String treeId = jsonObject.getString("treeId");
        String groupId = jsonObject.getString("groupId");
        String userId = jsonObject.getString("userId");

        getQueryList(treeId, groupId, userId);
//        // 选中分组和所有子分组
//        List<String> treeGroupsList = treeService.getDescendantsAndMe(treeId, groupId).stream().map(Group::getId)
//                                                .collect(Collectors.toList());
//
//        // 用户拥有角色的所有设备分组
//        List<String> userGroupList = userService.getUserGroupsDescendant(userId);
//        // 用户拥有设备的角色
//        List<String> userrolesList = userService.getUserDevRolesSql(userId);

        // 到es聚合
//        QueryBuilder queryBuilder_usergrs = QueryBuilders.termsQuery("group_ids.keyword", userGroupList);
//        QueryBuilder queryBuilder_userroles = QueryBuilders.termsQuery("role_ids.keyword", userrolesList);

//        QueryBuilder queryBuilder_treegrps = QueryBuilders.termsQuery("group_ids.keyword", treeGroupsList);

//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        boolQueryBuilder.should(queryBuilder_usergrs);
//        boolQueryBuilder.should(queryBuilder_userroles);

        // 按用户拥有 （角色分组列表or角色列表）and 选中分组列表 and dev1chn2=1 进行设备刷选
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .filter(QueryBuilders.boolQuery().should(QueryBuilders.termsQuery("group_ids.keyword", userGroupList)).
                        should(QueryBuilders.termsQuery("role_ids.keyword", userrolesList)))
                .filter(QueryBuilders.termsQuery("group_ids.keyword", treeGroupsList))
                .filter(QueryBuilders.termQuery("dev1chn2", 1));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(0).query(queryBuilder)
                .aggregation(AggregationBuilders.terms("device_type")
                        .field("device_type.keyword")
                        .subAggregation(AggregationBuilders.sum("sum_status").field("status"))
                        .subAggregation(AggregationBuilders.sum("sum_alarm_status").field("alarm_status"))
                );// 聚合在线设备个数和告警数

        SearchRequest searchRequest = new SearchRequest("devicechannel");
        searchRequest.types("doc");
        searchRequest.source(searchSourceBuilder);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        TimeValue timeValue = response.getTook();
        boolean timeOut = response.isTimedOut();

        SearchHits hits = response.getHits();
        System.out.println("took:"+timeValue+"[timeOut]:"+timeOut+"[count]:"+hits.getTotalHits());
        hits.forEach(System.out::println);


        return "[spanTime]"+spanTime+"\n" + response.toString();
    }

    @PostMapping("/search")
    public String seaarch(@RequestBody JSONObject jsonObject) throws IOException {
        String treeId = jsonObject.getString("treeId");
        String groupId = jsonObject.getString("groupId");
        String userId = jsonObject.getString("userId");

        String status = jsonObject.getString("status");
        String alarmStatus = jsonObject.getString("alarmStatus");
        String devName = jsonObject.getString("devName");
        String ip = jsonObject.getString("ip");
        String manufacture = jsonObject.getString("manufacture");

        getQueryList(treeId, groupId, userId);

        // 按用户拥有 （角色分组列表or角色列表）and 选中分组列表  进行设备刷选
//        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
//                .filter(QueryBuilders.boolQuery().should(QueryBuilders.termsQuery("group_ids.keyword", userGroupList)).
//                        should(QueryBuilders.termsQuery("role_ids.keyword", userrolesList)))
//                .filter(QueryBuilders.termsQuery("group_ids.keyword", treeGroupsList))
//                .filter(QueryBuilders.termQuery("dev1chn2", 1))
//                .filter(QueryBuilders.matchPhrasePrefixQuery("name", devName))
//                .filter(QueryBuilders.termQuery("status", status));

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.filter(QueryBuilders.boolQuery().should(QueryBuilders.termsQuery("group_ids.keyword", userGroupList)).
                should(QueryBuilders.termsQuery("role_ids.keyword", userrolesList)))
                .filter(QueryBuilders.termsQuery("group_ids.keyword", treeGroupsList))
                .filter(QueryBuilders.termQuery("dev1chn2", 1));
        if(status != null){
            boolQuery.filter(QueryBuilders.termQuery("status", status));
        }
        if(alarmStatus != null){
            boolQuery.filter(QueryBuilders.termQuery("alarm_status", alarmStatus));
        }
        if(devName != null && !devName.isEmpty()){
            boolQuery.filter(QueryBuilders.matchPhrasePrefixQuery("name", devName));
        }
        if(ip != null && !ip.isEmpty()){
            boolQuery.filter(QueryBuilders.matchPhrasePrefixQuery("ipv4", ip));
        }
        if(manufacture != null && !manufacture.isEmpty()){
            boolQuery.filter(QueryBuilders.matchPhrasePrefixQuery("manufacturer", manufacture));
        }

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQuery);//.query(QueryBuilders.termQuery("status", status));

        SearchRequest searchRequest = new SearchRequest("devicechannel");
        searchRequest.types("doc");
        searchRequest.source(searchSourceBuilder);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        return "[spanTime]"+spanTime+"\n" + response.toString();
    }

}
