package com.woowahan.data.entity

import com.woowahan.domain.model.Category

data class CategoryData(
    val id: Int,
    val name: String,
    val color: Int
)

fun CategoryData.toModel(): Category {
    return Category(this.id, this.name, this.color)
}
