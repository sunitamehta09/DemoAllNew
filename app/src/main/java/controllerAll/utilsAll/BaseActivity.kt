package controllerAll.utilsAll

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.self.demoaall.R

abstract class BaseActivity : AppCompatActivity(){
    internal var viewBaseBinding: ViewDataBinding? = null   // internal modifier used to visible to module.
    val currentApiVersion = Build.VERSION.SDK_INT
    var savedInstanceState: Bundle ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.savedInstanceState = savedInstanceState
        viewBaseBinding = DataBindingUtil.setContentView(this, getLayoutId())

        if(currentApiVersion >= Build.VERSION_CODES.LOLLIPOP){
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.navigationBarColor = ContextCompat.getColor(this,R.color.colorPrimaryDark)
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }

        initView()
    }

    abstract fun initView()

    abstract fun getLayoutId(): Int
}