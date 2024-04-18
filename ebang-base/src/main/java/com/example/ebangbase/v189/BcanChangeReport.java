package com.example.ebangbase.v189;

import com.alibaba.fastjson.JSONObject;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/18 15:11
 */
public class BcanChangeReport {

    public static void main(String[] args) {
        String ss = "{\"PositionDeposit_Failed\": {\"id\": \"PositionDeposit_Failed\", \"disabled\": 0, \"eventName\": {\"en_US\": \"Failed\", \"zh_CN\": \"存入失败\"}, \"isApprove\": 0, \"adjacencyList\": [\"PositionDeposit_FailedPendingReturn\"]}, \"PositionDeposit_Success\": {\"id\": \"PositionDeposit_Success\", \"disabled\": 0, \"eventName\": {\"en_US\": \"Success\", \"zh_CN\": \"存入成功\"}, \"isApprove\": 0}, \"PositionDeposit_Unstarted\": {\"id\": \"PositionDeposit_Unstarted\", \"children\": {\"PositionDeposit_PositionDeliver\": {\"id\": \"PositionDeposit_PositionDeliver\", \"disabled\": 0, \"eventName\": {\"en_US\": \"Position deliver\", \"zh_CN\": \"持仓转移\"}, \"isApprove\": 1}, \"PositionDeposit_RiskControlVerification\": {\"id\": \"PositionDeposit_RiskControlVerification\", \"disabled\": 0, \"eventName\": {\"en_US\": \"Risk control verification\", \"zh_CN\": \"风控审核\"}, \"isApprove\": 1}}, \"disabled\": 0, \"eventName\": {\"en_US\": \"Unstarted\", \"zh_CN\": \"待存入\"}, \"isApprove\": 0, \"adjacencyList\": [\"PositionDeposit_Delivering\"]}, \"PositionDeposit_Delivering\": {\"id\": \"PositionDeposit_Delivering\", \"children\": {\"PositionDeposit_PositionTransferIn\": {\"id\": \"PositionDeposit_PositionTransferIn\", \"disabled\": 0, \"eventName\": {\"en_US\": \"Position transfer in\", \"zh_CN\": \"持仓转入\"}, \"isApprove\": 0}}, \"disabled\": 0, \"eventName\": {\"en_US\": \"Delivering\", \"zh_CN\": \"存入中\"}, \"isApprove\": 0, \"adjacencyList\": [\"PositionDeposit_Success\"]}, \"PositionDeposit_FailedReturned\": {\"id\": \"PositionDeposit_FailedReturned\", \"disabled\": 0, \"eventName\": {\"en_US\": \"Failed,returned\", \"zh_CN\": \"失败已退回\"}, \"isApprove\": 0}, \"PositionDeposit_FailedPendingReturn\": {\"id\": \"PositionDeposit_FailedPendingReturn\", \"children\": {\"PositionDeposit_PositionReturn\": {\"id\": \"PositionDeposit_PositionReturn\", \"disabled\": 0, \"eventName\": {\"en_US\": \"Position return\", \"zh_CN\": \"持仓退回\"}, \"isApprove\": 1}}, \"disabled\": 0, \"eventName\": {\"en_US\": \"Failed,pending return\", \"zh_CN\": \"失败待退回\"}, \"isApprove\": 1, \"adjacencyList\": [\"PositionDeposit_FailedReturned\"]}}";
        JSONObject jsonObject = JSONObject.parseObject(ss);
        System.out.println(jsonObject);
    }
}
