package com.fastlink.zekrapp.ui.homeScreen

import android.content.Context
import androidx.lifecycle.ViewModel
import com.fastlink.zekrapp.di.ZekirCategorySingleton
import com.fastlink.zekrapp.appData.model.ZekirCategoryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {
    init {
        if(ZekirCategorySingleton.zekirCategoriesData.value.isEmpty())
            ZekirCategorySingleton.getZekirCategoriesFromCSVFile(context)
    }
    fun getAllZekirCategories(): List<ZekirCategoryModel> =
        ZekirCategorySingleton.zekirCategoriesData.value

    fun updateZekirCategory(categoryId: Int, isFavorite: Boolean) =
        ZekirCategorySingleton.updateCategory(categoryId, isFavorite)
}