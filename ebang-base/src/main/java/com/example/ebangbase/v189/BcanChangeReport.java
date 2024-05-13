package com.example.ebangbase.v189;

import com.alibaba.fastjson.JSONObject;
import com.example.ebangbase.model.TestDO;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/18 15:11
 */
public class BcanChangeReport {

    public static void main(String[] args) {
       // BigDecimal b = new BigDecimal(200.00);
      //  System.out.println(b.stripTrailingZeros().toPlainString());
       // System.out.println(b.stripTrailingZeros().toPlainString());
     //  b.setScale(2);
     /*   System.out.println(b.scale());
        System.out.println(new BigDecimal(b.stripTrailingZeros().toPlainString()).scale());
        System.out.println(b.stripTrailingZeros().scale());
        System.out.println(b.scale());*/
        //   System.out.println(b.stripTrailingZeros());
      /*  String ss = "{\"PositionDeposit_Failed\": {\"id\": \"PositionDeposit_Failed\", \"disabled\": 0, \"eventName\": {\"en_US\": \"Failed\", \"zh_CN\": \"存入失败\"}, \"isApprove\": 0, \"adjacencyList\": [\"PositionDeposit_FailedPendingReturn\"]}, \"PositionDeposit_Success\": {\"id\": \"PositionDeposit_Success\", \"disabled\": 0, \"eventName\": {\"en_US\": \"Success\", \"zh_CN\": \"存入成功\"}, \"isApprove\": 0}, \"PositionDeposit_Unstarted\": {\"id\": \"PositionDeposit_Unstarted\", \"children\": {\"PositionDeposit_PositionDeliver\": {\"id\": \"PositionDeposit_PositionDeliver\", \"disabled\": 0, \"eventName\": {\"en_US\": \"Position deliver\", \"zh_CN\": \"持仓转移\"}, \"isApprove\": 1}, \"PositionDeposit_RiskControlVerification\": {\"id\": \"PositionDeposit_RiskControlVerification\", \"disabled\": 0, \"eventName\": {\"en_US\": \"Risk control verification\", \"zh_CN\": \"风控审核\"}, \"isApprove\": 1}}, \"disabled\": 0, \"eventName\": {\"en_US\": \"Unstarted\", \"zh_CN\": \"待存入\"}, \"isApprove\": 0, \"adjacencyList\": [\"PositionDeposit_Delivering\"]}, \"PositionDeposit_Delivering\": {\"id\": \"PositionDeposit_Delivering\", \"children\": {\"PositionDeposit_PositionTransferIn\": {\"id\": \"PositionDeposit_PositionTransferIn\", \"disabled\": 0, \"eventName\": {\"en_US\": \"Position transfer in\", \"zh_CN\": \"持仓转入\"}, \"isApprove\": 0}}, \"disabled\": 0, \"eventName\": {\"en_US\": \"Delivering\", \"zh_CN\": \"存入中\"}, \"isApprove\": 0, \"adjacencyList\": [\"PositionDeposit_Success\"]}, \"PositionDeposit_FailedReturned\": {\"id\": \"PositionDeposit_FailedReturned\", \"disabled\": 0, \"eventName\": {\"en_US\": \"Failed,returned\", \"zh_CN\": \"失败已退回\"}, \"isApprove\": 0}, \"PositionDeposit_FailedPendingReturn\": {\"id\": \"PositionDeposit_FailedPendingReturn\", \"children\": {\"PositionDeposit_PositionReturn\": {\"id\": \"PositionDeposit_PositionReturn\", \"disabled\": 0, \"eventName\": {\"en_US\": \"Position return\", \"zh_CN\": \"持仓退回\"}, \"isApprove\": 1}}, \"disabled\": 0, \"eventName\": {\"en_US\": \"Failed,pending return\", \"zh_CN\": \"失败待退回\"}, \"isApprove\": 1, \"adjacencyList\": [\"PositionDeposit_FailedReturned\"]}}";
        JSONObject jsonObject = JSONObject.parseObject(ss);
        System.out.println(jsonObject);*/
     /*   TestDO testDO = new TestDO();
        //testDO.setName();
        testDO.setXxx("xxx");
        TestDO testDO1 = new TestDO();
        testDO1.setName("258");
        BeanUtils.copyProperties(testDO, testDO1);
        System.out.println(testDO1.getName());
        testDO.setName("1234546");
        System.out.println(testDO1.getName());
*/
      /*  BigDecimal b = new BigDecimal(100);
        BigDecimal bigDecimal = b.setScale(8);
        System.out.println(b.toPlainString());
        System.out.println(bigDecimal.toPlainString());
        System.out.println();*/
        TestDO testDO1 = new TestDO();
        System.out.println(testDO1);

        Integer i = 1;
        int x = 1;
        System.out.println(i.equals(1));
    }
}
