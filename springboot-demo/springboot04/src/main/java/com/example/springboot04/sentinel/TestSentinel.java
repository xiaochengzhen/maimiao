package com.example.springboot04.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/12/20 13:53
 */
public class TestSentinel {

    public static void main(String[] args) {
        initFlowRules();
        for (int i = 0; i < 25; i++) {
            try(Entry entry = SphU.entry("HelloWorld")){
                System.out.println("被保护的逻辑"+i);
            }catch (BlockException e) {
                System.out.println("处理被限制逻辑"+i);
            }
        }
    }

    private static void initFlowRules() {
        List<FlowRule> list = new ArrayList();
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("HelloWorld");
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(20);
        list.add(flowRule);
        FlowRuleManager.loadRules(list);

    }
}
