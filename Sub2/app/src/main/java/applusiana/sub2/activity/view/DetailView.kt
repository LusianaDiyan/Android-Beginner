package applusiana.sub2.activity.view

import applusiana.sub2.activity.model.Team

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamsList(data: List<Team>?, data2: List<Team>?)
}
