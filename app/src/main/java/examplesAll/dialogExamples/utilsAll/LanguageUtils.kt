package examplesAll.dialogExamples.utilsAll

import android.content.Context
import examplesAll.dialogExamples.viewModelsAll.StatesOptionData
import examplesAll.dialogExamples.viewModelsAll.LanguageOptionsData
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class LanguageUtils {

    companion object {

        @JvmStatic
        fun getLanguageData(context: Context): ArrayList<LanguageOptionsData>?  {
            val languageModelArrayList = ArrayList<LanguageOptionsData>()
            try {
                val inputStream = context.resources.assets.open("languageData.txt")
                val size = inputStream.available()
                val byteArray = ByteArray(size)
                inputStream.read(byteArray)
                val jsonObject = JSONObject(String(byteArray))
                val jsonArray = jsonObject.getJSONArray("data_lang")
                inputStream.close()
                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getJSONObject(i)
                    val languageModel = LanguageOptionsData()
                    languageModel.value = item.getString("value")
                    languageModel.code = item.getString("code")
                    languageModel.label = item.getString("label")
                    languageModel.select = item.getString("select")
                    languageModelArrayList.add(languageModel)
                }
            } catch (jsonException: JSONException) {
                jsonException.printStackTrace()
            } catch (io: IOException) {
                io.printStackTrace()
            }
            return languageModelArrayList
        }

        @JvmStatic
        fun getStatesData(context: Context): ArrayList<StatesOptionData>?  {
            val statesArrayList = ArrayList<StatesOptionData>()
            try {
                val inputStream = context.resources.assets.open("statesData.txt")
                val size = inputStream.available()
                val byteArray = ByteArray(size)
                inputStream.read(byteArray)
                val jsonObject = JSONObject(String(byteArray))
                val jsonArray = jsonObject.getJSONArray("data_states")
                inputStream.close()
                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getJSONObject(i)
                    val states = StatesOptionData()
                    states.value = item.getString("value")
                    states.code = item.getString("code")
                    states.label = item.getString("label")
                    states.select = item.getString("select")
                    statesArrayList.add(states)
                }
            } catch (jsonException: JSONException) {
                jsonException.printStackTrace()
            } catch (io: IOException) {
                io.printStackTrace()
            }
            return statesArrayList
        }
    }
}