package com.fastlink.zekrapp.viewModel

import androidx.lifecycle.ViewModel
import com.fastlink.zekrapp.appData.ZekirCategorySingleton
import com.fastlink.zekrapp.appData.model.ZekirCategoryModel

class ZekirCategoryViewModel : ViewModel() {
    fun getAllZekirCategories(): List<ZekirCategoryModel> =
        ZekirCategorySingleton.zekirCategoriesData.value

    fun getFavoriteCategories(): List<ZekirCategoryModel> =
        ZekirCategorySingleton.getFavoriteCategories()

    fun updateCategory(categoryId: Int, isFavorite: Boolean) =
        ZekirCategorySingleton.updateCategory(categoryId, isFavorite)

    fun getCategoryById(categoryId: Int) = ZekirCategorySingleton.getCategoryById(categoryId)
}


