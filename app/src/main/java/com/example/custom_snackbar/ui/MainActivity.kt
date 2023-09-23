package com.example.custom_snackbar.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.custom_snackbar.databinding.ActivityMainBinding
import com.example.custom_snackbar.utils.themeConst

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnWithCL.setOnClickListener {
            startNewActivity(CoordinatorLayoutAct::class.java)
        }

        binding.btnWithoutCL.setOnClickListener {
            startNewActivity(SimpleAct::class.java)
        }

        binding.btnCustomView.setOnClickListener {
            startNewActivity(CustomViewAct::class.java)
        }

        binding.btnDrawableAct.setOnClickListener {
            startNewActivity(DrawableAct::class.java)
        }
    }

    private fun startNewActivity(cls: Class<*>) {
        val theme = if (binding.rbAppCompact.isChecked) 0 else 1
        val intent = Intent(this, cls).apply {
            putExtra(themeConst, theme)
        }
        startActivity(intent)
    }
}
