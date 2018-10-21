package com.home24.data.model
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose


class Embedded {
    @SerializedName("articles")
    @Expose
    lateinit var articles:List<Article>
}