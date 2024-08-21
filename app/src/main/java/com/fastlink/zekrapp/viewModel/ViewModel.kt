package com.fastlink.zekrapp.viewModel

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import com.fastlink.zekrapp.appData.ZekirCategorySingleton
import com.fastlink.zekrapp.appData.ZekirSingleton
import com.fastlink.zekrapp.appData.model.ZekirCategoryModel
import com.fastlink.zekrapp.appData.model.ZekirModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val zekirNumber = mutableIntStateOf(0)
    val zekirCounter = mutableIntStateOf(0)
    var zekirs :  List<ZekirModel> = emptyList()

    fun getAllZekirCategories(): List<ZekirCategoryModel> = ZekirCategorySingleton.zekirCategoriesData.value

    fun getFavoriteCategories(): List<ZekirCategoryModel> =ZekirCategorySingleton.getFavoriteCategories()


    fun updateCategory(categoryId: Int, isFavorite: Boolean) = ZekirCategorySingleton.updateCategory(categoryId, isFavorite)

    fun getCategoryById(categoryId: Int): ZekirCategoryModel = ZekirCategorySingleton.getCategoryById(categoryId)

     fun getZekirByCategoryId(categoryId: Int){
         zekirNumber.intValue=0
         zekirCounter.intValue=0
        zekirs =  ZekirSingleton.getZekirsByCategoryId(categoryId=categoryId)
    }

    fun onClickedFAB(scope : CoroutineScope) {
        if (zekirCounter.intValue < zekirs[zekirNumber.intValue].numericCounter) {
            zekirCounter.intValue++
            if (zekirCounter.intValue == zekirs[zekirNumber.intValue].numericCounter) {
                scope.launch {
                    delay(1500)
                    if (zekirNumber.intValue < zekirs.size - 1) {
                        zekirCounter.intValue = 0
                        zekirNumber.intValue++
                    }
                }
            }
        }
    }
    fun onClickedZekirCard(scope : CoroutineScope) {
        if (zekirCounter.intValue < zekirs[zekirNumber.intValue].numericCounter) {
            zekirCounter.intValue++
            if (zekirCounter.intValue == zekirs[zekirNumber.intValue].numericCounter) {
                scope.launch {
                    delay(1500)
                    if (zekirNumber.intValue  < zekirs.size -1) {
                        zekirCounter.intValue = 0
                        zekirNumber.intValue++
                    }
                }
            }
        }
    }
}


