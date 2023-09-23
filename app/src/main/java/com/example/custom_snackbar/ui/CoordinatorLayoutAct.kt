package com.example.custom_snackbar.ui

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.custom_snackbar.R
import com.example.custom_snackbar.databinding.ActivityCoordinatorLayoutBinding
import com.example.custom_snackbar.utils.themeConst
import com.google.android.material.snackbar.Snackbar
import com.kishandonga.csbx.CustomSnackbar
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener

class CoordinatorLayoutAct : AppCompatActivity() {

    private lateinit var binding: ActivityCoordinatorLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val themeConst = intent.getIntExtra(themeConst, 0)
        setTheme(if (themeConst == 0) R.style.AppCompat_NoActionBar else R.style.Material_NoActionBar)

        binding = ActivityCoordinatorLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarLayout)
        title = getString(R.string.lbl_with_coordinator_layout)

        var textColor = Color.WHITE
        var bgColor = Color.TRANSPARENT
        var borderColor = Color.TRANSPARENT
        var actionTxtColor = ContextCompat.getColor(this, R.color.colorAccent)

        binding.content.btnActionTextColor.setOnClickListener {
            ColorPickerDialog.Builder(this)
                .setTitle("ColorPicker Dialog")
                .setPositiveButton(
                    getString(android.R.string.ok),
                    ColorEnvelopeListener { envelope, _ ->
                        binding.content.llActionTextColor.setBackgroundColor(envelope.color)
                        actionTxtColor = envelope.color
                    })
                .setNegativeButton(getString(android.R.string.cancel)) { di: DialogInterface, _: Int ->
                    di.cancel()
                }
                .show()
        }

        binding.content.btnTextColor.setOnClickListener {
            ColorPickerDialog.Builder(this)
                .setTitle("ColorPicker Dialog")
                .setPositiveButton(
                    getString(android.R.string.ok),
                    ColorEnvelopeListener { envelope, _ ->
                        binding.content.llTextColor.setBackgroundColor(envelope.color)
                        textColor = envelope.color
                    })
                .setNegativeButton(getString(android.R.string.cancel)) { di: DialogInterface, _: Int ->
                    di.cancel()
                }
                .show()
        }

        binding.content.btnBgColor.setOnClickListener {
            ColorPickerDialog.Builder(this)
                .setTitle("ColorPicker Dialog")
                .setPositiveButton(
                    getString(android.R.string.ok),
                    ColorEnvelopeListener { envelope, _ ->
                        binding.content.llBgColor.setBackgroundColor(envelope.color)
                        bgColor = envelope.color
                    })
                .setNegativeButton(getString(android.R.string.cancel)) { di: DialogInterface, _: Int ->
                    di.cancel()
                }
                .show()
        }

        binding.content.btnBorderColor.setOnClickListener {
            ColorPickerDialog.Builder(this)
                .setTitle("ColorPicker Dialog")
                .setPositiveButton(
                    getString(android.R.string.ok),
                    ColorEnvelopeListener { envelope, _ ->
                        binding.content.llBorderColor.setBackgroundColor(envelope.color)
                        borderColor = envelope.color
                    })
                .setNegativeButton(getString(android.R.string.cancel)) { di: DialogInterface, _: Int ->
                    di.cancel()
                }
                .show()
        }

        binding.content.sbPadding.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.content.tvPaddingIndicator.text = p0?.progress.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        binding.content.sbCornerRadius.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.content.tvCornerRadiusIndicator.text = p0?.progress.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        binding.content.sbBorderWidth.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.content.tvBorderIndicator.text = p0?.progress.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        var snackbar: CustomSnackbar? = null

        binding.content.btnShow.setOnClickListener {

            val msg = binding.content.edMessage.text?.trim().toString()
            if (msg.isBlank() || msg.isEmpty()) {
                binding.content.edMessage.error = "Please Enter Message"
                return@setOnClickListener
            }

            val timeDuration: Int = when {
                binding.content.rbLengthIndefinite.isChecked -> Snackbar.LENGTH_INDEFINITE
                binding.content.rbLengthLong.isChecked -> Snackbar.LENGTH_LONG
                else -> Snackbar.LENGTH_SHORT
            }

            val typeface: Typeface = when {
                binding.content.rbBold.isChecked -> Typeface.defaultFromStyle(Typeface.BOLD)
                binding.content.rbBoldItalic.isChecked -> Typeface.defaultFromStyle(Typeface.BOLD_ITALIC)
                binding.content.rbItalic.isChecked -> Typeface.defaultFromStyle(Typeface.ITALIC)
                else -> Typeface.defaultFromStyle(Typeface.NORMAL)
            }

            snackbar = CustomSnackbar(this, binding.root).show {
                textTypeface(typeface)
                actionTypeface(typeface)
                textColor(textColor)
                backgroundColor(bgColor)
                border(binding.content.sbBorderWidth.progress, borderColor)
                padding(binding.content.sbPadding.progress)
                cornerRadius(binding.content.sbCornerRadius.progress.toFloat())
                duration(timeDuration)
                actionTextColor(actionTxtColor)
                message(msg)
                if (binding.content.rbWithAction.isChecked) {
                    withAction(android.R.string.ok) {
                        it.dismiss()
                    }
                }
            }
        }

        binding.content.btnHide.setOnClickListener {
            snackbar?.dismiss()
        }
    }
}
