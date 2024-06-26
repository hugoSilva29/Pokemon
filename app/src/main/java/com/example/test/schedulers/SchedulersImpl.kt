package com.example.test.schedulers

import com.example.test.schedulers.SchedulersBase
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulersImpl: SchedulersBase {

    override fun io(): Scheduler = Schedulers.io()

    override fun compute(): Scheduler = Schedulers.computation()

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
}