package org.datacenter.system.service;

import org.datacenter.common.core.domain.user.DataCenterUser;

public interface IDataCenterUserService {

    public DataCenterUser selectUserByUserName(String userName);
}
