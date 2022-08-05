package com.woowahan.accountbook.extenstion

fun String.month(): Int {
    return this.replace("년", "").replace("월", "").split(" ")[1].toInt()
}

fun String.year(): Int {
    return this.replace("년", "").split(" ")[0].toInt()
}

fun String.day(): Int {
    return this.replace("년", "").replace("월", "").replace("일", "").split(" ")[2].toInt()
}