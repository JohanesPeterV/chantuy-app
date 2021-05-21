package edu.bluejack20_2.chantuy.views.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.LinearProgressIndicator
import edu.bluejack20_2.chantuy.R
import edu.bluejack20_2.chantuy.views.NotificationAdapter
import edu.bluejack20_2.chantuy.views.Text
import edu.bluejack20_2.chantuy.views.TextAdapter

class NotificationActivity : AppCompatActivity() {
    val viewModel=NotificationViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_notification)
        val viewModel= NotificationViewModel()
        val notificationAdapter = NotificationAdapter()
        val notificationRV: RecyclerView = findViewById(R.id.notification_comment_rv)
        notificationRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        notificationRV.adapter = notificationAdapter

        val noNotifTv: TextView = findViewById(R.id.no_notification_tv)
        val btn: Button = findViewById(R.id.mn_btn)
        viewModel.nList.observe(this, Observer {
            if (it.isEmpty()) {
                noNotifTv.visibility = View.VISIBLE
            } else {
                noNotifTv.visibility = View.GONE
            }

            if (viewModel.limit >= viewModel.listSize-1) {
                btn.visibility = View.GONE
            } else {
                btn.visibility = View.VISIBLE
            }
            notificationAdapter.submitList(it)
        })

        btn.setOnClickListener {
            viewModel.limit = viewModel.limit + 5
            viewModel.getData()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}