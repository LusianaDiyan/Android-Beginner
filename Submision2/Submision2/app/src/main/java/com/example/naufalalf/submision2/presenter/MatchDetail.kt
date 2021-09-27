package com.example.naufalalf.submision2.presenter

import com.example.naufalalf.submision2.api.ApiRepository
import com.example.naufalalf.submision2.api.TheSportDBApi
import com.example.naufalalf.submision2.CCProvier
import com.example.naufalalf.submision2.TeamResponse
import com.example.naufalalf.submision2.view.MainView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchDetail(private val anInterface: MainView, private val api: ApiRepository,
                  private val gson: Gson, private val context: CCProvier = CCProvier()){

    fun getDetailMatch(team: String?){
        anInterface.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(api
                        .doRequest(TheSportDBApi.getDetailMatch(team)),
                        TeamResponse::class.java
                )
            }
            anInterface.showMatchList(data.await().events)
            anInterface.hideLoading()
        }
    }
}