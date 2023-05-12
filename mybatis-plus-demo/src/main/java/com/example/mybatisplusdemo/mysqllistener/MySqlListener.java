/*
package com.example.mybatisplusdemo.mysqllistener;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

*/
/**
 * @author xiaobo
 * @description
 * @date 2023/3/29 09:54
 *//*

@Component
public class MySqlListener {

    private static String host = "localhost";
    private static int port = 3306;
    private static String username = "root";
    private static String password = "123456";

    @PostConstruct
    public void listener() {
        BinaryLogClient client = new BinaryLogClient(host, port, username, password);
        //设置需要读取的Binlog的文件读以及位置，否则，client会从"头"开始取Binlog并监听
//        String path = "D:\\mysql-5.7.16-winx64\\data\\mysql-bin.000001";
//        client.setBinlogFilename(path);
        */
/*client.setBinlogPosition(1);*//*

        client.setServerId(1);
        //给客户端注册监听器，实现对Binlog的监听和解析
        //event 就是监听到的Binlog变化信息，event包含header & data 两部分
        client.registerEventListener(event -> {
            EventData data = event.getData();
            //修改监听事件
            if (data instanceof UpdateRowsEventData) {
                System.out.println("--------Update-----------");
                UpdateRowsEventData eventData = (UpdateRowsEventData) data;
                //新增监听事件
            } else if (data instanceof WriteRowsEventData) {
                System.out.println("--------Insert-----------");
                WriteRowsEventData eventData = (WriteRowsEventData) data;
                List<Serializable[]> rows = eventData.getRows();
                //删除的监听事件
            } else if (data instanceof DeleteRowsEventData) {
                System.out.println("--------Delete-----------");
                DeleteRowsEventData eventData = (DeleteRowsEventData) data;
                List<Serializable[]> rows = eventData.getRows();
            }
        });
        try {
            client.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
*/
