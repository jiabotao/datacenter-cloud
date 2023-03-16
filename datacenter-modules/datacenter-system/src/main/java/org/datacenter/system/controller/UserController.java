package org.datacenter.system.controller;

import jakarta.annotation.Resource;
import org.datacenter.common.core.domain.http.R;
import org.datacenter.common.core.domain.user.DataCenterUser;
import org.datacenter.common.core.domain.user.LoginUser;
import org.datacenter.common.security.annotation.InnerAuth;
import org.datacenter.system.service.IDataCenterUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    IDataCenterUserService userService;

    @InnerAuth(verify = false)
    @GetMapping("/info/{username}")
    public R<DataCenterUser> info(@PathVariable("username") String username)
    {
        DataCenterUser dataCenterUser = userService.selectUserByUserName(username);
        if (dataCenterUser==null)
        {
            return R.fail("用户名或密码错误");
        }
        // 角色集合
//        Set<String> roles = permissionService.getRolePermission(dataCenterUser);
//        // 权限集合
//        Set<String> permissions = permissionService.getMenuPermission(dataCenterUser);
//        LoginUser sysUserVo = new LoginUser();
//        sysUserVo.setSysUser(sysUser);
//        sysUserVo.setRoles(roles);
//        sysUserVo.setPermissions(permissions);
        return R.ok(dataCenterUser);
    }

}
