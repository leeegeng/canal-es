package com.utils.canalredis;

import com.utils.canalredis.bean.EsDevicechannel;
import com.utils.canalredis.bean.Tests;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestDevicechannelEs {
    @Autowired
    private RestHighLevelClient client;

    public static String INDEX_TEST = null;
    public static String TYPE_TEST = null;
    public static Tests tests = null;
    public static List<EsDevicechannel> testsList = null;

    @BeforeClass
    public static void before() {
        INDEX_TEST = "devicechannel";
        TYPE_TEST = "doc";
//        testsList = new ArrayList<>();
    }

    @Test
    public void testGetOne() throws IOException {
        GetRequest getRequest = new GetRequest("devicechannel", "doc", "iomsmasteragent00000000000000000");
        GetResponse getResponse = null;
        try {
            getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        }catch (ElasticsearchException e){
            if(e.status() == RestStatus.NOT_FOUND){
                System.out.println("not found index");
            }
            if(e.status() == RestStatus.CONFLICT){
                System.out.println("version conflict!");
            }
            System.out.println("get exception!");
        }
        if(getResponse != null) {
            System.out.println("[toString:]" + getResponse.toString());
            // 获取的结果在source里，通过getField获取不到数据
            System.out.println("[id]:" + getResponse.getId() + "[index]" + getResponse.getIndex()
                    + "[device_id]" + getResponse.getField("device_id")
                    + "[custom_attr]" + getResponse.getField("custom_attr")
                    + "[custom_attr.deviceTypeName]" + getResponse.getField("custom_attr.deviceTypeName"));
            String sourceAsString = getResponse.getSourceAsString(); //结果取成 String
            System.out.println(sourceAsString);
            Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();  // 结果取成Map
            System.out.println(sourceAsMap.toString());
        }
    }

    @Test
    public  void testBool() throws IOException {
        List<String> groupList = new ArrayList<>();
        groupList.add("defaulttreerootgroupid0000000000");

        QueryBuilder queryBuilder= QueryBuilders.termsQuery("group_ids.keyword", groupList);
        //QueryBuilder queryBuilder_name = QueryBuilders.matchQuery("name", "运维");

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(queryBuilder);
        //boolQueryBuilder.filter(queryBuilder_name);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(boolQueryBuilder);

        SearchRequest searchRequest = new SearchRequest("devicechannel");
        searchRequest.types("doc");
        searchRequest.source(searchSourceBuilder);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        TimeValue timeValue = response.getTook();
        boolean timeOut = response.isTimedOut();

        SearchHits hits = response.getHits();
        System.out.println("took:"+timeValue+"[timeOut]:"+timeOut+"[count]:"+hits.getTotalHits());
        hits.forEach(System.out::println);
    }
}
