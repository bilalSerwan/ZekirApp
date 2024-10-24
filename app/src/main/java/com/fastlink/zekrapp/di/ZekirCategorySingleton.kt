package com.fastlink.zekrapp.di

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.fastlink.zekrapp.appData.model.ZekirCategoryModel
import java.io.BufferedReader
import java.io.InputStreamReader


object ZekirCategorySingleton {
    private val _zekirCategoriesData = mutableStateOf<List<ZekirCategoryModel>>(emptyList())
    val zekirCategoriesData: State<List<ZekirCategoryModel>>
        get() = _zekirCategoriesData

    fun getCategoryById(categoryId: Int) = _zekirCategoriesData.value.first { it.id == categoryId }

    fun getFavoriteCategories(): List<ZekirCategoryModel> =
        _zekirCategoriesData.value.filter { it.isFavorite }

    fun updateCategory(categoryId: Int, isFavorite: Boolean) {
        _zekirCategoriesData.value = _zekirCategoriesData.value.map { category ->
            if (category.id == categoryId) {
                category.copy(isFavorite = isFavorite)
            } else {
                category
            }
        }
    }

    fun getZekirCategoriesFromCSVFile(context:Context) {
        val inputStream = context.assets.open("ziker_category_data.csv")
        val reader = BufferedReader(InputStreamReader(inputStream))
        val categories = mutableListOf<ZekirCategoryModel>()
        reader.use {
            it.forEachLine { line ->
                val tokens = line.split(",")
                if (tokens.size >= 3) {
                    categories.add(
                        ZekirCategoryModel(
                            id = tokens[0].toInt(),
                            zekirCategoryTitle = tokens[1],
                            plainZekirCategoryTitle = tokens[2]
                        )
                    )
                }
            }
        }
        _zekirCategoriesData.value = categories
    }

}
