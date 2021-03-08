package examplesAll.servicesExamples.activityAll

import android.content.Intent
import com.self.demoaall.R
import com.self.demoaall.databinding.ActivityPlaySongBinding
import controllerAll.utilsAll.BaseActivity
import examplesAll.servicesExamples.ServiceClasses.PlaySongService

class PlaySong : BaseActivity() {
    lateinit var viewBinding: ActivityPlaySongBinding

    override fun initView() {
        viewBinding = viewBaseBinding as ActivityPlaySongBinding

        viewBinding.playSong.setOnClickListener {
            startService(Intent(this, PlaySongService::class.java))
        }

        viewBinding.stopSong.setOnClickListener {
            stopService(Intent(this, PlaySongService::class.java))
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_play_song
    }
}