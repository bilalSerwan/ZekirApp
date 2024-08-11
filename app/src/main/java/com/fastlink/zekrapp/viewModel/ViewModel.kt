package com.fastlink.zekrapp.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import categoriesData
import com.fastlink.zekrapp.data.model.CategoryModel
import com.fastlink.zekrapp.data.model.ZekrModel
import getCateories
import getZekrs
import zekrs

class MainViewModel : ViewModel() {
    init {
        getZekrs()
        getCateories()
    }

    private var _categories = mutableStateOf(getAllCategories())

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
        return _categories.value.first { category ->
            category.id == categoryId
        }
    }

    fun getZekirByCategoryId(categoryId: Int): List<ZekrModel> {
        return zekrs.filter { it.categoryId == categoryId }
    }

}


