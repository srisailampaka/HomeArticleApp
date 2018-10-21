package com.home24.ui.main.selection

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log.e
import com.home24.data.db.ArticleDao
import com.home24.data.db.ArticleEntity
import com.home24.data.model.ArticleDetails
import com.home24.data.repository.HomeRepository
import com.home24.ui.main.States
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * this model class for SelectFragment
 */
class SelectionViewModel @Inject constructor(private val repo: HomeRepository, private val dbRepository: ArticleDao) : ViewModel() {
    val articleListData = MutableLiveData<List<ArticleEntity>>()
    val errorData = MutableLiveData<String>()


    /**
     * Method for get the Article list
     */
    fun getArticleListDetails() {
        repo.getArticleList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onArticleResponseRecieved, this::onError)
    }

    /**
     * Method for update the Article
     * @param sku
     * @param state
     */
    fun updateArticleDetails(sku: String, state: Int) {

        dbRepository.updateArticle(sku, state)

    }

    /**
     * Method for update the Article
     * @param sku
     * @return ArticleEntity
     */
    fun getSelectedArticleDetails(sku: String): ArticleEntity {

        return dbRepository.getSelectedArticle(sku)

    }

    private fun onError(error: Throwable) {
        e("Error : " + error.localizedMessage, "")
        errorData.value = error.localizedMessage
    }

    private fun onArticleResponseRecieved(articleDetails: ArticleDetails) {
        var articleEntityList = ArrayList<ArticleEntity>()
        for (article in articleDetails.embedded.articles) {
            articleEntityList!!.add(ArticleEntity(article.sku, article.title, article.mediaList.get(0).uri, States.NOT_SELECTED.value))
        }
        dbRepository.insertAll(articleEntityList!!)
        dbRepository.getAllArticles().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccessDb, this::onError)

    }

    private fun onSuccessDb(articleEntityList: List<ArticleEntity>) {
        articleListData.postValue(articleEntityList)
    }


}