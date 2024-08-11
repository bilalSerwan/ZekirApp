import com.fastlink.zekrapp.data.data.categoryData
import com.fastlink.zekrapp.data.model.CategoryModel

val categoriesData = mutableListOf<CategoryModel>()


fun getCateories(){
    for(i in categoryData){
        categoriesData.add(
            CategoryModel(
                id = i[0].toString().toInt(),
                name = i[1].toString(),
                nameAbstract = i[2].toString()
            )
        )
    }
}
