package com.example.naufalalf.submision2.presenter

import com.example.naufalalf.submision2.api.ApiRepository
import com.example.naufalalf.submision2.api.TheSportDBApi
import com.example.naufalalf.submision2.TeamResponse
import com.example.naufalalf.submision2.view.DetailView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg


class Detail(private val anInterface: DetailView, private val api: ApiRepository,
             private val gson: Gson){

    fun getBadgeList(team: String?, team2: String?){
        anInterface.showLoading()

        async(UI){
            val data = bg {
                gson.fromJson(api
                        .doRequest(TheSportDBApi.getHomeBadge(team)),
                        TeamResponse::class.java
                )
            }
            val data2 = bg {
                gson.fromJson(api.doRequest(TheSportDBApi.getAwayBadge(team2)),
                        TeamResponse::class.java
                )
            }

            anInterface.showTeamsList(data.await().teamId,data2.await().teamId)
            anInterface.hideLoading()
        }
    }
}