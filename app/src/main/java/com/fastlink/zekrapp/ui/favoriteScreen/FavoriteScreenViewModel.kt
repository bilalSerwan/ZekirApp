package com.fastlink.zekrapp.ui.favoriteScreen

import androidx.lifecycle.ViewModel
import com.fastlink.zekrapp.di.ZekirCategorySingleton
import com.fastlink.zekrapp.appData.model.ZekirCategoryModel
import com.fastlink.zekrapp.ui.utils.ZekirCategoryViewModelInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(
    private val zekirCategorySingleton: ZekirCategorySingleton,
) : ViewModel(), ZekirCategoryViewModelInterface {

    fun getFavoriteZekirCategories(): List<ZekirCategoryModel> =
        zekirCategorySingleton.getFavoriteCategories()

    override fun updateZekirCategory(categoryId: Int, isFavorite: Boolean) =
        zekirCategorySingleton.updateCategory(categoryId, isFavorite)
}