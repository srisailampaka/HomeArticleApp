package com.home24.data.repository

import com.home24.data.model.ArticleDetails
import io.reactivex.Single

interface HomeRepository {
    fun getArticleList(): Single<ArticleDetails>

}