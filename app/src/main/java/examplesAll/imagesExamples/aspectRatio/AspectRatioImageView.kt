package examplesAll.imagesExamples.activityAll.aspectRatio

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.self.demoaall.R

class AspectRatioImageView: AppCompatImageView {

    constructor(context: Context) : super(context){
    }

    constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr){
        attrs?.let {
            context.obtainStyledAttributes(it, R.styleable.AspectRatioImageView).apply {
                ratio = getFloat(R.styleable.AspectRatioImageView_ari_ratio, DEFAULT_RATIO)
                recycle()
            }
        }
    }


    var ratio: Float = DEFAULT_RATIO


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var width = measuredWidth
        var height = measuredHeight

        when {
            width > 0 -> height = (width * ratio).toInt()
            height > 0 -> width = (height / ratio).toInt()
            else -> return
        }

        setMeasuredDimension(width, height)
    }

    companion object {
        const val DEFAULT_RATIO = 0.75F
    }
}
