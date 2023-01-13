package com.litangtech.txcuploadimage

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.GetContent

/**
 * 兔小巢图片上传核心代码
 */
open class CustomWebChromeClient(
    private val context: Context,
    private val fileChooserLauncher: ActivityResultLauncher<Intent>
) : WebChromeClient() {
    private var mUploadMessage: ValueCallback<Array<Uri>>? = null

    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        mUploadMessage = filePathCallback
        var input = "*/*"
        if (fileChooserParams != null && fileChooserParams.acceptTypes != null && fileChooserParams.acceptTypes.isNotEmpty()) {
            input = fileChooserParams.acceptTypes[0]
        }
        fileChooserLauncher.launch(GetContent().createIntent(context, input))
        return true
    }

    fun onGetImage(uri: Uri?) {
        if (uri != null) {
            mUploadMessage?.onReceiveValue(arrayOf(uri))
        }
    }
}