package kt.spider

import cn.hutool.http.HttpUtil

fun main() {

    val url = "https://api.map.baidu.com/place/v2/search"
    val map: MutableMap<String, Any> = mutableMapOf()
//    map["query"] = "美食"
//    map["location"] = "22.5729660392055,113.473248972131"
    map["query"] = "美食"
    map["location"] = "22.5729660392055,113.473248972131"
    //radius
    map["radius"] = 1000
    map["radius_limit"] = "false"
    map["output"] = "json"
    map["scope"] = "2"
    map["filter"] = "sort_name:distance;sort_rule:1"
    map["page_size"] = "10"
    map["page_num"] = "0"
    map["ak"] = "52RquM9kvK6uHWqypFQUL3iHwsXAA9Nn"

    println(HttpUtil.get(url, map))


}