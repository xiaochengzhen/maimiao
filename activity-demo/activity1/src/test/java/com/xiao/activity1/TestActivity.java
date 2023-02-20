package com.xiao.activity1;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/25 14:22
 */
public class TestActivity {

    @Test
    public void testCreateDbTable() {
        //使用classpath下的activiti.cfg.xml中的配置创建processEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
        // 而除了使用默认配置进行创建工作流引擎对象，还可以通过自定义的方式进行创建。
        // 自定义配置文件名
        ProcessEngineConfiguration processEngineConfigurationFromResource = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        // 自定义配置文件名 bean对象id
        ProcessEngineConfiguration processEngineConfigurationFromResource1 = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml", "processEngineConfiguration");
    }

    @Test
    public void devo() {
        // 创建流程对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取service对象
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 流程部署 设置名字 将bpmn和png部署进去
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("test.bpmn20.xml")
           //     .addClasspathResource("bpmn/leave.png")
                .name("请假流程")
                .deploy();
        System.out.println("id = " + deploy.getId());
        System.out.println("name = " + deploy.getName());
    }

    @Test
    public void start(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 根据流程id启动流程
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("test");
        System.out.println("流程id = " + instance.getProcessDefinitionId());
        System.out.println("实例id = " + instance.getId());
        System.out.println("活动id = " + instance.getActivityId());
    }

    @Test
    public void findTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();

        List<Task> list = taskService.createTaskQuery().processDefinitionKey("test").taskAssignee("5001").list();
        for (org.activiti.engine.task.Task task : list){
            System.out.println("流程id = " + task.getProcessInstanceId());
            System.out.println("任务id = " + task.getId());
            System.out.println("任务负责人 = " + task.getAssignee());
            System.out.println("任务名称 = " + task.getName());
        }
    }




}
