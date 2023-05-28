package com.example.myapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var nameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var phoneTextView: TextView
    private lateinit var pictureImageView: ImageView
    private lateinit var reloadButton: Button

    private var currentUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameTextView = findViewById(R.id.nameTextView)
        emailTextView = findViewById(R.id.emailTextView)
        phoneTextView = findViewById(R.id.phoneTextView)
        pictureImageView = findViewById(R.id.pictureImageView)
        reloadButton = findViewById(R.id.reloadButton)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val randomUserApi = retrofit.create(RandomUserApi::class.java)

        reloadButton.setOnClickListener {
            loadRandomUser(randomUserApi)
        }

        loadRandomUser(randomUserApi)
    }

    private fun loadRandomUser(randomUserApi: RandomUserApi) {
        randomUserApi.getRandomUser().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                val user = response.body()?.results?.firstOrNull()

                if (user != null) {
                    currentUser = user
                    runOnUiThread {
                        nameTextView.text = "${user.name.first.fullName()} ${user.name.last.fullName()}"
                        emailTextView.text = user.email
                        phoneTextView.text = user.phone

                        Glide.with(this@MainActivity)
                            .load(user.picture.large)
                            .into(pictureImageView)
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, R.string.error, Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
    private fun String.fullName(): String {
        return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }




}
