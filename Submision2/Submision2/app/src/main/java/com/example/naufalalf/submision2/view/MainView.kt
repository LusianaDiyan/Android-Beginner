package com.example.naufalalf.submision2.view

import com.example.naufalalf.submision2.model.MatchTeam

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<MatchTeam>?)
}