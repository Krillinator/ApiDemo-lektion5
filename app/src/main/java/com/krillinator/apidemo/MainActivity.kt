package com.krillinator.apidemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val results = findViewById<TextView>(R.id.result)
        val foxImage = findViewById<ImageView>(R.id.img_fox)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://randomfox.ca/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(FoxApi::class.java)
        val call = service.getInfo()

        call.enqueue(object : Callback<Fox> {
            override fun onResponse(call: Call<Fox>, response: Response<Fox>) {
                if (response.isSuccessful) {
                    val fox = response.body()!!

                    val stringBuilder = "Fox: \n link: ${fox.myLink} \n link: ${fox.myImage}"

                    results.text = stringBuilder

                    // ImageView
                    Glide.with(this@MainActivity)
                        .load(fox.myImage)
                        .into(foxImage)
                }
            }

            override fun onFailure(call: Call<Fox>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}