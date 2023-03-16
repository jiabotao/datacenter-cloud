package org.daracenter.system.api;

import org.datacenter.common.core.constants.SecurityConstants;
import org.datacenter.common.core.domain.http.R;
import org.datacenter.common.core.domain.user.DataCenterUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(contextId = "remoteUserService", value = "datacenter-system" )
public interface UserFeignClient {

    @GetMapping("/user/info/{username}")
    R<DataCenterUser> getUserInfo(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

}
