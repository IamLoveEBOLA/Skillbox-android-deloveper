package myapplication.Main.di


import dagger.Component
import myapplication.Main.presentation.MainViewModelFactory

@Component
interface AppComponent {

    fun mainViewModelFactory(): MainViewModelFactory

}