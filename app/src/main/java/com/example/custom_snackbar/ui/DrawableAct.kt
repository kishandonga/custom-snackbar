package com.example.custom_snackbar.ui

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.custom_snackbar.R
import com.example.custom_snackbar.utils.themeConst
import com.google.android.material.snackbar.Snackbar
import com.kishandonga.csbx.CustomSnackbar
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import kotlinx.android.synthetic.main.activity_drawable.*

class DrawableAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val themeConst = intent.getIntExtra(themeConst, 0)
        setTheme(if (themeConst == 0) R.style.AppCompat_ActionBar else R.style.Material_ActionBar)

        setContentView(R.layout.activity_drawable)
        title = "Custom Drawable"

        var textColor = Color.WHITE
        var actionTxtColor = ContextCompat.getColor(this, R.color.colorAccent)

        btnActionTextColor.setOnClickListener {
            ColorPickerDialog.Builder(this)
                .setTitle("ColorPicker Dialog")
                .setPositiveButton(getString(android.R.string.ok), ColorEnvelopeListener { envelope, _ ->
                    llActionTextColor.setBackgroundColor(envelope.color)
                    actionTxtColor = envelope.color
                })
                .setNegativeButton(getString(android.R.string.cancel)) { di: DialogInterface, _: Int ->
                    di.cancel()
                }
                .show()
        }

        btnTextColor.setOnClickListener {
            ColorPickerDialog.Builder(this)
                .setTitle("ColorPicker Dialog")
                .setPositiveButton(getString(android.R.string.ok), ColorEnvelopeListener { envelope, _ ->
                    llTextColor.setBackgroundColor(envelope.color)
                    textColor = envelope.color
                })
                .setNegativeButton(getString(android.R.string.cancel)) { di: DialogInterface, _: Int ->
                    di.cancel()
                }
                .show()
        }

        sbPadding.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                tvPaddingIndicator.text = p0?.progress.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        var snackbar: CustomSnackbar? = null

        btnShow.setOnClickListener {

            val timeDuration: Int = when {
                rbLengthIndefinite.isChecked -> Snackbar.LENGTH_INDEFINITE
                rbLengthLong.isChecked -> Snackbar.LENGTH_LONG
                else -> Snackbar.LENGTH_SHORT
            }

            val typeface: Typeface = when {
                rbBold.isChecked -> Typeface.defaultFromStyle(Typeface.BOLD)
                rbBoldItalic.isChecked -> Typeface.defaultFromStyle(Typeface.BOLD_ITALIC)
                rbItalic.isChecked -> Typeface.defaultFromStyle(Typeface.ITALIC)
                else -> Typeface.defaultFromStyle(Typeface.NORMAL)
            }

            snackbar = CustomSnackbar(this).show {
                drawableRes(R.drawable.ic_gradient)
                textColor(textColor)
                actionTextColor(actionTxtColor)
                textTypeface(typeface)
                actionTypeface(typeface)
                padding(sbPadding.progress)
                duration(timeDuration)
                message("Testing Message...")
                if (rbWithAction.isChecked) {
                    withAction(android.R.string.ok) {
                        it.dismiss()
                    }
                }
            }
        }

        btnHide.setOnClickListener {
            snackbar?.dismiss()
        }
    }
}
