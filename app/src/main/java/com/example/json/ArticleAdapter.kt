package com.example.json

    import android.content.Intent
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView

    class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

        private var articles: List<Article> = emptyList()

        fun setArticles(articles: List<Article>) {
            this.articles = articles
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
            return ArticleViewHolder(view)
        }

        override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
            val article = articles[position]
            holder.titleTextView.text = article.title.rendered
            holder.dateTextView.text = article.date

            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, ArticleDetailActivity::class.java).apply {
                    putExtra("title", article.title.rendered)
                    putExtra("date", article.date)
                    putExtra("content", article.content.rendered)
                }
                holder.itemView.context.startActivity(intent)
            }
        }

        override fun getItemCount(): Int = articles.size

        class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
            val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        }
    }