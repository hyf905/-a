/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Lenovo
 * @create 2021/6/24
 * @since 1.0.0
 */
package com.ateam05.hotel.zhifubao.bean;

import com.alipay.api.AlipayApiException;

/*支付服务*/
public interface PayService {
    /*支付宝*/
    String aliPay(AlipayBean alipayBean) throws AlipayApiException;
}
