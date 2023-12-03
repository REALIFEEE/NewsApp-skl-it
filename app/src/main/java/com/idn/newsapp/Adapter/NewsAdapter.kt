package com.idn.newsapp.Adapter

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idn.newsapp.DetailActivity
import com.idn.newsapp.R
import com.idn.newsapp.data.ArticlesItems
import com.idn.newsapp.databinding.ItemNews1Binding
import com.squareup.picasso.Picasso
import java.util.Calendar
import java.util.Locale
class NewsAdapter : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {
    private val listNews = ArrayList<ArticlesItems>()

    fun setData(list: List<ArticlesItems>?) {
        if (list == null) return
        listNews.clear()
        listNews.addAll(list)
    }

    inner class MyViewHolder(val binding: ItemNews1Binding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemNews1Binding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val news = listNews[position]

        val date = news.publishedAt?.take(10)
        val dateArray = date?.split("-")?.toTypedArray()

        val time = news.publishedAt?.takeLast(9)
        val timeArray = time?.split(":")?.toTypedArray()

        val calendar = Calendar.getInstance()
        dateArray?.let { calendar.set(Calendar.YEAR, it[0].toInt())
            calendar.set(Calendar.MONTH, it[1].toInt() - 1)
            calendar.set(Calendar.DAY_OF_MONTH, it[2].toInt())}
        timeArray?.let {
            calendar.set(Calendar.HOUR_OF_DAY, it[0].toInt())
            calendar.set(Calendar.MINUTE, it[1].toInt())
        }

        val dateResultFormat = SimpleDateFormat("EEE, dd MMMM", Locale.getDefault())
            .format(calendar.time).toString()
        val timeResultFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            .format(calendar.time).toString()

        Log.i("NewsAdapter", "onBindViewHolder: $dateResultFormat")
        Log.i("NewsAdapter", "onBindViewHolder: $timeResultFormat")
        val newsDate = "$dateResultFormat | "
        val newsTime = "$timeResultFormat UTC"
        holder.binding.apply {
            tvSource.text = news.source?.name
            tvNewsTittle.text = news.title
            tvDate.text = newsDate
            tvNewsTime.text = newsTime
            Picasso.get().load(news.urlToImage).resize(
                this.root.resources.getDimensionPixelSize(R.dimen._150dp),
                this.root.resources.getDimensionPixelSize(R.dimen._120dp)
            )
                .onlyScaleDown()
                .centerCrop()
                .placeholder(R.drawable.news)
                .into(imgNews)
        }

        holder.itemView.setOnClickListener { val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.NEWS_DATA, news)
            intent.putExtra(DetailActivity.EXTRA_DATA_DATE, newsDate)
            intent.putExtra(DetailActivity.EXTRA_DATA_TIME, newsTime)
            it.context.startActivity(intent)
        } }

    override fun getItemCount() = listNews.size

}