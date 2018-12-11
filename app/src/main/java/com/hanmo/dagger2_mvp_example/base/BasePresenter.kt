package com.hanmo.dagger2_mvp_example.base

interface BasePresenter<T> {

    fun onViewCreated(view: T)

    fun onViewDestroyed()

}