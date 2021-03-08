package examplesAll.dialogExamples.activityAll.EditorJi.utilsAll

import android.content.Context
import controllerAll.AppUtilsAll.MyLogger
import examplesAll.dialogExamples.activityAll.EditorJi.viewModelsAll.InitialData
import examplesAll.dialogExamples.activityAll.EditorJi.viewModelsAll.SecondaryData
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class ReportUtils {
    companion object {
        @JvmStatic
        fun getInitialData(context: Context): ArrayList<InitialData>? {
            val initialDataArray = ArrayList<InitialData>()
            try {
                val inputStream = context.resources.assets.open("report.txt")
                val size = inputStream.available()
                val byteArray = ByteArray(size)
                inputStream.read(byteArray)
                val jsonObject = JSONObject(String(byteArray))
                val data = jsonObject.getJSONObject("data")
                val jsonArray = data.getJSONArray("initial")
                inputStream.close()
                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getJSONObject(i)
                    val initialData = InitialData()
                    initialData.id = item.getInt("id")
                    initialData.showSecondary = item.getBoolean("showSecondary")
                    initialData.reportText = item.getString("reportText")
                    initialDataArray.add(initialData)
                }
            } catch (jsonException: JSONException) {
                jsonException.printStackTrace()
            } catch (io: IOException) {
                io.printStackTrace()
            }
            return initialDataArray
        }

        @JvmStatic
        fun getSecondaryData(context: Context): ArrayList<SecondaryData>? {
            val secondaryDataArray = ArrayList<SecondaryData>()
            try {
                val inputStream = context.resources.assets.open("report.txt")
                val size = inputStream.available()
                val byteArray = ByteArray(size)
                inputStream.read(byteArray)
                val jsonObject = JSONObject(String(byteArray))
                val data = jsonObject.getJSONObject("data")
                val jsonArray = data.getJSONArray("secondary")
                inputStream.close()
                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getJSONObject(i)
                    val secondaryData = SecondaryData()
                    secondaryData.id = item.getInt("id")
                    secondaryData.showSecondary = item.getBoolean("showSecondary")
                    secondaryData.reportText = item.getString("reportText")
                    secondaryDataArray.add(secondaryData)
                }
            } catch (jsonException: JSONException) {
                jsonException.printStackTrace()
            } catch (io: IOException) {
                io.printStackTrace()
            }
            return secondaryDataArray
        }
    }
}