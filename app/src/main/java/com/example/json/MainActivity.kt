package com.example.json

import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.json.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ArticleAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val baseUrl = when (checkedId) {
                R.id.radioTechCrunch -> "https://techcrunch.com/"
                R.id.radioAyudaWP -> "https://www.ayudawp.com/"
                R.id.radioJavaProgrammer -> "https://www.thejavaprogrammer.com/"
                else -> "https://techcrunch.com/"
            }
            loadArticles(baseUrl)
        }

        // Load default articles
        loadArticles("https://techcrunch.com/")
    }

    private fun loadArticles(baseUrl: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)
        api.getArticles().enqueue(object : Callback<List<Article>> {
            override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
                if (response.isSuccessful) {
                    adapter.setArticles(response.body() ?: emptyList())
                }
            }

            override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                // Handle failure
            }
        })
    }
}