package kt.spider

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

fun main() {
    val urlStr = "https://zs.loupan.com/community/huoju/"

    val doc: Document = Jsoup.connect(urlStr).get()
    val url = doc.connection().request().url()
    println("${url.protocol}://${url.host}")
    println(doc.title())
    val pagenxt = doc.select("a[href].pagenxt").attr("href")
    println(pagenxt)
    val list = doc.select("ul.list")[0]

    //<li><a href="https://zs.loupan.com/community/8591921.html" class="img" target="_blank" title="君华硅谷"> <img
    //            src="https://static.loupan.com/fy3/communityimage/2019/0114/1611109866388.jpg"
    //            onerror="this.src='https://static.loupan.com/no_pic.jpg'" alt="君华硅谷"> </a>
    //    <div class="text">
    //        <h2><a href="https://zs.loupan.com/community/8591921.html" target="_blank" title="君华硅谷"> 君华硅谷 </a></h2>
    //        <div class="txt">
    //            <div class="d"><a href="https://zs.esf.loupan.com/?&amp;comm_id=8591921"
    //                    target="_blank">0套在售</a>&nbsp;|&nbsp; <a href="https://zs.zu.loupan.com/?&amp;comm_id=8591921"
    //                    target="_blank">0套在租</a>&nbsp;|&nbsp;
    //            </div>
    //            <div class="d"><span>[火炬区]博爱七路依云路1号</span> <a
    //                    href="https://zs.loupan.com/community/8591921.html#zhoubianpeitao" class="map"
    //                    target="_blank">查看地图</a>
    //            </div>
    //            <div class="tags m_bottom_10"><span>住宅</span>
    //            </div>
    //            <div class="d"><a href="https://zs.loupan.com/community/around/8591921.html" class="orangeFont"
    //                    target="_blank">周边设施</a>&nbsp;|&nbsp; <a href="https://zs.loupan.com/community/trends/8591921.html"
    //                    class="orangeFont" target="_blank">价格走势</a>&nbsp;|&nbsp; <a
    //                    href="https://zs.loupan.com/community/photos/8591921.html" class="orangeFont"
    //                    target="_blank">小区相册</a>
    //            </div>
    //        </div>
    //        <div class="price">
    //            <p>18000<i>元/㎡</i></p>
    //        </div>
    //    </div>
    //</li>

    list.select("li").forEach {
        val select = it.select("div")
        val name = select.select("h2").text().replace(",", "").replace(" ", "")
        val price = select.select("div.price").text()
        val div_d = select.select("div.text").select("div.d")
        val addressDiv = div_d[1]
        val address = addressDiv.select("span").text().replace("[", "").replace("]", "")
        val href = addressDiv.select("a").attr("href")
        val m_bottom_10 = select.select("div.text").select("div.tags m_bottom_10")
        var type = "住宅"
        if (m_bottom_10.isNotEmpty()) {
            type = m_bottom_10.text()
        }
        println("name:$name,address:$address,href:$href,type:$type,price:$price")
        val community = Community(name, address, href, type, price)
        println(community)
    }

}


data class Community(
    val name: String,
    val address: String,
    val href: String,
    val type: String,
    val price: String
)