package examplesAll.adapterExamples.activityAll

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivitySelectUnselectOptionsBinding
import controllerAll.utilsAll.AppUtils
import controllerAll.AppUtilsAll.MyLogger
import examplesAll.adapterExamples.UtilsAll.LanguageUtils.Companion.getLanguageData
import examplesAll.adapterExamples.adapterAll.LanguageSelectUnselectAdapter
import examplesAll.adapterExamples.callBacksAll.LanguageClickListener
import examplesAll.adapterExamples.viewModelsAll.LanguageOptionsData
import java.util.*

class SelectUnselectOptions : AppCompatActivity() {
    private val savedLanguageData = ArrayList<String>()
    var languageDataList: ArrayList<LanguageOptionsData>? = null
    private val langDataList = ArrayList<String>()
    private var languageAdapter: LanguageSelectUnselectAdapter? = null
    lateinit var viewBinding: ActivitySelectUnselectOptionsBinding
    private var TAG: String = "SelectUnselectOptions"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_unselect_options)

        languageDataList = getLanguageData(this@SelectUnselectOptions)!!
        for (i in languageDataList!!.indices) {
            if (languageDataList!![i].value != " ") langDataList.add(languageDataList!![i].value)
            if (languageDataList!![i].value == " ") languageDataList!![i].value ="0"
        }
        MyLogger.e(TAG, "languageDataList:"+languageDataList.toString())
        MyLogger.e(TAG, "langDataList:"+langDataList.toString())

        val rvLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        viewBinding!!.rvLanguages.setLayoutManager(rvLayoutManager)
        languageAdapter = LanguageSelectUnselectAdapter(
                this, languageDataList!!, object :LanguageClickListener{
            override fun onLanguageSelectUnselect(position: Int, id: String, name: String) {
                if (id.equals("0")) {
                    if (savedLanguageData.contains("0"))
                        savedLanguageData.clear() else {
                        savedLanguageData.clear()
                        for (i in languageDataList!!.indices) {
                            savedLanguageData.add(languageDataList!!.get(i).value)
                        }
                        MyLogger.e(TAG, "savedLanguageData0: "+savedLanguageData.toString())
                    }
                } else {
                    if (savedLanguageData.contains("0")) savedLanguageData.remove("0")
                    if (savedLanguageData.contains(id)) savedLanguageData.remove(id) else savedLanguageData.add(id)
                    if (savedLanguageData.size == langDataList.size && savedLanguageData.containsAll(langDataList)) {
                        savedLanguageData.clear()
                        for (i in languageDataList!!.indices) {
                            savedLanguageData.add(languageDataList!!.get(i).value)
                        }
                    }
                    MyLogger.e(TAG, "savedLanguageDataelse0: "+savedLanguageData.toString())

                }
                languageAdapter!!.notifyDataSetChanged()
            }

        }, savedLanguageData)
        viewBinding.rvLanguages.setAdapter(languageAdapter)


        viewBinding.rlDone.setOnClickListener {
            AppUtils.showToast(this, "Selected languages: "+savedLanguageData.toString())
        }

    }
}