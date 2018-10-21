package com.home24.data.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "article")
class ArticleEntity constructor(sku: String, title: String, uri: String, state: Int) {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "sku")
    @NonNull
    var sku: String? = sku

    @ColumnInfo(name = "title")
    var title: String? = title


    @ColumnInfo(name = "uri")
    var uri: String? = uri

    @ColumnInfo(name = "state")
    var state: Int? = state
}