package applusiana.sub2.activity.presenter

import applusiana.sub2.activity.CarConProvier
import applusiana.sub2.activity.TeamResponse
import applusiana.sub2.activity.api.ApiRepository
import applusiana.sub2.activity.api.TheSupportDBApi
import applusiana.sub2.activity.view.MainView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class Main(private val anInterface: MainView, private val api: ApiRepository,
           private val gson: Gson, private val context: CarConProvier = CarConProvier()){

    fun getMatchList(match: String?){
        anInterface.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(api
                        .doRequest(TheSupportDBApi.getPastMatch(match)),
                        TeamResponse::class.java
                )
            }
            anInterface.showMatchList(data.await().events)
            anInterface.hideLoading()
        }
    }
}

