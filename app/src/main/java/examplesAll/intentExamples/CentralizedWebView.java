package examplesAll.intentExamples;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityCentralizedWebViewBinding;

import controllerAll.AppUtilsAll.ProgressBarUtils;
import controllerAll.utilsAll.BaseActivityJava;

public class CentralizedWebView extends BaseActivityJava {
    private ActivityCentralizedWebViewBinding viewBinding;

    @Override
    protected int getLayoutById() {
        return R.layout.activity_centralized_web_view;
    }

    @Override
    protected void initView() {
        viewBinding = (ActivityCentralizedWebViewBinding)viewDataBinding;

  /* To Manage settings state for a WebView we use WebSettings class. When a WebView is first created, it obtains a set of
     default settings. These default settings will be returned from any getter call.
     A WebSettings object obtained from WebView.getSettings() is tied to the life of the WebView.
     If a WebView has been destroyed, any method call on WebSettings will throw an IllegalStateException.  */
        ProgressBarUtils.Companion.pdStart(CentralizedWebView.this);

        WebSettings webSettings = viewBinding.webView.getSettings();

        //JavaScript is by default turned off in WebView widgets. Hence web pages containing javascript references
        // won’t work properly. To enable java script the following snippet needs to be called on the webview instance:

        webSettings.setJavaScriptEnabled(true);
        viewBinding.webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        viewBinding.webView.getSettings().setDomStorageEnabled(true);

        // In order to load a web url into the WebView,
        // you need to call a method loadUrl(String url) of the WebView class, specifying the required url.
        viewBinding.webView.loadUrl("https://developer.android.com/reference/android/view/View");


      /*  The default behavior when a user clicks on a link inside the webpage is to open the systems
       default browser app. This can break the user experience of the app users.
        To keep page navigation within the WebView and hence within the app, we need to create a subclass of
        WebViewClient, and override its shouldOverrideUrlLoading(WebView webView, String url) method.
        */

        viewBinding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                viewBinding.webView.loadUrl(url);
                return true;
            }

            //            @Override
//            public boolean shouldOverrideUrlLoading(WebView webView, String  url) {
//                Log.e("CentralizedWebView", "shouldOverrideUrlLoading" + "call");
//                webView.loadUrl("https://developer.android.com/reference/android/view/View");
//                return true;
//           }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                ProgressBarUtils.Companion.pdStop();
            }
        });
    }

}
