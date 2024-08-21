package com.fastlink.zekrapp.appData

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.fastlink.zekrapp.appData.model.ZekirCategoryModel

object  ZekirCategorySingleton{
    private val _zekirCategoriesData = mutableStateOf(CategoryRepository.zekirCategoriesData)
    val zekirCategoriesData: State<List<ZekirCategoryModel>>
        get() = _zekirCategoriesData
    fun getCategoryById(categoryId:Int)= _zekirCategoriesData.value.first{it.id ==categoryId}

    fun getFavoriteCategories(): List<ZekirCategoryModel> = _zekirCategoriesData.value.filter { it.isFavorite }

    fun updateCategory(categoryId: Int, isFavorite: Boolean) {
        _zekirCategoriesData.value = _zekirCategoriesData.value.map { category ->
            if (category.id == categoryId) {
                category.copy(isFavorite = isFavorite)
            } else {
                category
            }
        }
    }

}
