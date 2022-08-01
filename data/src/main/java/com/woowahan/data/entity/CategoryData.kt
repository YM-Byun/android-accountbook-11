package com.woowahan.data.entity

import com.woowahan.domain.model.Category

data class CategoryData(
    val name: String,
    val color: Int
)

fun CategoryData.toModel(): Category {
    return Category(this.name, this.color)
}
