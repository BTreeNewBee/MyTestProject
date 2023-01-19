package kt.baidumaps

import cn.hutool.http.HttpUtil


fun main() {
    val url = "https://api.map.baidu.com/place/v2/search"
    val map: MutableMap<String, Any> = mutableMapOf()
    map["query"] = "住宅区"
    map["region"] = "340"
    map["city_limit"] = "true"
    map["output"] = "json"
    map["scope"] = "2"
    map["center"] = "22.559,113.9035"
    map["page_num"] = "0"
    map["ak"] = "52RquM9kvK6uHWqypFQUL3iHwsXAA9Nn"
    map["filter"] = "sort_name:distance;sort_rule:1"

    println(HttpUtil.get(url, map))


}