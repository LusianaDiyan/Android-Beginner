package applusiana.sub2.activity.view

import applusiana.sub2.activity.model.MatchTeam

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data : List<MatchTeam>?)

}
