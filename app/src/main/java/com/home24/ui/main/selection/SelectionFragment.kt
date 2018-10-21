package com.home24.ui.main.selection

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.home24.R
import com.home24.data.db.ArticleEntity
import com.home24.ui.main.MainActivity
import com.home24.ui.main.ViewPagerListener
import com.home24.ui.main.adapter.ViewpagerAdapter
import com.home24.ui.main.review.ReviewFragment
import com.home24.utils.CommonUtil
import com.home24.utils.ProgressDialog
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_selection.*
import javax.inject.Inject

private val TAG = SelectionFragment::class.java.name

/**
 * This fragment class for select the images like or dislike
 */
class SelectionFragment : Fragment(), ViewPagerListener {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SelectionViewModel

    companion object {
        fun newInstance() = SelectionFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_selection, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SelectionViewModel::class.java)

        observerViewModel()

        review_button.setOnClickListener(View.OnClickListener {
            (activity as MainActivity).replaceTheFragment(R.id.container, ReviewFragment.newInstance(), ReviewFragment::class.java.name)
        })
        if (CommonUtil.isNetworkStatusAvailable(this!!.context!!)) {
            viewModel.getArticleListDetails()
            ProgressDialog.showProgressDialog(this!!.context!!)
        } else {
            Toast.makeText(context, getText(R.string.network_error), Toast.LENGTH_LONG).show()
        }
    }

    private fun observerViewModel() {
        viewModel.articleListData.observe(this, articleStateObserver)
        viewModel.errorData.observe(this, errorStateObserver)
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }


    override fun onDestroy() {
        super.onDestroy()
        viewModel.articleListData.removeObserver(articleStateObserver)
        viewModel.errorData.removeObserver(errorStateObserver)
    }


    private val articleStateObserver = Observer<List<ArticleEntity>> {
        ProgressDialog.dismissProgressDialog()
        Log.d(TAG, "data -> ${it!!.size} ")
        viewPager.adapter = ViewpagerAdapter(it, viewModel, this)

    }

    private val errorStateObserver = Observer<String> {
        ProgressDialog.dismissProgressDialog()
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()

    }

    override fun onItemSelectedListener(positionText: String) {

        selected_value.text = positionText
    }
}