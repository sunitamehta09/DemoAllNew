package controllerAll.utilsAll

import android.content.Context

public class SetDataAll {
    companion object {

        fun getOptionsAll(context: Context, value: String): ArrayList<HashMap<String, Any>>? {
            var data: ArrayList<HashMap<String, Any>> = ArrayList()
            var dataGet: HashMap<String, Any>? = null
            when (value) {
                AppConstants.SELECT_UNSELECT_EXAMPLS -> {
                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.BUTTON_SELECT_UNSELECT)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.RADIOBUTTON_IN_RADIOGROUP_SELECT_UNSELECT)
                    data!!.add(dataGet)

                    return data
                }

                AppConstants.GALLERY_EXAMPLES -> {
                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.PHONE_GALLERY)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.CUSTOM_GALLERY_MULTIPLE_SELECTION)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.CUSTOM_GALLERY_SINGLE_SELECTION)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.GALLERY_IMAGES_SHOW_IN_GRID_VIEW)
                    data!!.add(dataGet)

                    return data
                }

                AppConstants.ADAPTER_EXAMPLS -> {
                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.PAGINATION_ADAPTER)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.MULTIPLE_UI_RECYCLEVIEW)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.CHIPS_LAYOUT_MANAGER)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.SELECT_UNSELECT_OPTIONS)
                    data!!.add(dataGet)

                    return data
                }

                AppConstants.INTENT_EXAMPLS -> {
                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.StartActivityForResult)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.ImplicitIntentExamples)
                    data!!.add(dataGet)

                    return data
                }

                AppConstants.IMPLICIT_INTENT_EXAMPLS -> {
                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.shareByEMailWhatAppMessage)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.shareOnWhatApp)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.shareByMessage)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.shareByEMail)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.calling)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.openWebView)
                    data!!.add(dataGet)

                    return data
                }

                AppConstants.VALIDATIONS_EXAMPLS -> {
                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.LoginFormValidations)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.PasswordValidations)
                    data!!.add(dataGet)

                    return data
                }

                AppConstants.CUSTOM_CLASS_EXAMPLES -> {
                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.VIDEO_SHARE_OPTIONS)
                    data!!.add(dataGet)

                    return data
                }

                AppConstants.VIEWPAGER_EXAMPLS -> {
                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.HORIZONTAL_VIEW_PAGER)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.VERTICAL_VIEW_PAGER)
                    data!!.add(dataGet)

                    return data
                }

                AppConstants.DRAWER_EXAMPLS -> {
                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.CustomNavigation)
                    data!!.add(dataGet)

                    return data
                }

                AppConstants.MEDIAPLAYER_EXAMPLS -> {
                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.PLAY_SONG_WITH_SEEKBAR_DURATION)
                    data!!.add(dataGet)

                    return data
                }

                AppConstants.PERMISSION_EXAMPLS -> {
                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.FetchContacts)
                    data!!.add(dataGet)

                    return data
                }

                AppConstants.DIALOG_FRAGMENTS_EXAMPLS -> {
                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.Open_Dialog_by_Create)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.Open_Dialog_With_Param_by_Create)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.Open_Dialog_by_New_Instance)
                    data.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.Dialog_Activity)
                    data.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.Custom_Dialog_Class)
                    data.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.Alert_Dialog_Frgament)
                    data.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.Multiple_Open_Dialog_With_Param_by_Create)
                    data.add(dataGet)

                    return data
                }

                AppConstants.FRAGMENTS_EXAMPLS -> {
                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.Static_Fragment_Communication)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.Dynamic_Fragment_Communication)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.Activity_Fragment_Communication)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.FRAGMENT_ADD_REMOVE_REPLACE)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.FRAGMENT_ADD_REMOVE_POPBACKSTACK)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.FRAGMENT_RETAINING_STATE_EXAMPLE)
                    data!!.add(dataGet)

                    return data
                }

                AppConstants.BROADCAST_RECEIVERS_EXAMPLS -> {
                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.STATIC_BR_EXAMPLE)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.DYNAMIC_BR_EXAMPLE)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.CUSTOM_BR_EXAMPLE)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.INTERNET_CONNECTOR_RECEIVER_EXAMPLE)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.LOCAL_BROADCAST_MANAGER_EXAMPLE)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.ORDER_BR_EXAMPLE)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.FIREBASE_NOTIFICATION_BR_EXAMPLE)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.CALL_ACTIVITY_METHOD_FROM_ANOTHER_BR_EXAMPLE)
                    data!!.add(dataGet)

                    return data
                }

                AppConstants.SERVICES_EXAMPLES -> {
                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.SERVICES_LIFECYCLE_EXAMPLE)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.PLAYSONG_EXAMPLE)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.BINDING_SERVICE_EXAMPLE)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.CURRENT_DATE_SERVICE_EXAMPLE)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.INTENT_SERVICE_EXAMPLE)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.FOREGROUND_SERVICE_EXAMPLE)
                    data!!.add(dataGet)

                    return data
                }

                AppConstants.THREAD_EXAMPLES -> {
                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.WHY_USE_OF_THREAD_EXAMPLE)
                    data!!.add(dataGet)

                    return data
                }

              AppConstants.BANDWIDTH_EXAMPLS -> {
                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.SHOW_NETWORK_INFORMATION)
                    data!!.add(dataGet)

                    return data
                }

                AppConstants.IMAGES_EXAMPLES -> {
                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.ASPECT_RATIO_EXAMPLES)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.ROUNDED_IMAGE_VIEW)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.ROUND_CORNERS_IMAGE_VIEW)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.ZOOM_IMAGE_VIEW)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.CROP_IMAGE_VIEW)
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("option", AppConstants.CROP_IMAGE_VIEW_CUSTOMIZE)
                    data!!.add(dataGet)

                    return data
                }


                AppConstants.STUDENT_DATA -> {
                    dataGet = HashMap()
                    dataGet.put("name", "Shiv")
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("name", "Deepika")
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("name", "Anuj")

                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("name", "Abhinav")
                    data!!.add(dataGet)

                    dataGet = HashMap()
                    dataGet.put("name", "Dinesh")
                    data!!.add(dataGet)

                    return data
                }
                else -> return data
            }
        }
    }
}