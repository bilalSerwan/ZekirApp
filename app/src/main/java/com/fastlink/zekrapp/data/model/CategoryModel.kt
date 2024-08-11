package com.fastlink.zekrapp.data.model

//@Entity(tableName = "zekr_category_table")
data class CategoryModel(
//    @PrimaryKey(autoGenerate = true)
    val id: Int,
//    @ColumnInfo(name = "name")
    val name: String,
//    val zekrs: List<Zekr>,
//    @ColumnInfo(name = "name_abstract")
    val nameAbstract:String,
//    @ColumnInfo(name="bookmarked")
    var isFavorite : Boolean =false
)


