package com.example.naufalalf.submision2.model

import com.google.gson.annotations.SerializedName

data class MatchTeam(

        @SerializedName("idEvent")
        var eventId: String? = null,

        @SerializedName("strHomeTeam")
        var homeName: String? = null,

        @SerializedName("strAwayTeam")
        var awayName: String? = null,

        @SerializedName("intHomeScore")
        var homeScore: String? = null,

        @SerializedName("intAwayScore")
        var awayScore: String? = null,

        @SerializedName("dateEvent")
        var dateEvent: String? = null,

        @SerializedName("strTeamBadge")
        var teamBadge: String? = null,

        @SerializedName("idHomeTeam")
        var homeId: String? = null,

        @SerializedName("idAwayTeam")
        var awayId: String? = null
)