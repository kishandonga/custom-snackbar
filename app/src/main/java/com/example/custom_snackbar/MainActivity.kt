package com.example.custom_snackbar

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.kishandonga.snackbar.CustomSnackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        snack.setOnClickListener {
            val content = findViewById<View>(android.R.id.content)
            val s = Snackbar.make(root, "You can also define objects", Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", {}).show()

            /*val v = (s.view as ViewGroup).children.first()
            //v.setPadding(s.view.paddingLeft,0,s.view.paddingRight,0)
            v.setBackgroundColor(Color.GRAY)



            //s.view.setBackgroundColor(Color.TRANSPARENT)
            //s.view.setPadding(20,0,20,20)*/

            /*
            withCustomView {
                    it.findViewById<View>(R.id.btnSnackLayout)?.setOnClickListener {
                        Log.d("TAG_DATA", "Test...")
                    }
                }
             */
        }

        test.setOnClickListener {
            CustomSnackbar(this, root).show {
                padding(20)
                cornerRadius(15f)
                duration(Snackbar.LENGTH_LONG)
                message("You can also define objects")
                withAction("OK") {
                    it.dismiss()
                }
            }
        }
    }
}
