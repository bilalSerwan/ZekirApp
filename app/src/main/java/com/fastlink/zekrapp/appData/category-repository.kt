package com.fastlink.zekrapp.appData

import android.content.Context
import com.fastlink.zekrapp.appData.model.ZekirCategoryModel
import java.io.BufferedReader
import java.io.InputStreamReader

object CategoryRepository{
    private val _zekirCategoriesData = mutableListOf<ZekirCategoryModel>()
    val zekirCategoriesData: List<ZekirCategoryModel>
        get() = _zekirCategoriesData

    fun getZekirCategoriesFromCSVFile(context: Context){
        val inputStream = context.assets.open("ziker_category_data.csv")
        val reader = BufferedReader(InputStreamReader(inputStream))
        reader.use {
            it.forEachLine { line ->
                val tokens = line.split(",")
                if (tokens.size >= 3) {
                    _zekirCategoriesData.add(
                        ZekirCategoryModel(
                            id = tokens[0].toInt(),
                            categoryTitle = tokens[1],
                            plainCategoryTitle = tokens[2]
                        )
                    )
                }
            }
        }
    }
}