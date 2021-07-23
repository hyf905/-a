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
import org.springframework.stereotype.Service;

/* 支付服务 */
@Service(value = "alipayOrderService")
public class PayServiceImpl implements PayService {
    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        return AlipayUtil.connect(alipayBean);
    }
}

