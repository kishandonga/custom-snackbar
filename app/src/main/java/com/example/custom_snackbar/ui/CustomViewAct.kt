package com.example.custom_snackbar.ui

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.custom_snackbar.R
import com.example.custom_snackbar.databinding.ActivityCustomViewBinding
import com.example.custom_snackbar.utils.themeConst
import com.google.android.material.snackbar.Snackbar
import com.kishandonga.csbx.CustomSnackbar

class CustomViewAct : AppCompatActivity() {

    private lateinit var binding: ActivityCustomViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val themeConst = intent.getIntExtra(themeConst, 0)
        setTheme(if (themeConst == 0) R.style.AppCompat_ActionBar else R.style.Material_ActionBar)

        binding = ActivityCustomViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Custom View"

        var snackbar: CustomSnackbar? = null

        binding.sbPadding.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.tvPaddingIndicator.text = p0?.progress.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        binding.btnShowCustomView.setOnClickListener {

            val timeDuration: Int = when {
                binding.rbLengthIndefinite.isChecked -> Snackbar.LENGTH_INDEFINITE
                binding.rbLengthLong.isChecked -> Snackbar.LENGTH_LONG
                else -> Snackbar.LENGTH_SHORT
            }

            snackbar = CustomSnackbar(this).show {
                customView(R.layout.snack_layout)
                padding(binding.sbPadding.progress)
                duration(timeDuration)
                withCustomView {
                    it.findViewById<View>(R.id.btnUndo).setOnClickListener {
                        dismiss()
                    }
                }
            }
        }

        binding.btnHideCustomView.setOnClickListener {
            snackbar?.dismiss()
        }
    }
}
