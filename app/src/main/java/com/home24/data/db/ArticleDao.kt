package com.home24.data.db

import android.arch.persistence.room.*
import io.reactivex.Single


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<ArticleEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: ArticleEntity)

    @Update
    fun update(article: ArticleEntity)

    @Delete
    fun delete(article: ArticleEntity)

    @Query("Select * FROM article")
    fun getAllArticles(): Single<List<ArticleEntity>>


    @Query("Select * FROM article WHERE sku = :sku")
    fun getSelectedArticle(sku: String): ArticleEntity

    /**
     * Update article status
     */
    @Query("UPDATE article SET state=:state WHERE sku = :sku")
    fun updateArticle(sku: String, state: Int)
}