package com.example.naufalalf.submision2.view

import com.example.naufalalf.submision2.model.MatchTeam
import com.example.naufalalf.submision2.model.Team

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamsList(data: List<Team>?, data2: List<Team>?)
}