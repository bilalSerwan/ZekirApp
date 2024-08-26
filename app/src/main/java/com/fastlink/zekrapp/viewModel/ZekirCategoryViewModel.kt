package com.fastlink.zekrapp.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.fastlink.zekrapp.appData.ZekirCategorySingleton
import com.fastlink.zekrapp.appData.model.ZekirCategoryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ZekirCategoryViewModel @Inject constructor(
    private val zekirCategorySingleton: ZekirCategorySingleton,
    @ApplicationContext private val context: Context
) : ViewModel() {

    init {
        zekirCategorySingleton.getZekirCategoriesFromCSVFile(context)
    }

    fun getAllZekirCategories(): List<ZekirCategoryModel> =
        zekirCategorySingleton.zekirCategoriesData.value

    fun getFavoriteZekirCategories(): List<ZekirCategoryModel> =
        zekirCategorySingleton.getFavoriteCategories()

    fun updateZekirCategory(categoryId: Int, isFavorite: Boolean) =
        zekirCategorySingleton.updateCategory(categoryId, isFavorite)

    fun getZekirCategoryById(categoryId: Int) =
        zekirCategorySingleton.getCategoryById(categoryId)
}


