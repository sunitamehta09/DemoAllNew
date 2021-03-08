package com.earshot.apiservice

interface RetrofitApiCallBack<T> : CommonView {

    /**
     * Call when api is successfully executed and move to next screen.
     *
     */
    fun onApiSuccess(childList : T);

    /**
     * Call when api response comes with en error.
     * @errorMsg : Show error message
     * @errorCode : Show error code
     */
    fun onApiError(errorMsg : String, errorCode : Int );
}