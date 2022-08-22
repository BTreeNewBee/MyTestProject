package kt.arknightSimulate

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger


val scheduledThreadPool: ScheduledExecutorService = Executors.newScheduledThreadPool(5)

fun main() {
    val atomicInteger = AtomicInteger(0)


    val 肥鸭 = FuckEmployee(7, 2800)
    //陈每3秒骂一次
    scheduledThreadPool.scheduleAtFixedRate({
        肥鸭.incrSp(1)
        println("陈每3秒骂一次")
    }, 3000, 3000, TimeUnit.MILLISECONDS)

    //空弦每2.5秒拉一把
    scheduledThreadPool.scheduleAtFixedRate({
        肥鸭.incrSp(1)
        println("空弦每2.5秒拉一把")
    }, 2500, 2500, TimeUnit.MILLISECONDS)

    //塞雷亚
    scheduledThreadPool.scheduleAtFixedRate({
        肥鸭.incrSp(2)
        println("塞雷亚每7秒拉一把")
    }, 8200, 8200, TimeUnit.MILLISECONDS)


    //肥鸭每2.8*(1-0.27) = 2.044秒开一次
    scheduledThreadPool.scheduleAtFixedRate({
        肥鸭.incrSp(1)
    }, 2044, 2044, TimeUnit.MILLISECONDS)


}


//干员
class FuckEmployee(private val sp: Int, private val coolDownTime: Long) {

    var currentSp = AtomicInteger(0)

    var coolDown = false

    var lastTime = System.currentTimeMillis()
    fun incrSp(incr: Int) {
        if (coolDown) {
            return
        }
        val addAndGet = currentSp.addAndGet(incr)
        if (addAndGet >= sp) {
            coolDown = true
            currentSp.set(0)
            println("${System.currentTimeMillis()} : {${System.currentTimeMillis() - lastTime}} 满${sp}点了正在使用技能,阻回${coolDownTime / 1000.0}秒")
            scheduledThreadPool.schedule({
                coolDown = false
                lastTime = System.currentTimeMillis()
            }, coolDownTime, TimeUnit.MILLISECONDS)
        }
    }

}
