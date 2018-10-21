package com.home24.ui.main.review

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.home24.R
import com.home24.data.db.ArticleEntity
import com.home24.ui.main.adapter.ReviewListAdapter
import com.home24.utils.ProgressDialog
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_review.*
import javax.inject.Inject

private val TAG = ReviewFragment::class.java.name

/**
 * This fragment class for review the images like or dislike
 */
class ReviewFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ReviewViewModel
    private var articleList = ArrayList<ArticleEntity>()

    companion object {
        fun newInstance() = ReviewFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_review, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ReviewViewModel::class.java)
        observerViewModel()
        viewModel.getArticleListDetails()
        grid.setOnClickListener(View.OnClickListener {
            setAdapter(2)
        })
        list.setOnClickListener(View.OnClickListener {
            setAdapter(1)
        })


    }

    private fun observerViewModel() {
        viewModel.articleListData.observe(this, stateObserver)
        viewModel.errorData.observe(this, errorStateObserver)
    }


    private val stateObserver = Observer<List<ArticleEntity>> {
        ProgressDialog.dismissProgressDialog()
        Log.d(TAG, "data -> ${it!!.size} ")
        articleList = it as ArrayList<ArticleEntity>
        setAdapter(1)
    }

    private val errorStateObserver = Observer<String> {
        ProgressDialog.dismissProgressDialog()
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()

    }


    fun setAdapter(spanCount: Int) {
        recyclerview.layoutManager = GridLayoutManager(activity, spanCount)
        recyclerview.adapter = ReviewListAdapter(articleList)
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.articleListData.removeObserver(stateObserver)
        viewModel.errorData.removeObserver(errorStateObserver)
    }

}