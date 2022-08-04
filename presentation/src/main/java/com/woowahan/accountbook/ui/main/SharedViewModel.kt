package com.woowahan.accountbook.ui.main

import androidx.lifecycle.ViewModel
import com.woowahan.domain.model.Category
import com.woowahan.domain.model.Payment
import com.woowahan.domain.model.Record

class SharedViewModel : ViewModel() {
    private val _sharedRecord = ArrayList<Record>()
    private val _sharedCategory = ArrayList<Category>()
    private val _sharedPayment = ArrayList<Payment>()

    private fun <T> baseSharingItem(item: T, sharedList: MutableList<T>) {
        sharedList.add(0, item)
    }

    private fun <T> baseGetSharedItem(sharedList: MutableList<T>): T? {
        return if (sharedList.isEmpty()) {
            null
        } else {
            val shared = sharedList.first()
            sharedList.removeAt(0)
            shared
        }
    }

    fun sharingRecord(record: Record) = baseSharingItem(record, _sharedRecord)
    fun getSharedRecord(): Record? = baseGetSharedItem(_sharedRecord)

    fun sharingPayment(payment: Payment) = baseSharingItem(payment, _sharedPayment)
    fun getSharedPayment(): Payment? = baseGetSharedItem(_sharedPayment)


    fun sharingCategory(category: Category) = baseSharingItem(category, _sharedCategory)
    fun getSharedCategory(): Category? = baseGetSharedItem(_sharedCategory)
}