package applusiana.sub2.activity.presenter

import applusiana.sub2.activity.TeamResponse
import applusiana.sub2.activity.api.ApiRepository
import applusiana.sub2.activity.api.TheSupportDBApi
import applusiana.sub2.activity.model.Team
import applusiana.sub2.activity.view.DetailView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailMatch(private val anInterface: DetailView, private val api: ApiRepository,
                  private val gson: Gson){

    fun getBadgeList(team: String?, team2: String?){
        anInterface.showLoading()

        async(UI) {
            val data = bg{
                gson.fromJson(api
                        .doRequest(TheSupportDBApi.getHomeBadge(team)),
                        TeamResponse::class.java
                )
            }

            val data2 = bg{
                gson.fromJson(api
                        .doRequest(TheSupportDBApi.getHomeBadge(team2)),
                        TeamResponse::class.java
                )
            }
            anInterface.showTeamsList(data.await().teamId,data2.await().teamId)
            anInterface.hideLoading()
        }
    }

    fun getAwayBadgeList(team: String?, team2: String?){
        anInterface.showLoading()

        async (UI){
            val away = bg {
                gson.fromJson(api
                        .doRequest(TheSupportDBApi.getAwayBadge(team)),
                        TeamResponse::class.java
                )
            }

            val dtaway2 = bg {
                gson.fromJson(api
                        .doRequest(TheSupportDBApi.getAwayBadge(team2)),
                        TeamResponse::class.java
                )
            }
            anInterface.showTeamsList(away.await().teamId,dtaway2.await().teamId)
            anInterface.hideLoading()
        }
    }
}
