package com.example.test.schedulers

import io.reactivex.Scheduler

interface SchedulersBase {

    fun io(): Scheduler

    fun ui(): Scheduler

    fun compute(): Scheduler

}