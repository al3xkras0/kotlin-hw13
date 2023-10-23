package com.al3xkras.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.maltaisn.calcdialog.CalcDialog
import com.maltaisn.calcdialog.CalcDialog.CalcDialogCallback
import java.math.BigDecimal

class MainActivity : AppCompatActivity(), CalcDialogCallback {
    private var value: BigDecimal? = null
    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        setContentView(R.layout.activity_main)
        val runnable = {
            val calcDialog = CalcDialog()
            calcDialog.settings.initialValue = value
            calcDialog.settings.isZeroShownWhenNoValue = false
            calcDialog.show(supportFragmentManager, "calc_dialog")
            val t = Thread {
                while (calcDialog.dialog == null) {
                    Thread.sleep(20)
                }
                calcDialog.dialog?.setCanceledOnTouchOutside(false)
                calcDialog.dialog?.setCancelable(false)
            }
            t.start()
        }
        runnable()
        findViewById<Button>(R.id.button).setOnClickListener{
            runnable()
        }
    }
    override fun onValueEntered(requestCode: Int, value: BigDecimal?) {
        this.value = value
    }
}