package iguigui.html;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.excel.util.FileUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
        byte[] b = FileUtil.readBytes("C:\\Users\\atmzx\\Desktop\\mylist3.txt");
        JSONObject jsonObject = JSON.parseObject(new String(b));
        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("tracks");

    }

}
