package com.example.mvvm_tmdb.eventbus

import com.squareup.otto.Bus
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

object BusProvider{
    private var bus: BehaviorSubject<Int> = BehaviorSubject.create()

    fun setId(id:Int){
        bus.onNext(id)
    }

    fun getId(): Observable<Int>{
        return bus
    }
}


