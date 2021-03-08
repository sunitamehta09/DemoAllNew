package examplesAll.galleryExamples.utilsAll

import java.util.*

class GalleryUtils {
    companion object{
        private var mDataSaved: ArrayList<HashMap<String, Object>>? = null

        fun savedImageArrayData(dataSaved: ArrayList<HashMap<String, Object>>) {
            mDataSaved = dataSaved!!
        }

        fun getImageArrayData(): ArrayList<HashMap<String, Object>> {
            return mDataSaved!!
        }

    }
}