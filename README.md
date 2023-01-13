# TxcUploadImageDemo
兔小巢 Android 端上传图片示例，通过 `CustomWebChromeClient` 与 `ActivityResultLauncher<Intent>` 实现选择图片后上传逻辑。

兔小巢官方方案 [链接](https://cloud.tencent.com/developer/article/1035050) 依旧使用 `startActivityForResult` 参数获取比较繁琐，建议使用 `ActivityResultLauncher<Intent>` 。 