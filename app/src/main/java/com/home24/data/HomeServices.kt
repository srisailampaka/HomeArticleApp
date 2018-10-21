package com.home24.data

import com.home24.data.model.ArticleDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers


interface HomeServices {
    @Headers("Content-type: application/json")
    @GET("articles?appDomain=1&locale=de_DE&limit=10")
    fun getArticleList(): Single<ArticleDetails>


}