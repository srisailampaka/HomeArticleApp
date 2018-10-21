package com.home24.ui.main.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.home24.R
import com.home24.data.db.ArticleEntity
import com.home24.di.GlideApp
import com.home24.ui.main.States
import com.home24.ui.main.ViewPagerListener
import com.home24.ui.main.selection.SelectionViewModel

/**
 * ViewAdapter class for Select images list
 */
class ViewpagerAdapter(private val articles: List<ArticleEntity>, private val viewModel: SelectionViewModel, private val pagerListener: ViewPagerListener) : PagerAdapter() {


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = container.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_selection, null)

        val imageView: ImageView = view.findViewById(R.id.image_view)
        val likeButton: Button = view.findViewById(R.id.like_button)
        val disLikeButton: Button = view.findViewById(R.id.dislike_button)
        GlideApp.with(container.context)
                .load(articles.get(position).uri)
                .into(imageView)
        container?.addView(view)

        val articleEntity = viewModel.getSelectedArticleDetails(articles.get(position).sku!!)
        when (articleEntity.state) {
            States.LIKED.value -> likeButton.background = ContextCompat.getDrawable(container.context, R.drawable.icons_like_on)
            States.DIS_LIKED.value -> disLikeButton.background = ContextCompat.getDrawable(container.context, R.drawable.icons_dislike_on)
        }
        likeButton.setOnClickListener(View.OnClickListener {
            likeButton.background = ContextCompat.getDrawable(container.context, R.drawable.icons_like_on)
            disLikeButton.background = ContextCompat.getDrawable(container.context, R.drawable.icons_unlike_off)
            Toast.makeText(container.context, container.context.getString(R.string.liked), Toast.LENGTH_LONG).show()
            viewModel.updateArticleDetails(articles.get(position).sku!!, States.LIKED.value)
        })

        disLikeButton.setOnClickListener(View.OnClickListener {
            likeButton.background = ContextCompat.getDrawable(container.context, R.drawable.icons_like_off)
            disLikeButton.background = ContextCompat.getDrawable(container.context, R.drawable.icons_dislike_on)
            Toast.makeText(container.context, container.context.getString(R.string.disliked), Toast.LENGTH_LONG).show()
            viewModel.updateArticleDetails(articles.get(position).sku!!, States.DIS_LIKED.value)
        })
        pagerListener.onItemSelectedListener((position + 1).toString() + "/" + articles.size)
        return view
    }

    override fun isViewFromObject(view: View, view2: Any): Boolean {
        return view === view2 as View
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return articles.size
    }
}