package com.example.custom_snackbar.ui

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.custom_snackbar.R
import com.example.custom_snackbar.databinding.ActivityDrawableBinding
import com.example.custom_snackbar.utils.themeConst
import com.google.android.material.snackbar.Snackbar
import com.kishandonga.csbx.CustomSnackbar
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener

class DrawableAct : AppCompatActivity() {

    private lateinit var binding: ActivityDrawableBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val themeConst = intent.getIntExtra(themeConst, 0)
        setTheme(if (themeConst == 0) R.style.AppCompat_ActionBar else R.style.Material_ActionBar)

        binding = ActivityDrawableBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Custom Drawable"

        var textColor = Color.WHITE
        var actionTxtColor = ContextCompat.getColor(this, R.color.colorAccent)

        binding.btnActionTextColor.setOnClickListener {
            ColorPickerDialog.Builder(this)
                .setTitle("ColorPicker Dialog")
                .setPositiveButton(
                    getString(android.R.string.ok),
                    ColorEnvelopeListener { envelope, _ ->
                        binding.llActionTextColor.setBackgroundColor(envelope.color)
                        actionTxtColor = envelope.color
                    })
                .setNegativeButton(getString(android.R.string.cancel)) { di: DialogInterface, _: Int ->
                    di.cancel()
                }
                .show()
        }

        binding.btnTextColor.setOnClickListener {
            ColorPickerDialog.Builder(this)
                .setTitle("ColorPicker Dialog")
                .setPositiveButton(
                    getString(android.R.string.ok),
                    ColorEnvelopeListener { envelope, _ ->
                        binding.llTextColor.setBackgroundColor(envelope.color)
                        textColor = envelope.color
                    })
                .setNegativeButton(getString(android.R.string.cancel)) { di: DialogInterface, _: Int ->
                    di.cancel()
                }
                .show()
        }

        binding.sbPadding.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.tvPaddingIndicator.text = p0?.progress.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        var snackbar: CustomSnackbar? = null

        binding.btnShow.setOnClickListener {

            val timeDuration: Int = when {
                binding.rbLengthIndefinite.isChecked -> Snackbar.LENGTH_INDEFINITE
                binding.rbLengthLong.isChecked -> Snackbar.LENGTH_LONG
                else -> Snackbar.LENGTH_SHORT
            }

            val typeface: Typeface = when {
                binding.rbBold.isChecked -> Typeface.defaultFromStyle(Typeface.BOLD)
                binding.rbBoldItalic.isChecked -> Typeface.defaultFromStyle(Typeface.BOLD_ITALIC)
                binding.rbItalic.isChecked -> Typeface.defaultFromStyle(Typeface.ITALIC)
                else -> Typeface.defaultFromStyle(Typeface.NORMAL)
            }

            snackbar = CustomSnackbar(this).show {
                drawableRes(R.drawable.ic_gradient)
                textColor(textColor)
                actionTextColor(actionTxtColor)
                textTypeface(typeface)
                actionTypeface(typeface)
                padding(binding.sbPadding.progress)
                duration(timeDuration)
                message("Testing Message...")
                if (binding.rbWithAction.isChecked) {
                    withAction(android.R.string.ok) {
                        it.dismiss()
                    }
                }
            }
        }

        binding.btnHide.setOnClickListener {
            snackbar?.dismiss()
        }
    }

}
