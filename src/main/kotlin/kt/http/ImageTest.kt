package kt.http

import cn.hutool.http.HttpUtil

fun main() {
    //multipart file upload
    HttpUtil.createPost("http://127.0.0.1:3000/image").form("multipart", "d:/test.jpg").execute()
}