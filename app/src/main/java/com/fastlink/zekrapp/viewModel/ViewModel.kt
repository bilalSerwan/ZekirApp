package com.fastlink.zekrapp.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fastlink.zekrapp.appData.data.categoriesData
import com.fastlink.zekrapp.appData.model.CategoryModel
import com.fastlink.zekrapp.appData.model.ZekrModel
import com.fastlink.zekrapp.appData.data.getCateories
import com.fastlink.zekrapp.appData.data.getZekrs
import com.fastlink.zekrapp.appData.data.zekrs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    init {
        getZekrs()
        getCateories()
    }

    private var _categories = mutableStateOf(getAllCategories())
    val zekirNumber = mutableIntStateOf(0)
    val zekirCounter = mutableIntStateOf(0)
    var zekirs :  List<ZekrModel> = emptyList()



    fun onClicked(scope : CoroutineScope) {
        if (zekirCounter.intValue < zekirs[zekirNumber.intValue].counterNumber) {
            zekirCounter.intValue++
            if (zekirCounter.intValue == zekirs[zekirNumber.intValue].counterNumber) {
                scope.launch {
                    delay(1500)
                    if (zekirNumber.intValue < zekirs.size - 1) {
                        zekirCounter.intValue = 0
                        zekirNumber.intValue++
//                        scope.launch {
//                            toast(" الذكر ${zekirNumber.intValue + 1} من ${zekirs.size}")
//                        }
                    }
                }
            }
        }
    }


    val categories: State<List<CategoryModel>>
        get() = _categories

    private fun getAllCategories(): List<CategoryModel> {
        return categoriesData
    }

    fun getFavoriteCategories(): List<CategoryModel> {
        return _categories.value.filter { it.isFavorite }
    }

    fun updateCategory(categoryId: Int, isFavorite: Boolean) {
        _categories.value = _categories.value.map { category ->
            if (category.id == categoryId) {
                category.copy(isFavorite = isFavorite)
            } else {
                category
            }
        }
    }


    fun getCategoryById(categoryId: Int): CategoryModel {
        Log.d("TAG", "getategoryId: $categoryId")
        return _categories.value.first { category ->
            category.id == categoryId
        }
    }

    fun getZekirByCategoryId(categoryId: Int){
        Log.d("TAG", "getZekirByCategoryId: $categoryId")
        zekirs =  zekrs.filter { it.categoryId == categoryId }

    }

}


