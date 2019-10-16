package com.mcornel.ecommerce

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAdd.setOnClickListener {
            var num1 = txtNum1.text.toString().toFloat()
            var num2 = txtNum2.text.toString().toFloat()
            var sum  = num1 + num2
            Toast.makeText(this, "$num1 + $num2 = $sum", Toast.LENGTH_LONG).show()

        }
        rbRed.setOnClickListener {
            screen.setBackgroundColor(Color.rgb(255,0,0))
        }
        rbGreen.setOnClickListener {
            screen.setBackgroundColor(Color.parseColor("green"))
        }
        rbBlue.setOnClickListener {
//            screen.setBackgroundColor(Color.parseColor("#ffOO00ff")) todo figure out why this format fails
            screen.setBackgroundColor(Color.rgb(0,0,255))
        }

        btnSecondAct.setOnClickListener {
            var intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}
