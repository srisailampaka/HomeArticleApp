package com.home24.data

import com.home24.data.model.ArticleDetails
import com.home24.utils.CommonUtil
import com.home24.utils.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers


interface HomeServices {
    @Headers("Content-type: application/json")
    @GET(Constants.API_ARTICLE_URL)
    fun getArticleList(): Single<ArticleDetails>


}