import com.fastlink.zekrapp.data.fakedata.zikerData
import com.fastlink.zekrapp.data.model.ZekrModel

val zekrs = mutableListOf<ZekrModel>()

fun getZekrs(){
    for(i in zikerData){
        zekrs.add(
            ZekrModel(
                id = i[0].toString().toInt(),
                categoryId = i[1].toString().toInt(),
                description = i[2].toString(),
                descriptionAbstract = i[3].toString(),
                hint = i[4].toString(),
                counter = i[5].toString(),
                counterNumber = i[6].toString().toInt()
            )
        )
    }
}