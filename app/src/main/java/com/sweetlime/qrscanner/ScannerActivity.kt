package com.sweetlime.qrscanner

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScannerActivity : AppCompatActivity(), ZXingScannerView.ResultHandler   {

    private lateinit var mScannerView: ZXingScannerView

    public override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        mScannerView = ZXingScannerView(this)
        setContentView(mScannerView)
    }

    public override fun onResume() {
        super.onResume()
        mScannerView.let {
            it.setResultHandler(this)
            it.startCamera()
        }
    }

    public override fun onPause() {
        super.onPause()
        mScannerView.let{
            it.stopCamera()
        }
    }

    override fun handleResult(rawResult: Result?) {

        if (rawResult != null) {
            val result = Intent()

            result.putExtra(SCANNER_RESULT, rawResult.text)
            setResult(Activity.RESULT_OK, result)
        } else {
            setResult(Activity.RESULT_CANCELED)
        }

        finish()
    }

    companion object{
        const val SCANNER_RESULT = "scannerResult"
    }
}
