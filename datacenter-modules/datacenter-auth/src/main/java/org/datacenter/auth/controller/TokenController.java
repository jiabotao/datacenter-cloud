package org.datacenter.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.daracenter.system.api.UserFeignClient;
import org.datacenter.common.core.domain.http.R;
import org.datacenter.common.core.domain.user.DataCenterUser;
import org.datacenter.common.security.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    UserFeignClient userFeignClient;

    @PostMapping("login")
    public String login()
    {
        R<DataCenterUser> result = userFeignClient.getUserInfo("jiabotao", "inner");
        DataCenterUser dataCenterUser = result.getData();
        ObjectMapper objMapper = new ObjectMapper();
        String resultStr = null;
        try {
            resultStr = objMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return  JWTUtil.createJWT(resultStr);
    }
}
