package com.utils.canalredis;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.exception.CanalClientException;
import com.utils.canalredis.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestCanal {

   @Autowired
   public RedisUtils redisUtils;

   @Test
    public void testR() {
        // 创建链接
       CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("172.16.64.85", 11111),"example", "canal", "Kedacom@123");
       //CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("172.16.64.85", 11111),"example", "canal", "S2VkYWNvbUAxMjM=");

        //CanalConnector connector = CanalConnectors.newClusterConnector("10.10.2.137:2181,10.10.2.138:2181,10.10.2.139:2181", "example", null, null);
        int batchSize = 5;
        int emptyCount = 0;
        try {
            connector.connect();
            connector.subscribe(".*\\..*");
            //connector.subscribe("test.student");
            //connector.subscribe("ioms.device_channel");
            connector.rollback();
            int totalEmtryCount = 100000;
            while (emptyCount < totalEmtryCount) {
                try {
                    Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                    long batchId = message.getId();
                    int size = message.getEntries().size();
                    if (batchId == -1 || size == 0) {
                        emptyCount++;
                        //System.out.println("empty count : " + emptyCount);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        emptyCount = 0;
                        System.out.printf("message[batchId=%s,size=%s] \n", batchId, size);
                        printEntry(message.getEntries());
                    }

                    connector.ack(batchId); // 提交确认
                    // connector.rollback(batchId); // 处理失败, 回滚数据
                }catch (CanalClientException e){
                    System.out.println(e.toString());
                }

            }

            System.out.println("empty too many times, exit");
        } finally {
            connector.disconnect();
        }
    }

    private void printEntry(List<CanalEntry.Entry> entrys) {
        for (CanalEntry.Entry entry : entrys) {
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN
                    || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                //System.out.println("transtaction!");
                continue;
            }

            RowChange rowChage;
            try {
                rowChage = RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }

            CanalEntry.EventType eventType = rowChage.getEventType();
            System.out.println(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(), eventType));

            for (RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == EventType.DELETE) {
                    printColumn(rowData.getBeforeColumnsList(), eventType);
                } else if (eventType == CanalEntry.EventType.INSERT) {
                    printColumn(rowData.getAfterColumnsList(), eventType);
                } else {
                    System.out.println("-------> before");
                    printColumn(rowData.getBeforeColumnsList(), eventType);
                    System.out.println("-------> after");
                    printColumn(rowData.getAfterColumnsList(), eventType);
                }
            }
        }
    }

    private void printColumn(List<Column> columns, CanalEntry.EventType eventType) {
        Map<String, Object> mapUsers = new HashMap<String, Object>();
        String strKey = "persons:";//new String();
        for (Column column : columns) {
            System.out.println("=======>" + column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());

            if(eventType == EventType.INSERT){
                mapUsers.put(column.getName(), column.getValue());
//                if(column.getName().equals("name")){
//                    strKey = column.getValue();
//                }
                if(column.getName().equals("id")){
                    strKey += column.getValue();
                }
            }
        }

//        Map<String, Object> mapGrpInfo = new HashMap<String, Object>();
//        mapGrpInfo.put("grp1", "sh1");
//        mapGrpInfo.put("grp2", "sh2");

//        System.out.println("hash key: " + strKey);
//        System.out.println(mapUsers.toString());
//        redisUtils.hmset(strKey, mapUsers);
//
//        Map<Object, Object>  newData = redisUtils.hmget(strKey);
//        System.out.println(newData.toString());
    }
}

