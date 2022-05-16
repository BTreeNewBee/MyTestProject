package iguigui.express;

import cn.hutool.http.GlobalHeaders;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ExpressTest {

    static String cookie = "BAIDUID=C5B2147727C4AA533DF7BD7AA800292E:FG=1";

    public static void main(String[] args) {
        System.out.println("hY2tuh9UUKy_uY5gryWLA2pdvRH_Rb4s0eWAyqOvTjALAEGeOd1dXlpSm7N3OGdC".length());
//        HttpResponse execute = HttpRequest.get("https://www.baidu.com/baidu?isource=infinity&iname=baidu&itype=web&tn=02003390_42_hao_pg&ie=utf-8&wd=%E5%BF%AB%E9%80%92").execute();
//        Map<String, List<String>> headers = execute.headers();
//        String body = execute.body();
//        System.out.println(body);
//        Pattern pattern = Pattern.compile("tokenV2=(.*?)\"");
//        Matcher matcher = pattern.matcher(body);
//        if (matcher.find()) {
//            System.out.println(matcher.group());
//        }
//
//        Optional<Map.Entry<String, List<String>>> first = headers.entrySet().stream().filter(e -> "Set-Cookie".equals(e.getKey())).findFirst();
//        for (String s : first.get().getValue()) {
//            System.out.println(s);
//            if (s.startsWith("BAIDUID")) {
//                String[] split = s.split(";");
//                cookie = split[0];
//            }
//        }

//
//        String postNumber = "ci091440818jp";
//        String url = "https://express.baidu.com/express/api/express";
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("query_from_srcid","4001");
//        jsonObject.put("isBaiduBoxApp",10002);
//        jsonObject.put("isWisePc",10020);
//        jsonObject.put("tokenV2","IyvaD--yb0UweUIpPF5r6tc3VgdkPR2Mmbf37lfnAT7EiDvZcs7KADsfEpMGQZZ5");
//        jsonObject.put("appid",4001);
//        jsonObject.put("nu",postNumber);
//        jsonObject.put("qid","949ee9460001b3e7");
//        jsonObject.put("_",System.currentTimeMillis());
//        HttpResponse execute1 = HttpUtil.createGet(url)
//                .form(jsonObject)
//                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:100.0) Gecko/20100101 Firefox/100.0")
//                .header("Cookie", cookie)
//                .header("Referer", "https://www.baidu.com/baidu?tn=monline_4_dg&ie=utf-8&wd=ci091440818jp")
//                .execute();
//
//
//        System.out.println(execute1.body());
////

    }
}
