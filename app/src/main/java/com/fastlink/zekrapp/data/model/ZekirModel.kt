package com.fastlink.zekrapp.data.model

//@Entity(tableName = "zekr_table")
data class ZekrModel(
//    @PrimaryKey(autoGenerate = true)
    val id: Int,
//    @ColumnInfo(name = "category_id")
    val categoryId: Int,
//    @ColumnInfo(name="description")
    val description: String,
//    @ColumnInfo(name="descriptionAbstract")
    val descriptionAbstract: String,
//    @ColumnInfo(name="hint")
    val hint: String,

    val counter: String,
//    @ColumnInfo(name="counter_number")
    val counterNumber: Int
)