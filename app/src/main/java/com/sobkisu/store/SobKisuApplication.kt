package com.sobkisu.store

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class SobKisuApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)

    }

    fun getTestConfigaration(): RealmConfiguration {
        return RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
    }

}