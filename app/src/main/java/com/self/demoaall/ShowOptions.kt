package com.self.demoaall

import adapterAll.CentralizeOptionsAdapter
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.GridLayoutManager
import callBacksAll.OptionsClickListener
import com.self.demoaall.databinding.ActivityShowOptionsBinding
import controllerAll.utilsAll.AppConstants
import controllerAll.utilsAll.BaseActivity
import examplesAll.viewPagerExamples.activitiesAll.ViewPagerExamples
import examplesAll.adapterExamples.activityAll.AdapterExamples
import examplesAll.bandwidthall.activityAll.BandwidthActivity
import examplesAll.fragmentsExamples.activityAll.FragmentsOptions
import examplesAll.broadcastReceiversExamples.activityAll.BroadcastReceiversOptions
import examplesAll.customClassExamples.activityAll.CustomClassExamples
import examplesAll.dialogExamples.activityAll.DialogFragmentOptions
import examplesAll.drawerExamples.DrawerExamples
import examplesAll.galleryExamples.activityAll.GalleryExamples
import examplesAll.imagesExamples.activityAll.ImagesExamplesOptions
import examplesAll.intentExamples.IntentOptions
import examplesAll.mediaplayerExamples.MediaPlayerExamples
import examplesAll.permissionExamples.activityAll.PermissionsExamples
import examplesAll.qrCodeScanner.QRCodeScanner
import examplesAll.selectUnselectExamples.activitiesAll.SelectUnselectExamples
import examplesAll.servicesExamples.activityAll.ServiceOptions
import examplesAll.threadExamples.activityAll.ThreadOptions
import examplesAll.validationExamples.ValidationsOptions
import java.util.*

class ShowOptions : BaseActivity(), TextWatcher {
    lateinit var viewBinding: ActivityShowOptionsBinding
    var centralizeOptionsAdapter: CentralizeOptionsAdapter? = null
    private val data = ArrayList<HashMap<String, Any>>()
    private var filteredData = ArrayList<HashMap<String, Any>>()
    private var searchStringtxt: String? = null

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityShowOptionsBinding

        setDataByValues()

        centralizeOptionsAdapter = CentralizeOptionsAdapter(this@ShowOptions, AppConstants.showOptions,
                R.layout.adapter_show_options, data, object : OptionsClickListener {
            override fun onClickOptions(type: String, value: String) {
                startNewActivity(value)
            }
        })

        viewBinding.recyclerView.layoutManager = GridLayoutManager(this@ShowOptions, 2)
        viewBinding!!.recyclerView.adapter = centralizeOptionsAdapter

        viewBinding.searchString.addTextChangedListener(this)

        viewBinding.clearData.setOnClickListener {
            viewBinding.searchString.setText("")
            centralizeOptionsAdapter!!.updateData(data)
        }
    }

    private fun setDataByValues() {
        val options = resources.getStringArray(R.array.options)
        for (i in options.indices) {
            val dataGet = HashMap<String, Any>()
            dataGet["option"] = options[i]
            data.add(dataGet)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_show_options
    }

    private fun startNewActivity(activityType: String) {
        val intent: Intent
        when (activityType) {
            AppConstants.VIEWPAGER_EXAMPLS -> {
                intent = Intent(this, ViewPagerExamples::class.java)
                startActivity(intent)
            }

            AppConstants.ADAPTER_EXAMPLS -> {
                intent = Intent(this, AdapterExamples::class.java)
                startActivity(intent)
            }

            AppConstants.SELECT_UNSELECT_EXAMPLS -> {
                intent = Intent(this, SelectUnselectExamples::class.java)
                startActivity(intent)
            }

            AppConstants.BANDWIDTH_EXAMPLS -> {
                intent = Intent(this, BandwidthActivity::class.java)
                startActivity(intent)
            }

            AppConstants.THREAD_EXAMPLES -> {
                intent = Intent(this, ThreadOptions::class.java)
                startActivity(intent)
            }

            AppConstants.MEDIAPLAYER_EXAMPLS -> {
                intent = Intent(this, MediaPlayerExamples::class.java)
                startActivity(intent)
            }

            AppConstants.QR_CODE_SCANNER -> {
                intent = Intent(this, QRCodeScanner::class.java)
                startActivity(intent)
            }

            AppConstants.PERMISSION_EXAMPLS -> {
                intent = Intent(this, PermissionsExamples::class.java)
                startActivity(intent)
            }

            AppConstants.DRAWER_EXAMPLS -> {
                intent = Intent(this, DrawerExamples::class.java)
                startActivity(intent)
            }

            AppConstants.INTENT_EXAMPLS -> {
                intent = Intent(this, IntentOptions::class.java)
                startActivity(intent)
            }

            AppConstants.GALLERY_EXAMPLES -> {
                intent = Intent(this, GalleryExamples::class.java)
                startActivity(intent)
            }

            AppConstants.VALIDATIONS_EXAMPLS -> {
                intent = Intent(this, ValidationsOptions::class.java)
                startActivity(intent)
            }

            AppConstants.DIALOG_FRAGMENTS_EXAMPLS -> {
                intent = Intent(this, DialogFragmentOptions::class.java)
                startActivity(intent)
            }
            AppConstants.FRAGMENTS_EXAMPLS -> {
                intent = Intent(this, FragmentsOptions::class.java)
                startActivity(intent)
            }
            AppConstants.BROADCAST_RECEIVERS_EXAMPLS -> {
                intent = Intent(this, BroadcastReceiversOptions::class.java)
                startActivity(intent)
            }

            AppConstants.SERVICES_EXAMPLES -> {
                intent = Intent(this, ServiceOptions::class.java)
                startActivity(intent)
            }

            AppConstants.IMAGES_EXAMPLES -> {
                intent = Intent(this, ImagesExamplesOptions::class.java)
                startActivity(intent)
            }

            AppConstants.CUSTOM_CLASS_EXAMPLES -> {
                intent = Intent(this, CustomClassExamples::class.java)
                startActivity(intent)
            }

            /*else -> {
                intent = Intent(this, DialogFragmentOptions::class.java)
                startActivity(intent)
            }*/
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
                                for (i in data.indices) {
                                    var text = ""
                                    text = data[i]["option"].toString().toLowerCase()
                                    if (text.contains(searchStringtxt!!)) {
                                        filteredData.add(data[i])
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
                centralizeOptionsAdapter!!.updateData(filteredData)
            } else if (searchtype == "hide") {
                viewBinding.clearData.setImageResource(R.drawable.cross_gray_icon)
                centralizeOptionsAdapter!!.updateData(data)
            }
        }
    }


}

