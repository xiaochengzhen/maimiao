package com.example.springsecurity.security;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.ebang.it.common.general.utils.SpringContextBeanUtil;
import io.ebang.it.data.auth.db.model.menu.SysMenu;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author xiaobo
 * @description
 * @date 2022/12/7 09:01
 */
public class SecurityUtils {

    public static Integer getCompanyId(HttpServletRequest request) {
        //路径
        Integer companyId = pathCompanyId(request);
        if (companyId == null) {
            String companyIdString = request.getParameter("companyId");
            if (StringUtils.isNotBlank(companyIdString)){
                companyId = Integer.parseInt(companyIdString);
            } else {
                companyId = getCompanyIdByRequest(request);
            }
        }
        return companyId;
    }

    public static Integer postCompanyId(HttpServletRequest request, String bodyString) {
        //路径
        Integer companyId = pathCompanyId(request);
        if (companyId == null) {
            companyId = getCompanyIdByBodyString(bodyString);
        }
        return companyId;
    }

    public static Integer putCompanyId(HttpServletRequest request, String bodyString) {
        //路径
        Integer companyId = pathCompanyId(request);
        if (companyId == null) {
            companyId = getCompanyIdByBodyString(bodyString);
        }
        return companyId;
    }

    public static Integer deleteCompanyId(HttpServletRequest request) {
        //路径
        Integer companyId = pathCompanyId(request);
        if (companyId == null) {
            String companyIdString = request.getParameter("companyId");
            if (StringUtils.isNotBlank(companyIdString)){
                companyId = Integer.parseInt(companyIdString);
            } else {
                companyId = getCompanyIdByRequest(request);
            }
        }
        return companyId;
    }

    public static Integer getCompanyIdByBodyString(String bodyString) {
        Integer companyId = null;
        if (StringUtils.isNotBlank(bodyString)) {
            if (bodyString.contains("companyId")) {
                if (bodyString.startsWith("[")) {
                    JSONArray jsonArray = JSONArray.parseArray(bodyString);
                    if (jsonArray != null && jsonArray.size() > 0) {
                        for (Object o : jsonArray) {
                            JSONObject jsonb = (JSONObject) o;
                            companyId = parseJSON(jsonb);
                        }
                    }
                } else {
                    JSONObject jsonObject = JSONObject.parseObject(bodyString);
                    companyId = parseJSON(jsonObject);
                }
            }
        }
        return companyId;
    }

    public static Integer parseJSON(JSONObject jsonObject) {
        for(String key : jsonObject.keySet()) {
            if(jsonObject.get(key) instanceof JSONObject){
                JSONObject childJb = JSONObject.parseObject(String.valueOf(jsonObject.get(key)));
                return parseJSON(childJb);
            }else if(jsonObject.get(key) instanceof JSONArray){
                JSONArray childArr = (JSONArray) jsonObject.get(key);
                if (childArr != null && childArr.size() > 0) {
                    for (Object o : childArr) {
                        JSONObject jsonb = (JSONObject) o;
                        return parseJSON(jsonb);
                    }
                }
            }else{
                if (key.equals("companyId")) {
                    Integer companyId = jsonObject.getInteger("companyId");
                    return companyId;
                }
            }
        }
        return null;
    }

    public static Integer pathCompanyId(HttpServletRequest request) {
        Integer companyId = null;
        Integer id = null;
        String requestPath = request.getMethod()+request.getServletPath()+request.getPathInfo();
        String[] requestPathArray = StringUtils.split(requestPath, "/");
        SysMenu sysMenu = matcherMenu(request);
        if (sysMenu != null) {
            String path = sysMenu.getPath();
            String[] menuPathArray = StringUtils.split(path, "/");
            for (int i = 0; i < menuPathArray.length; i++) {
                String menuPath = menuPathArray[i];
                if (menuPath.equals("{companyId}")) {
                    companyId = Integer.parseInt(requestPathArray[i]);
                    break;
                }
            }
            if (companyId == null) {
                for (int i = 0; i < menuPathArray.length; i++) {
                    String menuPath = menuPathArray[i];
                    if (menuPath.equals("{id}") || menuPath.equals("{ids}") || menuPath.equals("{idList}")) {
                        String ids = requestPathArray[i];
                        id = Integer.parseInt(StringUtils.split(ids, ",")[0]);
                        break;
                    }
                }
            }
        }
        if (companyId == null && id != null) {
            companyId = getCompanyIdById(id, request);
        }
        return companyId;
    }

