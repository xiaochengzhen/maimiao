package com.example.craw.controller;
/**
 * @description 
 * @author xiaobo
 * @date 2024/1/11 18:31
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.craw.http.TestTraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Test {
    public static void main(String[] args) {
        // 原始的平行结构JSON字符串
        String jsonString = "{\n" +
                "  \"id\": 1000,\n" +
                "  \"value\": \"main.menu\",\n" +
                "  \"name\": \"主菜单\",\n" +
                "  \"type\": 0,\n" +
                "  \"parentId\": 0,\n" +
                "  \"rank\": -1,\n" +
                "  \"status\": 1,\n" +
                "  \"child\": [\n" +
                "    {\n" +
                "      \"id\": 10,\n" +
                "      \"value\": \"user.operations\",\n" +
                "      \"name\": \"用户运营\",\n" +
                "      \"type\": 0,\n" +
                "      \"parentId\": 1000,\n" +
                "      \"rank\": 1,\n" +
                "      \"status\": 1,\n" +
                "      \"child\": [\n" +
                "        {\n" +
                "          \"id\": 101,\n" +
                "          \"value\": \"user.list\",\n" +
                "          \"name\": \"用户列表\",\n" +
                "          \"type\": 1,\n" +
                "          \"parentId\": 10,\n" +
                "          \"rank\": 0,\n" +
                "          \"status\": 1,\n" +
                "          \"child\": [\n" +
                "            {\n" +
                "              \"id\": 5001,\n" +
                "              \"value\": \"user.list.search\",\n" +
                "              \"name\": \"搜索\",\n" +
                "              \"type\": 2,\n" +
                "              \"parentId\": 101,\n" +
                "              \"rank\": 0,\n" +
                "              \"status\": 1,\n" +
                "              \"child\": null\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": 5002,\n" +
                "              \"value\": \"user.list.download\",\n" +
                "              \"name\": \"下载\",\n" +
                "              \"type\": 2,\n" +
                "              \"parentId\": 101,\n" +
                "              \"rank\": 0,\n" +
                "              \"status\": 1,\n" +
                "              \"child\": null\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 102,\n" +
                "          \"value\": \"invitation.relationship.records\",\n" +
                "          \"name\": \"邀请关系记录\",\n" +
                "          \"type\": 1,\n" +
                "          \"parentId\": 10,\n" +
                "          \"rank\": 1,\n" +
                "          \"status\": 1,\n" +
                "          \"child\": [\n" +
                "            {\n" +
                "              \"id\": 7336,\n" +
                "              \"value\": \"invitation.relationship.records.new\",\n" +
                "              \"name\": \"新建\",\n" +
                "              \"type\": 2,\n" +
                "              \"parentId\": 102,\n" +
                "              \"rank\": 0,\n" +
                "              \"status\": 1,\n" +
                "              \"child\": null\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": 7337,\n" +
                "              \"value\": \"invitation.relationship.records.detail\",\n" +
                "              \"name\": \"查看\",\n" +
                "              \"type\": 2,\n" +
                "              \"parentId\": 102,\n" +
                "              \"rank\": 0,\n" +
                "              \"status\": 1,\n" +
                "              \"child\": null\n" +
                "            } \n" +
                "          ]\n" +
                "        } \n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        // 将JSON字符串解析为JSONObject
        JSONObject root = JSON.parseObject(jsonString);

        // 进行结构转换和字符串追加
        transformAndAppend(root, "");

        // 打印转换后的JSON字符串
        System.out.println(root.toJSONString());
    }

    // 递归方法进行结构转换和字符串追加
    private static void transformAndAppend(JSONObject node, String parentName) {
        String name = node.getString("name");
        node.put("name", parentName + name);

        JSONArray childNodes = node.getJSONArray("child");
        if (childNodes != null && !childNodes.isEmpty()) {
            for (int i = 0; i < childNodes.size(); i++) {
                transformAndAppend(childNodes.getJSONObject(i), parentName + name + ".");
            }
        }
    }

    @Autowired
    private TestTraService testTraService;

    @GetMapping("/tra")
    public void testTra() {
        testTraService.testTra();
    }

}
