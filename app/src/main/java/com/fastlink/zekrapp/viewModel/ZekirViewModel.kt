package com.fastlink.zekrapp.viewModel

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fastlink.zekrapp.appData.ZekirSingleton
import com.fastlink.zekrapp.appData.model.ZekirModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZekirViewModel @Inject constructor(
    private val zekirSingleton: ZekirSingleton
) : ViewModel() {
    private var categoryId: Int = 0
    val zekirCounter = mutableIntStateOf(0)
    var zekirs: List<ZekirModel> = emptyList()
        private set

    fun setCategoryIdAndGetZekirs(categoryId: Int) {
        this.categoryId = categoryId
        getZekirsByCategoryIdAndResetZekirCounter(categoryId)
    }

    private fun getZekirsByCategoryIdAndResetZekirCounter(categoryId: Int) {
        resetZekirCounter()
        zekirs = zekirSingleton.getZekirsByCategoryId(categoryId = categoryId)
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
        return zekirCounter.intValue == zekirs[zekirNumber].numericCounter || zekirNumber < zekirs.size - 1
    }
}