package com.fastlink.zekrapp.ui.zekirScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fastlink.zekrapp.appData.model.ZekirCategoryModel
import com.fastlink.zekrapp.di.ZekirSingleton
import com.fastlink.zekrapp.appData.model.ZekirModel
import com.fastlink.zekrapp.di.ZekirCategorySingleton
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZekirScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val zekirCounter = mutableIntStateOf(0)
    var zekirs: List<ZekirModel> = emptyList()
        private set
    var zekirCategory: MutableState<ZekirCategoryModel> =
        mutableStateOf(ZekirCategoryModel())
        private set

    init {
        val categoryId: Int = savedStateHandle.get<String>("categoryId")?.toInt() ?: 0
        zekirCategory.value = getZekirCategoryById(categoryId)
        getZekirsByCategoryIdAndResetZekirCounter(categoryId = categoryId)
    }

    private fun getZekirCategoryById(categoryId: Int) =
        ZekirCategorySingleton.getCategoryById(categoryId)

    fun updateZekirCategory(categoryId: Int, isFavorite: Boolean) {
        zekirCategory.value = zekirCategory.value.copy(isFavorite = isFavorite)
        ZekirCategorySingleton.updateCategory(categoryId, isFavorite)
    }

    private fun getZekirsByCategoryIdAndResetZekirCounter(categoryId: Int) {
        resetZekirCounter()
        zekirs = ZekirSingleton.getZekirsByCategoryId(categoryId = categoryId)
    }

    fun resetZekirCounter() {
        zekirCounter.intValue = 0
    }

    fun isCardButtonEnabled(zekirNumber: Int): Boolean {
        return zekirNumber + 1 < zekirs.size || zekirCounter.intValue < zekirs[zekirNumber].numericCounter
    }

    suspend fun handleFABClick(zekirNumber: Int) =
        incrementZekirCounter(zekirNumber)

    suspend fun handleZekirCardClick(zekirNumber: Int) =
        incrementZekirCounter(zekirNumber)

    private suspend fun incrementZekirCounter(zekirNumber: Int) {
        if (zekirCounter.intValue < zekirs[zekirNumber].numericCounter) {
            zekirCounter.intValue++
            viewModelScope.launch {
                delay(1000)
            }.join()
        }
    }

    fun shouldNavigateToNextZekir(zekirNumber: Int): Boolean {
        return zekirCounter.intValue == zekirs[zekirNumber].numericCounter && zekirNumber < zekirs.size - 1
    }
}