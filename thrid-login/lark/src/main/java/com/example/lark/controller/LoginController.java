package com.example.lark.controller;

import com.larksuite.oapi.core.AppSettings;
import com.larksuite.oapi.core.Config;
import com.larksuite.oapi.core.DefaultStore;
import com.larksuite.oapi.core.Domain;
import com.larksuite.oapi.core.api.AccessTokenType;
import com.larksuite.oapi.core.api.Api;
import com.larksuite.oapi.core.api.request.Request;
import com.larksuite.oapi.core.api.response.Response;
import com.larksuite.oapi.core.utils.Jsons;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/15 10:07
 */
@RestController
@Slf4j
public class LoginController {

    public Config config;

    public LoginController() {
        AppSettings appSettings = Config.createInternalAppSettings("cli_a2d64332a0b8500a", "hvRCqun8azwWixrCxjxZCeINBfTJ85HS", "VerificationToken", "EncryptKey");
        config = new Config(Domain.LarkSuite, appSettings, new DefaultStore());
    }

    @GetMapping("/auth")
    public String auth(@RequestParam(value = "code")String code) {
        Map<String, Object> message = new HashMap<>();
        message.put("grant_type", "authorization_code");
        message.put("code", code);
        // 构建请求
        Request<Map<String, Object>, Map<String, Object>> request = Request.newRequest("/open-apis/authen/v1/access_token",
                "POST", AccessTokenType.Tenant, message, new HashMap<>());
        Response<Map<String, Object>> response = null;
        try {
            response = Api.send(config, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("result = {}", Jsons.DEFAULT_GSON.toJson(response));
        if (response != null) {
            int resultCode = response.getCode();
            if (resultCode == 0) {
                Map<String, Object> data = response.getData();
                if (data != null && !data.isEmpty()){
                    String open_id = (String) data.get("open_id");
                    Response<Map<String, Object>> userByUserId = getUserByUserId(open_id);
                    if (userByUserId != null) {
                        int userByUserIdCode = userByUserId.getCode();
                        if (userByUserIdCode == 0) {
                            Map<String, Object> userByUserIdData = userByUserId.getData();
                            return Jsons.DEFAULT_GSON.toJson(userByUserId);
                        }
                    }
                }
            }
        }
        return Jsons.DEFAULT_GSON.toJson(response);
    }

    /**
     * @description 获取单个用户信息
     * @param openId
     * @author xiaobo
     * @date 2022/4/18 19:55
     */
    public Response<Map<String, Object>> getUserByUserId(String openId) {
        Request<Map<String, Object>, Map<String, Object>> request = Request.newRequest("/open-apis/contact/v3/users/" + openId,
                "GET", AccessTokenType.Tenant, null, new HashMap<>());
        Response<Map<String, Object>> response = null;
        try {
            response = Api.send(config, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
