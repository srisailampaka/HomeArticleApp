package com.home24.data.repository

import com.home24.data.HomeServices
import com.home24.data.model.ArticleDetails
import io.reactivex.Single

/**
 * Implementation class for Home Repository
 */
class HomeRepositoryImpl(private val service: HomeServices) : HomeRepository {


    override fun getArticleList(): Single<ArticleDetails> {

        return service.getArticleList()


    }

}