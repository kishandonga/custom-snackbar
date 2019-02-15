package com.example.custom_snackbar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.custom_snackbar.utils.themeConst
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        withCL.setOnClickListener {
            startNewActivity(CoordinatorLayoutAct::class.java)
        }

        withoutCL.setOnClickListener {
            startNewActivity(SimpleAct::class.java)
        }
    }

    private fun startNewActivity(cls: Class<*>) {
        val theme = if (rbAppCompact.isChecked) 0 else 1
        val intent = Intent(this, cls).apply {
            putExtra(themeConst, theme)
        }
        startActivity(intent)
    }
}
