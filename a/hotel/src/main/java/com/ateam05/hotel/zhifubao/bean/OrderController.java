///**
// * 〈一句话功能简述〉<br>
// * 〈〉
// *
// * @author Lenovo
// * @create 2021/6/24
// * @since 1.0.0
// */
//package com.ateam05.hotel.zhifubao.bean;
//
//import com.alipay.api.AlipayApiException;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///* 订单接口 */
//@RestController()
//@RequestMapping("order")
//public class OrderController {
//    @Resource
//    private PayService payService;//调用支付服务
//    /*阿里支付*/
//    @PostMapping(value = "alipay")
//    public String alipay(String out_trade_no,String subject,String total_amount,String body) throws AlipayApiException {payService.aliPay(new AlipayBean()
//            .setBody(body)
//            .setOut_trade_no(out_trade_no)
//            .setTotal_amount(new StringBuffer().append(total_amount))
//            .setSubject(subject));
//
//        return  null;
//    }
//}
//
