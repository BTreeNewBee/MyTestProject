package iguigui.html;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.excel.util.FileUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class HtmlParse {

    public static void main(String[] args) throws IOException {
        byte[] b = FileUtil.readBytes("C:\\Users\\atmzx\\Desktop\\mylist4.txt");
        System.out.println("啥");
        System.out.println("以UTF-8输出" + new String(b,StandardCharsets.UTF_8));
        System.out.println("以默认字符集输出" + new String(b,Charset.defaultCharset()));
        System.out.println("以GBK输出" + new String(b,Charset.forName("GBK")));
        System.out.println(Charset.defaultCharset());
    }

}
