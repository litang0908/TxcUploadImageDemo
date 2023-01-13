package com.litangtech.txcuploadimage

import android.content.Intent
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val TXC_URL = "https://support.qq.com/product/492160"
    }

    private lateinit var chromeClient: CustomWebChromeClient
    private lateinit var fileChooserLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.webview)
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        settings.domStorageEnabled = true
        settings.blockNetworkImage = false

        webView.webViewClient = WebViewClient()

        /****** 兔小巢图片上传设置 START ******/
        fileChooserLauncher = registerForActivityResult(
            StartActivityForResult()
        ) { activityResult ->
            if (activityResult.resultCode != RESULT_OK) {
                return@registerForActivityResult
            }
            val intent = activityResult.data
            chromeClient.onGetImage(intent!!.data)
        }
        chromeClient = CustomWebChromeClient(this, fileChooserLauncher)
        webView.webChromeClient = chromeClient

        /****** 兔小巢图片上传设置 END ******/

        webView.loadUrl(TXC_URL)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}