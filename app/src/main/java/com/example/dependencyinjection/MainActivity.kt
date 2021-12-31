package com.example.dependencyinjection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var someClass: SomeOtherThing

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text = findViewById<TextView>(R.id.textViewText)
        text.text = someClass.otherThing()
    }


}
//class SomeClass
//    @Inject
//    constructor(
//
//        private var someOtherThing: SomeOtherThingInterface
//    )  {
//
//        fun doOtherThing():String{
//             return someOtherThing.otherThing()
//        }
//
//}
class SomeOtherThing @Inject constructor(): SomeOtherThingInterface{
    override fun otherThing():String {
        return "hey i did a thing !!!"
    }
}

interface SomeOtherThingInterface{
    fun otherThing():String
}

@Module
@InstallIn(ActivityComponent::class)
abstract class MyModule{

//    @Singleton
//    @Provides
//    fun provideInterface():SomeOtherThingInterface{
//        return SomeOtherThing()
//    }

    @Singleton
    @Binds
    abstract fun bindSomeOtherThingInterface(someOtherThing: SomeOtherThing) :SomeOtherThingInterface
}