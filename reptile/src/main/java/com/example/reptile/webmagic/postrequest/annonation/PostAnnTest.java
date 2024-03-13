package com.example.reptile.webmagic.postrequest.annonation;

import com.example.reptile.webmagic.getrequest.annonation.model.Article;
import com.example.reptile.webmagic.postrequest.annonation.model.PostResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.utils.HttpConstant;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/9 17:44
 */
public class PostAnnTest {

    public static void main(String[] args) {
        Request request = new Request("https://msg.csdn.net/v1/web/message/view/unread");
        request.addCookie("cookie", "uuid_tt_dd=10_37085562930-1649923668859-391490; UN=qq_35285375; __bid_n=1843274a36753cf7f54207; FEID=v10-674be56c147fd0a48c93a3572023fe806f9b4ddb; __xaf_fpstarttimer__=1672802894013; __xaf_thstime__=1672802894126; __xaf_fptokentimer__=1672802894166; FCNEC=%5B%5B%22AKsRol88AwHT_ak0k5hV3S17naEetEmwO53VvAL9E3PahIvBu5WfQZmwifyficuIdpHoA0DvxRVBiVNrDNs58-9luLzCgPa_uswxx4leD6f5ulSIEo4jLOnP_QvQcUAzNbaFLn5EbFiPHg0qrTFcWw1lQckLeQ4kfg%3D%3D%22%5D%2Cnull%2C%5B%5D%5D; FPTOKEN=1ElE+czfPezofEY60He3yhKLr/j/EiZAFOqj1IyOfgb6+YDq9VkvYcMWxyt+D7XYjQ58KwGZPm6EEllfURBYtHDGXtoRwbDTSCVyUDMzGitmjT6GpHfQmcsIton2+omgigXiW53KWo+rO0a3LjT36p5FVEWAYBVO9lNr4ZxZPr2VPbST2keqUkQHF2xxqGY6QSJ0iifYOGqG5TKB5ws+9KX+JOEOwAk0NU/g/7GxSbgq76O1QNOKMPlBEtgqZ/nCOvbbEfXlJyqceEEDCRauyYGHGjB0rllCYUHmzfDfXsT9nVjav3aN2+NWDwP0AED5icnCDElmbLHWO/AMp1WnIZbuFlEDB0nWslLQKxs8aW8tuFO8uwhdNPIRfmWwSvRwo0EUL+pe74Zrj+Z/Ho/aUA==|Y7ZYzMGUCGuUYtm6ge0oRUOX1AEoDDyceV163pxAmDw=|10|50d2c220fed033ec0b0e653055b17a93; UserName=qq_35285375; UserInfo=1c36303e549641078f49030f37b6c270; UserToken=1c36303e549641078f49030f37b6c270; UserNick=qq_35285375; AU=3A2; BT=1701160823427; p_uid=U010000; Hm_up_6bcd52f51e9b3dce32bec4a3997715ac=%7B%22islogin%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isonline%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isvip%22%3A%7B%22value%22%3A%220%22%2C%22scope%22%3A1%7D%2C%22uid_%22%3A%7B%22value%22%3A%22qq_35285375%22%2C%22scope%22%3A1%7D%7D; c_segment=2; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1708502547; dp_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjkxOTQ5OSwiZXhwIjoxNzEwNDg0NzUxLCJpYXQiOjE3MDk4Nzk5NTEsInVzZXJuYW1lIjoicXFfMzUyODUzNzUifQ.csNMwW5iovWfiA1R9Y_OGJm1I-XkVh5LIvNVzq5GWHk; _clck=1d09ayv%7C2%7Cfjw%7C0%7C1520; qq_35285375comment_new=1709697225097; dc_sid=53136fea75999dd484c68278711f516c; log_Id_click=423; dc_session_id=10_1710117398002.312972; c_pref=default; SESSION=ODYyNzIwMTctMWZmMC00MDJjLWJjOTUtOWZjYzljOTM2MmQ5; firstDie=1; creative_btn_mp=3; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1710119564; __gads=ID=d7405bacb6cd60f7-22c5df83a5e00085:T=1683628143:RT=1710119565:S=ALNI_Mb7kU5qYKfu8vIaigVN1pcLicJfsw; __gpi=UID=00000c32572c83d7:T=1685003769:RT=1710119565:S=ALNI_MbKWXGBh_Muz_gJtx1G2QoTmvdFbw; __eoi=ID=e6d5d8028f086928:T=1705883020:RT=1710119565:S=AA-AfjaRMcpbKJCFA34zVvrOyov-; c_ref=default; c_first_ref=default; c_first_page=https%3A//blog.csdn.net/qq_35285375; c_dsid=11_1710119884936.188714; c_page_id=default; dc_tos=sa5sy4; log_Id_pv=830; log_Id_view=16245");
        request.setMethod(HttpConstant.Method.POST);
        request.setRequestBody(HttpRequestBody.json("{'coupon': true}", "UTF-8"));
        OOSpider.create(Site.me().setSleepTime(1000)
                        , new PostAnnoPipeline(), PostResult.class)
                .addRequest(request).thread(1).run();
        Runtime.getRuntime().halt(-1);
        System.exit(1);
    }

}
