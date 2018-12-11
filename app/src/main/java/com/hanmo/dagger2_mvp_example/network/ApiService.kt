package com.hanmo.dagger2_mvp_example.network

import com.hanmo.dagger2_mvp_example.model.Repo
import com.hanmo.dagger2_mvp_example.model.UserInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/users/{username}")
    fun getUserInfo(@Path("username") userName: String): Single<UserInfo>

    @GET("/users/{username}/repos")
    fun getRepo(@Path("username") userName: String): Single<List<Repo>>

}