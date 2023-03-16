package org.datacenter.system.service.impl;

import org.datacenter.common.core.domain.user.DataCenterUser;
import org.datacenter.system.service.IDataCenterUserService;
import org.springframework.stereotype.Service;

@Service
public class DataCenterUserServiceImpl implements IDataCenterUserService {
    @Override
    public DataCenterUser selectUserByUserName(String userName) {
        DataCenterUser dataCenterUser = new DataCenterUser();
        dataCenterUser.setUserId(1L);
        dataCenterUser.setUserName("jiabotao");
        dataCenterUser.setNickName("贾博韬");
        dataCenterUser.setEmail("724270650@qq.com");
        dataCenterUser.setPassword("JiaBoTao@0819");
        return dataCenterUser;
    }
}
