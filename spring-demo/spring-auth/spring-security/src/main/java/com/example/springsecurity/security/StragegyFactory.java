package com.example.springsecurity.security;

import io.ebang.it.common.general.annotation.ControllerAttr;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

@Component
public class StragegyFactory {
    private static HashMap<String, String> sourceMap = new HashMap<>();
    private static HashMap<String, String> sourceUpdateMap = new HashMap<>();
    private static StragegyFactory stragegyFactory = new StragegyFactory();

    private StragegyFactory() {};

    public static StragegyFactory getInstance() {
        return stragegyFactory;
    }

    static{
        setMap("io.ebang.it.auth.api.controller", "auth-api");
        setMap("io.ebang.it.erp.api.controller", "erp-api");
    }
    public static void setMap(String scanPaht, String type) {
        try {
            Reflections reflections = new Reflections(scanPaht);
            if (reflections != null){
                Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(ControllerAttr.class);
                if (!CollectionUtils.isEmpty(classSet)) {
                    for (Class clazzSet:classSet) {
                        ControllerAttr controllerAttr = (ControllerAttr) clazzSet.getAnnotation(ControllerAttr.class);
                        if (controllerAttr.isAssociatedCompany()) {
                            String table = controllerAttr.table();
                            String path = "";
                            RequestMapping controllerMapping = (RequestMapping) clazzSet.getAnnotation(RequestMapping.class);
                            String controllerPath = controllerMapping == null ? "" : controllerMapping.value()[0];
                            Method[] methods = clazzSet.getDeclaredMethods();
                            for (Method method : methods) {
                                String methodPath = "";
                                String reqMethod = "";
                                RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
                                if (methodRequestMapping != null) {
                                    String[] value = methodRequestMapping.value();
                                    if (value != null && value.length > 0) {
                                        methodPath = value[0];
                                    }
                                    RequestMethod[] methodArray = methodRequestMapping.method();
                                    if (methodArray != null && methodArray.length > 0) {
                                        RequestMethod requestMethod = methodArray[0];
                                        reqMethod = requestMethod.name();
                                    }
                                }
                                if (StringUtils.isBlank(reqMethod) && method.getAnnotation(GetMapping.class) != null) {
                                    GetMapping getMapping = method.getAnnotation(GetMapping.class);
                                    String[] value = getMapping.value();
                                    if (value != null && value.length > 0) {
                                        methodPath = value[0];
                                    }
                                    reqMethod = "GET";
                                }
                                if (StringUtils.isBlank(reqMethod) && method.getAnnotation(PostMapping.class) != null) {
                                    PostMapping postMapping = method.getAnnotation(PostMapping.class);
                                    String[] value = postMapping.value();
                                    if (value != null && value.length > 0) {
                                        methodPath = value[0];
                                    }
                                    reqMethod = "POST";
                                }
                                if (StringUtils.isBlank(reqMethod) && method.getAnnotation(PutMapping.class) != null) {
                                    PutMapping putMapping = method.getAnnotation(PutMapping.class);
                                    String[] value = putMapping.value();
                                    if (value != null && value.length > 0) {
                                        methodPath = value[0];
                                    }
                                    reqMethod = "PUT";
                                }
                                if (StringUtils.isBlank(reqMethod) && method.getAnnotation(DeleteMapping.class) != null) {
                                    DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
                                    String[] value = deleteMapping.value();
                                    if (value != null && value.length > 0) {
                                        methodPath = value[0];
                                    }
                                    reqMethod = "DELETE";
                                }
                                path = reqMethod+"/"+type+controllerPath+methodPath;
                                sourceMap.put(path, table);
                                if (StringUtils.isNotBlank(path)) {
                                    sourceUpdateMap.put(replace(path), table);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public String getTable(String path) {
        String tableName = "";
        if (!CollectionUtils.isEmpty(sourceUpdateMap)) {
            tableName = sourceUpdateMap.get(replace(path));
        }
        return tableName;
    }

    public static String replace(String path) {
        if (StringUtils.isNotBlank(path)) {
            String[] split = StringUtils.split(path, "/");
            String [] newArray = new String[split.length];
            for (int i = 0; i < split.length; i++) {
                String s = split[i];
                if (s.contains("{") || s.contains("[")) {
                    newArray[i] = "*";
                } else {
                    newArray[i] = split[i];
                }
            }
            return StringUtils.join(newArray, "/");
        }
        return path;
    }
}
