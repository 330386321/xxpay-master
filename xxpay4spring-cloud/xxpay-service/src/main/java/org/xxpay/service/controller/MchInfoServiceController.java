package org.xxpay.service.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xxpay.common.util.MyBase64;
import org.xxpay.common.util.MyLog;
import org.xxpay.dal.dao.model.MchInfo;
import org.xxpay.service.service.MchInfoService;

/**
 * @Description: 商户信息接口
 * @author dingzhiwei jmdhappy@126.com
 * @date 2017-07-05
 * @version V1.0
 * @Copyright: www.xxpay.org
 */
@RestController
public class MchInfoServiceController {

    private final MyLog _log = MyLog.getLog(MchInfoServiceController.class);

    @Autowired
    private MchInfoService mchInfoService;

    @RequestMapping(value = "/mch_info/select")
    public String selectMchInfo(@RequestParam String jsonParam) {
        // TODO 参数校验
        String param = new String(MyBase64.decode(jsonParam));
        JSONObject paramObj = JSON.parseObject(param);
        String mchId = paramObj.getString("mchId");
        MchInfo mchInfo = mchInfoService.selectMchInfo(mchId);
        JSONObject retObj = new JSONObject();
        if(mchInfo == null) {
            retObj.put("result", "");
            return retObj.toJSONString();
        }
        retObj.put("result", JSON.toJSON(mchInfo));
        _log.info("result:{}", retObj.toJSONString());
        return retObj.toJSONString();
    }



}
