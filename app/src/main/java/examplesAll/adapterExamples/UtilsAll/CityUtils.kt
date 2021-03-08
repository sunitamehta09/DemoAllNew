package examplesAll.adapterExamples.UtilsAll

import android.content.Context
import controllerAll.AppUtilsAll.MyLogger
import examplesAll.adapterExamples.viewModelsAll.State
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class CityUtils {
    companion object {
        @JvmStatic
        fun getStatesData(context: Context): ArrayList<State>?  {
            val stateList = ArrayList<State>()
            try {
                val inputStream = context.resources.assets.open("citySelection.txt")
                val size = inputStream.available()
                val byteArray = ByteArray(size)
                inputStream.read(byteArray)
                val jsonObject = JSONObject(String(byteArray))
                val jsonArray = jsonObject.getJSONArray("data_states")
                inputStream.close()
                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getJSONObject(i)
                    val state = State()
                    state.country = item.getString("state_country_id")
                    state.name = item.getString("state_name")
                    state.id = item.getString("state_id")
                    state.is_Top = item.getString("is_top_state")
                    state.is_check = item.getString("is_check")
                    stateList.add(state)
                }
            } catch (jsonException: JSONException) {
                jsonException.printStackTrace()
            } catch (io: IOException) {
                io.printStackTrace()
            }
            MyLogger.e("CitySelectionActivity", "getStates size:" + stateList.size)
            return stateList
        }



    }
}