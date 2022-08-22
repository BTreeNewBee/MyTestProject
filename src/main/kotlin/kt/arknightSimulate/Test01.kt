package kt.arknightSimulate

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger


fun main() {
    val atomicInteger = AtomicInteger(0)
    var lastTime = System.currentTimeMillis()
    val scheduledThreadPool = Executors.newScheduledThreadPool(5)
    //陈每3秒骂一次
    scheduledThreadPool.scheduleAtFixedRate({
        atomicInteger.incrementAndGet()
    }, 0, 3000, TimeUnit.MILLISECONDS)
    //空弦每2.5秒拉一把
    scheduledThreadPool.scheduleAtFixedRate({
        atomicInteger.incrementAndGet()
    }, 0, 2500, TimeUnit.MILLISECONDS)
    //肥鸭每2.8*(1-0.27) = 2.044秒开一次
    scheduledThreadPool.scheduleAtFixedRate({
        atomicInteger.incrementAndGet()
    }, 0, 2044, TimeUnit.MILLISECONDS)
    //每50ms检查一下有没有满7点
    scheduledThreadPool.scheduleAtFixedRate({
        if (atomicInteger.get() >= 7) {
            println("${System.currentTimeMillis()} : {${System.currentTimeMillis() - lastTime}} 满7点了开一次技能")
            atomicInteger.set(0)
            scheduledThreadPool.schedule({
                println("${System.currentTimeMillis()} : 技能结束")
                lastTime = System.currentTimeMillis()
                atomicInteger.set(0)
            }, 2800, TimeUnit.MILLISECONDS)
        }
    }, 0, 50, TimeUnit.MILLISECONDS)

}