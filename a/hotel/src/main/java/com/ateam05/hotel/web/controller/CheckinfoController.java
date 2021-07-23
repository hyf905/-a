package com.ateam05.hotel.web.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.ateam05.hotel.common.utils.ResultTool;

import com.ateam05.hotel.model.domain.Hotel;
import com.ateam05.hotel.model.dto.CheckinfoDTO;
import com.ateam05.hotel.model.dto.HotelDTO;
import com.ateam05.hotel.service.HotelService;
import com.ateam05.hotel.zhifubao.bean.AlipayBean;
import com.ateam05.hotel.zhifubao.bean.PayService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ateam05.hotel.service.CheckinfoService;
import com.ateam05.hotel.model.domain.Checkinfo;
import com.ateam05.hotel.common.entity.JsonResponse;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.alipay.api.AlipayConstants.CHARSET;
import static com.alipay.api.AlipayConstants.SIGN_TYPE;

/**
 *
 *  订单管理控制器
 *
 *
 * @author hyf
 * @since 2021-06-17
 * @version v1.0
 */
@RestController()
@RequestMapping("/api/checkinfo")
public class CheckinfoController {

    private final Logger logger = LoggerFactory.getLogger( CheckinfoController.class );


    @Autowired
    private HotelService hotelService;


    @Resource
    private PayService payService;//调用支付服务
    /*阿里支付*/
//    @PostMapping(value = "/alipay")
//    public String alipay(String out_trade_no,String subject,String total_amount,String body) throws AlipayApiException {
//        return  payService.aliPay(new Checkinfo()
//                .setBody(body)
//                .setOut_trade_no(out_trade_no)
//                .setTotal_amount(new StringBuffer().append(total_amount))
//                .setSubject(subject));
//    }


    @Autowired
    private CheckinfoService checkinfoService;

    /**
    * 描述：根据Id 查询
    *
    */
    @GetMapping("/{id}")
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Checkinfo  checkinfo =  checkinfoService.getById(id);


        return ResultTool.success(checkinfo);
    }
    /**
     * 描述：根据userId 查询
     *
     */
    @GetMapping("/selectcheckinfobyuserid/{userId}")
    @ResponseBody
    public JsonResponse getByUserId(@PathVariable("userId") Long userId)throws Exception {
        List<CheckinfoDTO> checkinfodto=  checkinfoService.querybyUserId(userId);

        for(CheckinfoDTO cd:checkinfodto){
            Hotel hotel = hotelService.getById(cd.getHotelid());
            cd.setHotelNameCn(hotel.getHotelNameCn());
        }
        return ResultTool.success(checkinfodto);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping("/deletecheckinfo")
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        checkinfoService.removeById(id);
        return ResultTool.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping("/updatecheckinfo")
    @ResponseBody
    public JsonResponse updateCheckinfo(@PathVariable("id") Long  id,Checkinfo  checkinfo) throws Exception {
        checkinfo.setId(id);
        checkinfoService.updateById(checkinfo);
        return ResultTool.success(null);
    }


    /**
    * 描述:创建Checkinfo
    *
    */
    @RequestMapping("/createcheckinfo")
    @ResponseBody
    public JsonResponse create(Checkinfo  checkinfo,String dateIn,String dateOut) throws Exception {
        checkinfo.setCheckInfoDate(new Date());
        checkinfo.setCheckInDate(new SimpleDateFormat("yyyy-MM-dd").parse(dateIn));
        checkinfo.setCheckOutDate(new SimpleDateFormat("yyyy-MM-dd").parse(dateOut));
        checkinfo.setStatus(true);
        checkinfoService.save(checkinfo);
//        payService.aliPay(new AlipayBean()
//                .setBody(null)
//                .setOut_trade_no("12312")
//                .setTotal_amount(new StringBuffer().append(checkinfo.getPrice()))
//                .setSubject("罗玲珑"));
        return ResultTool.success(payService.aliPay(new AlipayBean()
                .setBody(null)
                .setOut_trade_no("12312")
                .setTotal_amount(new StringBuffer().append(checkinfo.getPrice()))
                .setSubject("罗玲珑")));
    }

//    @RequestMapping(value = "/returnUrl", method = RequestMethod.GET)
//    public String returnUrl(HttpServletRequest request, HttpServletResponse response)
//            throws IOException, AlipayApiException {
//        System.out.println("=================================同步回调=====================================");
//
//        // 获取支付宝GET过来反馈信息
//        Map<String, String> params = new HashMap<String, String>();
//        Map<String, String[]> requestParams = request.getParameterMap();
//        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
//            String name = (String) iter.next();
//            String[] values = (String[]) requestParams.get(name);
//            String valueStr = "";
//            for (int i = 0; i < values.length; i++) {
//                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
//            }
//            // 乱码解决，这段代码在出现乱码时使用
//            valueStr = new String(valueStr.getBytes("utf-8"), "utf-8");
//            params.put(name, valueStr);
//        }
//
//        System.out.println(params);//查看参数都有哪些
//        boolean signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE); // 调用SDK验证签名
//        //验证签名通过
//        if(signVerified){
//            // 商户订单号
//            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
//
//            // 支付宝交易号
//            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
//
//            // 付款金额
//            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
//
//            System.out.println("商户订单号="+out_trade_no);
//            System.out.println("支付宝交易号="+trade_no);
//            System.out.println("付款金额="+total_amount);
//
////            //支付成功，修复支付状态
////            payService.updateById(Integer.valueOf(out_trade_no));
//            return "ok";//跳转付款成功页面
//        }else{
//            return "no";//跳转付款失败页面
//        }
//
//    }
}

