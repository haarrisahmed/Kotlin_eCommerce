package com.mcornel.ecommerce

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var resultString = intent.getStringExtra("result")
        txtResult.text = resultString
        btnSave.setOnClickListener {
            var name = txtName.text.toString()
            var fileData = "$name: $resultString"

            // using shared preferences to write to file
            var sharedPref = getSharedPreferences("my_calculations", Context.MODE_PRIVATE)  // creates file
            var editor = sharedPref.edit()
            editor.putString("result", fileData)        // add variable result with it's value
            editor.commit()
        }

        btnReadFile.setOnClickListener {
            var sharedPref = getSharedPreferences("my_calculations", Context.MODE_PRIVATE)  // creates file
            txtRead.text = sharedPref.getString("result", "Nothing found")       // use shared preference to read file or return default
        }
    }
}
