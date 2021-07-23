///**
// * 〈一句话功能简述〉<br>
// * 〈〉
// *
// * @author Lenovo
// * @create 2021/6/28
// * @since 1.0.0
// */
//package com.ateam05.hotel.config;
//
//import com.alibaba.fastjson.JSON;
//import com.alipay.api.AlipayApiException;
//import com.alipay.api.AlipayClient;
//import com.alipay.api.DefaultAlipayClient;
//import com.alipay.api.request.AlipayTradePagePayRequest;
//import com.ateam05.hotel.model.domain.Checkinfo;
//import com.ateam05.hotel.config.PropertiesConfig;
//
///* 支付宝 */
//public class AlipayUtil {
//    public static String connect(Checkinfo checkinfo) throws AlipayApiException {
//        //1、获得初始化的AlipayClient
//        AlipayClient alipayClient = new DefaultAlipayClient(
//                PropertiesConfig.getKey("gatewayUrl"),//支付宝网关
//                PropertiesConfig.getKey("app_id"),//appid
//                PropertiesConfig.getKey("merchant_private_key"),//商户私钥
//                "json",
//                PropertiesConfig.getKey("charset"),//字符编码格式
//                PropertiesConfig.getKey("alipay_public_key"),//支付宝公钥
//                PropertiesConfig.getKey("sign_type")//签名方式
//        );
//        //2、设置请求参数
//        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
//        //页面跳转同步通知页面路径
//        alipayRequest.setReturnUrl(PropertiesConfig.getKey("return_url"));
//        // 服务器异步通知页面路径
//        alipayRequest.setNotifyUrl(PropertiesConfig.getKey("notify_url"));
//        //封装参数
//        alipayRequest.setBizContent(JSON.toJSONString(checkinfo));
//        //3、请求支付宝进行付款，并获取支付结果
//        String result = alipayClient.pageExecute(alipayRequest).getBody();
//        //返回付款信息
//        return  result;
//    }
//}
