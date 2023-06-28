package kt.jcommander

import com.beust.jcommander.JCommander
import com.beust.jcommander.Parameter


fun main() {
    val command = """-k "123 456" -p 100-200 -c 1 """
    val args = Args()
    val argv = arrayOf("-k", "2", "-p", "unit")
    val argv2 = "-host host1 -verbose -host host2"
    JCommander.newBuilder()
        .addObject(args)
        .build()
        .parse(argv2)
    println(args)


}




class Args{

    @Parameter(names = ["-host"], description = "The host")
    var hosts: List<String>  = ArrayList()

    @Parameter
    var parameters:ArrayList<String> = ArrayList()

    @Parameter(names = ["-k", "--keyword"], description = "关键字")
    var keyWord:String = ""

    @Parameter(names     = [    "-p", "--price"], description = "价格区间")
    var priceRange:String = ""

    @Parameter(names = ["-c", "--condition"], description = "商品状态")
    var condition:String = ""

    override fun toString(): String {
        return "Args(hosts=$hosts, parameters=$parameters, keyWord='$keyWord', priceRange='$priceRange', condition='$condition')"
    }


}