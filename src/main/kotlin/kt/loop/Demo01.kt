package kt.loop


fun main() {
    repeat(10){i->repeat(i){print("$i*$it=${(i)*(it)} ")};println();}
    for(i in 1..9){for(j in 1..i)print("$j*$i=${i*j} ");println()}
    (1..9).forEach{i->(1..i).forEach{print("$i*$it=${i*it} ")};println()}
}

