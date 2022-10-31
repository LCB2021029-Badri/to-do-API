package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException

//for log
const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //calling the below created function
        setupRecyclerView()

        //to execute the api request in background thread
        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true

            //possible exceptions
            val response = try{
                RetrofitInstance.api.getTodos()
            }catch (e: IOException){
                Log.e(TAG,"TOEception, you might not have internet connection")
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(TAG,"HttpException, unexpected response")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }

            //if eceptions are handled
            if(response.isSuccessful && response.body() != null){
                todoAdapter.todos = response.body()!!
            }else{
                Log.e(TAG,"Response not successful")
            }

            //no matter what the outcome we are hiding the progress bar
            binding.progressBar.isVisible = false

        }
    }

    //function to initialize the recyclerView
    private fun setupRecyclerView() = binding.rvTodos.apply {
        todoAdapter = TodoAdapter()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }

}