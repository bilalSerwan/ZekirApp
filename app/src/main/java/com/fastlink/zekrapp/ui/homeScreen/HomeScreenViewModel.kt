package com.fastlink.zekrapp.ui.homeScreen

import android.content.Context
import androidx.lifecycle.ViewModel
import com.fastlink.zekrapp.di.ZekirCategorySingleton
import com.fastlink.zekrapp.appData.model.ZekirCategoryModel
import com.fastlink.zekrapp.ui.utils.ZekirCategoryViewModelInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val zekirCategorySingleton: ZekirCategorySingleton,
    @ApplicationContext private val context: Context
) : ViewModel(), ZekirCategoryViewModelInterface {

    init {
        if(zekirCategorySingleton.zekirCategoriesData.value.isEmpty())
            zekirCategorySingleton.getZekirCategoriesFromCSVFile(context)
    }
    fun getAllZekirCategories(): List<ZekirCategoryModel> =
        zekirCategorySingleton.zekirCategoriesData.value

    override  fun updateZekirCategory(categoryId: Int, isFavorite: Boolean) =
        zekirCategorySingleton.updateCategory(categoryId, isFavorite)
}