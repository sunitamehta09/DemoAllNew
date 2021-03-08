package examplesAll.galleryExamples.adapterAll

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import callBacksAll.RecyclerViewCallBack
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions
import com.self.demoaall.R
import com.self.demoaall.databinding.AdapterlayoutCustomgalleryBinding
import com.self.demoaall.databinding.LayoutAdapterPostImagesBinding
import java.util.*

class GalleryAdapter(private val context: Context, private val type: String, private val layout: Int,
                     private var data: ArrayList<HashMap<String, Any>>, private val recycleListener: RecyclerViewCallBack) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var getDataSub: HashMap<String, Any>? = null
    private var getData: ArrayList<HashMap<String, Any>>? = null

    init {
        getData = data
    }

    inner class AdapterCustomGalleryViewHolder(var customgalleryBinding: AdapterlayoutCustomgalleryBinding) :
            RecyclerView.ViewHolder(customgalleryBinding.root)

    inner class AdapterLayoutPostImagesViewHolder(var postImagesBinding: LayoutAdapterPostImagesBinding) :
            RecyclerView.ViewHolder(postImagesBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (type.equals(context.getString(R.string.custom_gallery_multiple_selection))) {
            val customgalleryBinding = DataBindingUtil.inflate<AdapterlayoutCustomgalleryBinding>(
                    LayoutInflater.from(context),
                    layout, parent, false
            )
            return AdapterCustomGalleryViewHolder(customgalleryBinding)
        } else if (type.equals(context.getString(R.string.custom_gallery_single_selection))) {
            val customgalleryBinding = DataBindingUtil.inflate<AdapterlayoutCustomgalleryBinding>(
                    LayoutInflater.from(context),
                    layout, parent, false
            )
            return AdapterCustomGalleryViewHolder(customgalleryBinding)
        } else {
            val postImagesBinding = DataBindingUtil.inflate<LayoutAdapterPostImagesBinding>(
                    LayoutInflater.from(context),
                    layout, parent, false
            )
            return AdapterLayoutPostImagesViewHolder(postImagesBinding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getDataSub = getData!!.get(position)

        if (type.equals(context.getString(R.string.custom_gallery_multiple_selection))) {
            val galleryHolder = holder as AdapterCustomGalleryViewHolder
            Glide.with(context).load(getDataSub!!["path"].toString()).into(galleryHolder.customgalleryBinding.galleryImage)

            if (getDataSub!!["isSelected"].toString() == "false") {
                galleryHolder.customgalleryBinding.galleryImageSelected.setVisibility(View.GONE)
            } else if (getDataSub!!["isSelected"].toString() == "true") {
                galleryHolder.customgalleryBinding.galleryImageSelected.setVisibility(View.VISIBLE)
            }
            galleryHolder.customgalleryBinding.layoutGalleryImage.setOnClickListener(View.OnClickListener {
                getDataSub = getData!![position]
                val selectPath = getDataSub!!["path"].toString()
                val isSelected = getDataSub!!["isSelected"].toString()
                recycleListener.recycleCallBack("selectImage", position, getData, selectPath, isSelected)
            })
        } else if (type.equals(context.getString(R.string.custom_gallery_single_selection))) {
            val galleryHolder = holder as AdapterCustomGalleryViewHolder
            Glide.with(context).load(getDataSub!!["path"].toString()).into(galleryHolder.customgalleryBinding.galleryImage)

            if (getDataSub!!["isSelected"].toString() == "false") {
                galleryHolder.customgalleryBinding.galleryImageSelected.setVisibility(View.GONE)
            } else if (getDataSub!!["isSelected"].toString() == "true") {
                galleryHolder.customgalleryBinding.galleryImageSelected.setVisibility(View.VISIBLE)
            }
            galleryHolder.customgalleryBinding.layoutGalleryImage.setOnClickListener(View.OnClickListener {
                getDataSub = getData!![position]
                val selectPath = getDataSub!!["path"].toString()
                val isSelected = getDataSub!!["isSelected"].toString()
                recycleListener.recycleCallBack("selectImage", position, getData, selectPath, isSelected)
            })
        } else if (type.equals(context.getString(R.string.post_images))) {
            val imagesHolder = holder as AdapterLayoutPostImagesViewHolder
            if (getDataSub!!["image"].toString() == "no image") {
                imagesHolder.postImagesBinding.layoutViewAd.setVisibility(View.GONE)
                imagesHolder.postImagesBinding.layoutUploadAd.setVisibility(View.VISIBLE)
            } else {
                imagesHolder.postImagesBinding.layoutViewAd.setVisibility(View.VISIBLE)
                imagesHolder.postImagesBinding.layoutUploadAd.setVisibility(View.GONE)
                RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE).skipMemoryCache(false).dontAnimate().downsample(DownsampleStrategy.AT_MOST)
                Glide.with(context).load(getDataSub!!["image"].toString()).apply(RequestOptions()).into(holder.postImagesBinding.viewImage)
            }

            imagesHolder.postImagesBinding.layoutUploadAd.setOnClickListener(View.OnClickListener {
                getDataSub = getData!![position]
                recycleListener.recycleCallBack(context.getString(R.string.upload_image), position)
            })

            imagesHolder.postImagesBinding.viewImage.setOnClickListener(View.OnClickListener {
                getDataSub = getData!![position]
                /*var intent = Intent(context, ShowFullScreenView::class.java)
                intent.putExtra("imageUrl", getDataSub!!["image"].toString())
                context.startActivity(intent)*/
            })

            imagesHolder.postImagesBinding.deleteAd.setOnClickListener(View.OnClickListener {
                getDataSub = getData!![position]
                if (data.size > 0) {
                    data.removeAt(position)
                    notifyDataSetChanged()
                    recycleListener.recycleCallBack(context.getString(R.string.delete_image), position, data.size)
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return getData!!.size
    }

    fun updateData(data: ArrayList<HashMap<String, Any>>) {
        getData = data
        notifyDataSetChanged()
    }

}