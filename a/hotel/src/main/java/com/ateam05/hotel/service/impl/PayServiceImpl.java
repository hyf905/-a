///**
// * 〈一句话功能简述〉<br>
// * 〈〉
// *
// * @author Lenovo
// * @create 2021/6/24
// * @since 1.0.0
// */
//package com.ateam05.hotel.service.impl;
//
//import com.alipay.api.AlipayApiException;
//import com.ateam05.hotel.config.AlipayUtil;
//import com.ateam05.hotel.model.domain.Checkinfo;
//import com.ateam05.hotel.service.PayService;
//
//
//
//import org.springframework.stereotype.Service;
//
///* 支付服务 */
//@Service(value = "alipayOrderService")
//public class PayServiceImpl implements PayService {
//    @Override
//    public String aliPay(Checkinfo checkinfo) throws AlipayApiException {
//        return AlipayUtil.connect(checkinfo);
//    }
//}
//
