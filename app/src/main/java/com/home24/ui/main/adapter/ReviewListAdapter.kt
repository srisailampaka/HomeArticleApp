package com.home24.ui.main.adapter


import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.home24.R
import com.home24.data.db.ArticleEntity
import com.home24.di.GlideApp
import com.home24.ui.main.States
import kotlinx.android.synthetic.main.item_review.view.*

/**
 * Adapter class for Review images list
 */
class ReviewListAdapter(articleDetails: ArrayList<ArticleEntity>) : RecyclerView.Adapter<ReviewListAdapter.ViewHolder>() {

    private val articleList: ArrayList<ArticleEntity>

    init {
        articleList = articleDetails
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_review, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = articleList.get(position)


        GlideApp.with(holder.itemView.context)
                .load(item.uri)
                .into(holder.image)


        when (item.state) {
            States.NOT_SELECTED.value -> setTheView(holder.statusView, R.drawable.icons_question_mark)
            States.LIKED.value -> setTheView(holder.statusView, R.drawable.icons_like_on)
            States.DIS_LIKED.value -> setTheView(holder.statusView, R.drawable.icons_dislike_on)
        }


    }

    private fun setTheView(statusView: ImageView, icon: Int) {
        statusView.background = ContextCompat.getDrawable(statusView.context, icon)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var image = view.image_view
        var statusView = view.like_button
    }


}