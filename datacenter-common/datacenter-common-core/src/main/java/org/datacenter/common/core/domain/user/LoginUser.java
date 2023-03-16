package org.datacenter.common.core.domain.user;

import lombok.Data;
import org.datacenter.common.core.domain.basic.BaseEntity;

import java.io.Serializable;

/**
 * @author liuxiaoxi
 */
@Data
public class LoginUser extends BaseEntity {

    protected String userName;

    protected String password;
}
