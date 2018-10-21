package com.home24.ui.main.review

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.home24.data.db.ArticleDao
import com.home24.data.db.ArticleEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * this model class for ReviewFragment
 */
class ReviewViewModel @Inject constructor(private val articleDao: ArticleDao) : ViewModel() {
    val articleListData = MutableLiveData<List<ArticleEntity>>()
    val errorData = MutableLiveData<String>()
    /**
     * Method for get the Article list
     */
    fun getArticleListDetails() {

        articleDao.getAllArticles().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccessDb, this::onError)
    }

    private fun onSuccessDb(articleEntityList: List<ArticleEntity>) {
        articleListData.postValue(articleEntityList)
    }

    private fun onError(error: Throwable) {
        Log.e("Error : " + error.localizedMessage, "")
        errorData.value = error.localizedMessage
    }
}