package com.fastlink.zekrapp.viewModel

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import com.fastlink.zekrapp.appData.ZekirSingleton
import com.fastlink.zekrapp.appData.model.ZekirModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ZekirViewModel(categoryId: Int) : ViewModel() {
    val zekirCounter = mutableIntStateOf(0)
    var zekirs: List<ZekirModel> = emptyList()

    init {
        getZekirByCategoryId(categoryId)
    }

    private fun getZekirByCategoryId(categoryId: Int) {
        resetZekirCounter()
        zekirs = ZekirSingleton.getZekirsByCategoryId(categoryId = categoryId)
    }

    fun resetZekirCounter() {
        zekirCounter.intValue = 0
    }

    fun cardButtonIsEnable(zekirNumber: Int): Boolean {
        return zekirNumber + 1 < zekirs.size || zekirCounter.intValue < zekirs[zekirNumber].numericCounter
    }

    suspend fun handleFABClick(scope: CoroutineScope, zekirNumber: Int) =
        incrementZekirCounter(scope, zekirNumber)

    suspend fun handleZekirCardClick(scope: CoroutineScope, zekirNumber: Int) =
        incrementZekirCounter(scope, zekirNumber)

    private suspend fun incrementZekirCounter(scope: CoroutineScope, zekirNumber: Int) {
        if (zekirCounter.intValue < zekirs[zekirNumber].numericCounter) {
            zekirCounter.intValue++
            scope.launch {
                delay(1000)
            }.join()
        }
    }

    fun shouldNavigateToNextZekir(zekirNumber: Int): Boolean {
        return zekirCounter.intValue == zekirs[zekirNumber].numericCounter || zekirNumber < zekirs.size - 1
    }
}