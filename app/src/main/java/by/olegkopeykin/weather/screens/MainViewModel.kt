package by.olegkopeykin.weather.screens

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import by.olegkopeykin.interactors.pref.PrefInteractor
import by.olegkopeykin.model.domain.WeatherModel
import by.olegkopeykin.weather.common.BaseViewModel
import by.olegkopeykin.weather.common.SingleLiveEvent
import by.olegkopeykin.weather.screens.citydetails.CityDetailsRouter
import by.olegkopeykin.weather.screens.citylist.CityListRouter
import by.olegkopeykin.weather.screens.selectcity.SelectCityRouter
import by.olegkopeykin.weather.screens.splash.SplashRouter
import io.reactivex.android.schedulers.AndroidSchedulers

class MainViewModel(private val prefInteractor: PrefInteractor) : BaseViewModel(),
    SplashRouter,
    CityListRouter,
    CityDetailsRouter,
    SelectCityRouter {

    private val postNextScreen: SingleLiveEvent<Screens> = SingleLiveEvent()
    val nextScreen: LiveData<Screens>
        get() = postNextScreen

    init {
        prefInteractor.isLightMode()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }, {
                it.printStackTrace()
            })
            .toComposite()
    }

    override fun showCityList() {
        postNextScreen.value = Screens.CityList
    }

    override fun showSelectCity() {
        postNextScreen.value = Screens.SelectCity
    }

    override fun showCityDetails(weather: WeatherModel){
        postNextScreen.value = Screens.CityDetails(weather)
    }

    override fun onBackClick(){
        postNextScreen.value = Screens.PrevScreen()
    }

    override fun hideKeyboard() {
        postNextScreen.value = Screens.HideKeyboard
    }
}