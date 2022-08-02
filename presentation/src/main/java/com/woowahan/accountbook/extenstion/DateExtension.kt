package com.woowahan.accountbook.extenstion

fun String.month(): Int {
    return this.replace("년", "").replace("월", "").split(" ")[1].toInt()
}

fun String.year(): Int {
    return this.replace("년", "").replace("월", "").split(" ")[0].toInt()
}