package apiServiceAll.ApiClientEj;

import com.self.demoaall.BuildConfig;

public class ApiControllerEj {

    /*
     * 1 for stage url
     * 2 for dev url
     * 3 for live url
     */
    static int baseUrlType = 3;
    /*new live url 22Mar2019*/
    private static String liveUrl = "https://app-api.editorji.com";
    private static String stageUrl = "https://app-stage.editorji.com";
    private static String devUrl = "https://app-dev.editorji.com";


    public static String getBaseUrl() {
        switch (baseUrlType) {
            case 1:
                return stageUrl;
            case 2:
                return devUrl;
            case 3:
                return liveUrl;
            default:
                return liveUrl;
        }
    }

    public static String getErrorUrl() {
        switch (baseUrlType){
            case 1 :
                return stageUrl+"/";
            case 2 :
                return devUrl+"/";
            case 3 :
                return liveUrl+"/";
            default:
                return liveUrl+"/";
        }
     }


    //  Another way

    private String getAppBaseUrl(){
        if (BuildConfig.BUILD_TYPE.equals("debug"))
            return stageUrl;
        else
            return liveUrl;
    }

}
