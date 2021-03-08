package examplesAll.mediaplayerExamples

import adapterAll.CentralizeOptionsAdapter
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import callBacksAll.OptionsClickListener
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivityShowOptionsBinding
import controllerAll.utilsAll.AppConstants
import controllerAll.utilsAll.BaseActivity
import controllerAll.utilsAll.SetDataAll
import java.util.*

class MediaPlayerExamples : BaseActivity(), TextWatcher {
    lateinit var viewBinding: ActivityShowOptionsBinding
    var centralizeOptionsAdapter: CentralizeOptionsAdapter? = null
    private var data: ArrayList<HashMap<String, Any>>? = null
    private var filteredData: ArrayList<HashMap<String, Any>>? = null
    private var searchStringtxt: String? = null

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityShowOptionsBinding
        viewBinding.layoutSearch.visibility = View.GONE

        data = SetDataAll.getOptionsAll(this, AppConstants.MEDIAPLAYER_EXAMPLS)

        centralizeOptionsAdapter = CentralizeOptionsAdapter(this@MediaPlayerExamples, AppConstants.showSecondaryOptions,
                R.layout.adapter_secondary_options, data!!, object : OptionsClickListener {
            override fun onClickOptions(type: String, value: String) {
                startNewActivity(value)
            }
        })

        viewBinding.recyclerView.layoutManager = GridLayoutManager(this@MediaPlayerExamples, 3)
        viewBinding!!.recyclerView.adapter = centralizeOptionsAdapter

        viewBinding.searchString.addTextChangedListener(this)

        viewBinding.clearData.setOnClickListener {
            viewBinding.searchString.setText("")
            centralizeOptionsAdapter!!.updateData(data!!)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_show_options
    }

    private fun startNewActivity(activityType: String) {
        val intent: Intent
        when (activityType) {
            AppConstants.PLAY_SONG_WITH_SEEKBAR_DURATION -> {
                intent = Intent(this, PlaySongWithSeekBarAndDuration::class.java)
                startActivity(intent)
            }
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        if (s.hashCode() == viewBinding.searchString.text.hashCode()) {
            if (s!!.length > 1) {
                val timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        if (s!!.length > 1) {
                            searchStringtxt = viewBinding.searchString.text.toString().toLowerCase()
                            if (searchStringtxt != null) {
                                filteredData = ArrayList<HashMap<String, Any>>()
                                for (i in data!!.indices) {
                                    var text = ""
                                    text = data!![i]["option"].toString().toLowerCase()
                                    if (text.contains(searchStringtxt!!)) {
                                        filteredData!!.add(data!![i])
                                    }
                                }
                                setfilterData("show")
                            } else {
                                setfilterData("hide")
                            }
                        } else {
                            setfilterData("hide")
                        }
                    }
                }, 600)
            } else {
                setfilterData("hide")
            }
            if (viewBinding.searchString.length() > 0) {
            } else if (viewBinding.searchString.length() <= 0) {
                viewBinding.clearData.setImageResource(R.drawable.cross_gray_icon)
            }
        }
    }

    private fun setfilterData(searchtype: String) {
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            if (searchtype == "show") {
                viewBinding.clearData.setImageResource(R.drawable.cross_red_icon)
                centralizeOptionsAdapter!!.updateData(filteredData!!)
            } else if (searchtype == "hide") {
                viewBinding.clearData.setImageResource(R.drawable.cross_gray_icon)
                centralizeOptionsAdapter!!.updateData(data!!)
            }
        }
    }
}

