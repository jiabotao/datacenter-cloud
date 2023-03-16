package org.datacenter.common.core.constants;

import org.springframework.http.HttpStatus;

public class BasicConstant {
    /**
     * 成功标记
     */
    public static final Integer SUCCESS = HttpStatus.OK.value();

    /**
     * 失败标记
     */
    public static final Integer FAIL = HttpStatus.INTERNAL_SERVER_ERROR.value();


    public static final String UTF8 = "UTF-8";


}
