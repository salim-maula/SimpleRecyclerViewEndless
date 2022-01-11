package com.example.simplerecylerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val dataList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this)
        val adapter = RecyclerAdapter(recyclerList = updateDataList(dataList))
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)){
                    onScrollToBottom()
                }
            }
            fun onScrollToBottom(){
                val initialSize = dataList.size
                updateDataList(dataList)
                val updateSize = dataList.size
                adapter.notifyItemRangeChanged(initialSize, updateSize)
            }

        })
    }
}