    public static Integer getCompanyIdById(Integer id, HttpServletRequest request) {
        JdbcTemplate jdbcTemplate ;
        Integer companyId = null;
        SysMenu sysMenu = matcherMenu(request);
        if (id != null && sysMenu != null) {
            String path = sysMenu.getPath();
            String servletPath = request.getServletPath();
            if ("/erp-api".equals(servletPath)){
                jdbcTemplate = SpringContextBeanUtil.getBean("jdbcTemplateErp", JdbcTemplate.class);
            } else {
                jdbcTemplate = SpringContextBeanUtil.getBean(JdbcTemplate.class);
            }
            StragegyFactory stragegyFactory = StragegyFactory.getInstance();
            String table = stragegyFactory.getTable(path);
            String sql = "select * from " + table + " where id = "+id;
            Map<String, Object> map = jdbcTemplate.queryForMap(sql);
            if (!CollectionUtils.isEmpty(map)){
                Object companyIdObject = map.get("company_id");
                if (companyIdObject != null) {
                    companyId = Integer.parseInt(companyIdObject.toString());
                }
            }
        }
        return companyId;
    }

    public static Integer getCompanyIdByRequest(HttpServletRequest request) {
        Integer companyId = null;
        Integer id = null;
        String idString = request.getParameter("id");
        if (StringUtils.isNotBlank(idString)) {
            id =  Integer.parseInt(StringUtils.split(idString, ",")[0]);
        } else {
            String ids = request.getParameter("ids");
            if (StringUtils.isNotBlank(ids)) {
                id =  Integer.parseInt(StringUtils.split(ids, ",")[0]);
            } else {
                String idList = request.getParameter("idList");
                if (StringUtils.isNotBlank(idList)) {
                    id =  Integer.parseInt(StringUtils.split(idList,",")[0]);
                }
            }
        }
        if (id != null) {
            companyId = getCompanyIdById(id, request);
        }
        return companyId;
    }

    public static SysMenu matcherMenu(HttpServletRequest request) {
        SysMenu sysMenu = null;
        List<SysMenu> list = SecurityResource.sysMenus;
        if (!CollectionUtils.isEmpty(list)) {
            for (SysMenu menu : list) {
                String rawPath = menu.getPath();
                if (org.apache.commons.lang3.StringUtils.isNotBlank(rawPath)) {
                    Pair<String, String> replace = replace(rawPath);
                    MyAntPathRequestMatcher myAntPathRequestMatcher = new MyAntPathRequestMatcher(replace.getRight(), replace.getLeft());
                    RequestMatcher.MatchResult matcher = myAntPathRequestMatcher.matcher(request);
                    if (matcher.isMatch()) {
                        sysMenu = menu;
                        break;
                    }
                }
            }
        }
        return sysMenu;
    }

    public static Pair<String, String> replace(String rawPath) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(rawPath)) {
            String method = "";
            String newPath = "";
            String[] split = org.apache.commons.lang3.StringUtils.split(rawPath, "/");
            String [] newArray = new String[split.length-1];
            for (int i = 0; i < split.length; i++) {
                String s = split[i];
                if (i == 0) {
                    method = s;
                } else {
                    if (s.contains("{")) {
                        newArray[i-1] = "*";
                    } else if (s.contains("[") && s.contains("]")) {
                        newArray[i-1] = split[i].replace("[", "").replace("]","");
                    } else {
                        newArray[i-1] = split[i];
                    }
                }

            }
            newPath = org.apache.commons.lang3.StringUtils.join(newArray, "/");
            return Pair.of(method, "/"+newPath);
        }
        return null;
    }

    public static Pair<HttpMethod, String> replaceMethod(String rawPath) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(rawPath)) {
            String method = "";
            String[] split = org.apache.commons.lang3.StringUtils.split(rawPath, "/");
            String [] newArray = new String[split.length-1];
            for (int i = 0; i < split.length; i++) {
                String s = split[i];
                if (i == 0) {
                    method = s;
                } else {
                    if (s.contains("{")) {
                        newArray[i-1] = "*";
                    } else if (s.contains("[") && s.contains("]")) {
                        newArray[i-1] = split[i].replace("[", "").replace("]","");
                    } else {
                        newArray[i-1] = split[i];
                    }
                }
            }
            String newPath = org.apache.commons.lang3.StringUtils.join(newArray, "/");
            HttpMethod httpMethod = HttpMethod.GET;
            if (org.apache.commons.lang3.StringUtils.isNotBlank(method)) {
                switch (method) {
                    case "GET":
                        httpMethod = HttpMethod.GET;
                        break;
                    case "POST":
                        httpMethod = HttpMethod.POST;
                        break;
                    case "PUT":
                        httpMethod = HttpMethod.PUT;
                        break;
                    case "DELETE":
                        httpMethod = HttpMethod.DELETE;
                        break;
                    default:
                        break;
                }
            }
            return Pair.of(httpMethod, "/"+newPath);
        }
        return null;
    }
}
