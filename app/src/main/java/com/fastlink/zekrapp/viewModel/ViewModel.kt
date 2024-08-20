package com.fastlink.zekrapp.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fastlink.zekrapp.appData.ZekirCategorySingleton
import com.fastlink.zekrapp.appData.ZekirSingleton
import com.fastlink.zekrapp.appData.model.ZekirCategoryModel
import com.fastlink.zekrapp.appData.model.ZekirModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var _zekirCategories = mutableStateOf(ZekirCategorySingleton.zekirCategoriesData)
    val zekirNumber = mutableIntStateOf(0)
    val zekirCounter = mutableIntStateOf(0)
    var zekirs :  List<ZekirModel> = emptyList()

    val zekirCategories: State<List<ZekirCategoryModel>>
        get() = _zekirCategories

    fun getFavoriteCategories(): List<ZekirCategoryModel> = _zekirCategories.value.filter { it.isFavorite }


    fun updateCategory(categoryId: Int, isFavorite: Boolean) {
        _zekirCategories.value = _zekirCategories.value.map { category ->
            if (category.id == categoryId) {
                category.copy(isFavorite = isFavorite)
            } else {
                category
            }
        }
    }

    fun getCategoryById(categoryId: Int): ZekirCategoryModel {
        return _zekirCategories.value.first { category ->
            category.id == categoryId
        }
    }

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


