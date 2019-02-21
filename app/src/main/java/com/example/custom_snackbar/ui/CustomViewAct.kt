package com.example.custom_snackbar.ui

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.custom_snackbar.R
import com.example.custom_snackbar.utils.themeConst
import com.google.android.material.snackbar.Snackbar
import com.kishandonga.csbx.CustomSnackbar
import kotlinx.android.synthetic.main.activity_custom_view.*

class CustomViewAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val themeConst = intent.getIntExtra(themeConst, 0)
        setTheme(if (themeConst == 0) R.style.AppCompat_ActionBar else R.style.Material_ActionBar)

        setContentView(R.layout.activity_custom_view)
        title = "Custom View"


        var snackbar: CustomSnackbar? = null

        sbPadding.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                tvPaddingIndicator.text = p0?.progress.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        btnShowCustomView.setOnClickListener {

            val timeDuration: Int = when {
                rbLengthIndefinite.isChecked -> Snackbar.LENGTH_INDEFINITE
                rbLengthLong.isChecked -> Snackbar.LENGTH_LONG
                else -> Snackbar.LENGTH_SHORT
            }

            snackbar = CustomSnackbar(this).show {
                customView(R.layout.snack_layout)
                padding(sbPadding.progress)
                duration(timeDuration)
                withCustomView {
                    it.findViewById<View>(R.id.btnUndo).setOnClickListener {
                        dismiss()
                    }
                }
            }
        }

        btnHideCustomView.setOnClickListener {
            snackbar?.dismiss()
        }
    }
}
