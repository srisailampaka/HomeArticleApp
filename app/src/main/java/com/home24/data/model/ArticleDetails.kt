package com.home24.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleDetails {
    @SerializedName("resourceType")
    @Expose
    lateinit var resourceType: String
    @SerializedName("articlesCount")
    @Expose
    var articlesCount: Int = 0
    @SerializedName("_embedded")
    @Expose
    lateinit var embedded: Embedded
}
