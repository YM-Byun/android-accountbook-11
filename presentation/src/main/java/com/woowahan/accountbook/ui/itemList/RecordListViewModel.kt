package com.woowahan.accountbook.ui.itemList

import androidx.lifecycle.ViewModel
import com.woowahan.domain.accountUseCase.DeleteRecordsUseCase
import com.woowahan.domain.model.Record
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordListViewModel @Inject constructor(
    private val deleteRecordsUseCase: DeleteRecordsUseCase
) : ViewModel() {
    suspend fun deleteItems(records: List<Record>) {
        deleteRecordsUseCase.execute(records)
    }
}