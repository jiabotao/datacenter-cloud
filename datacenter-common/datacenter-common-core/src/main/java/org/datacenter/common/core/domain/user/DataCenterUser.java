package org.datacenter.common.core.domain.user;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author liuxiaoxi
 */
@Data
public class DataCenterUser extends RegisterUser{

    protected Long userId;

    protected String nickName;

    protected String email;

    protected Integer gender;

    protected String phone;

    protected LocalDate birth;



}
