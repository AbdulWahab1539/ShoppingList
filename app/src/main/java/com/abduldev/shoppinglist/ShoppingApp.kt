package com.abduldev.shoppinglist

import android.app.Application
import com.abduldev.shoppinglist.data.db.ShoppingDatabase
import com.abduldev.shoppinglist.data.repo.ShoppingRepo
import com.abduldev.shoppinglist.ui.shoppinglist.ShoppingViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ShoppingApp() : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ShoppingApp))
        bind() from singleton {
            ShoppingDatabase(instance())
        }

        bind() from singleton {
            ShoppingRepo(instance())
        }

        bind() from provider {
            ShoppingViewModelFactory(instance())
        }
    }


}