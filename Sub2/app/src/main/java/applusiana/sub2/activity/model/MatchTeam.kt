package applusiana.sub2.activity.model

import com.google.gson.annotations.SerializedName

data class MatchTeam(
        @SerializedName("idEvent")
        var eventId: String? = null,

        @SerializedName("HomeTeam")
        var homeName: String? = null,

        @SerializedName("AwayTeam")
        var awayName: String? = null,

        @SerializedName("HomeScore")
        var homeScore: String? = null,

        @SerializedName("AwayScore")
        var awayScore: String? = null,

        @SerializedName("dateEvent")
        var dateEvent: String? = null,

        @SerializedName("TeamBadge")
        var teamBadge: String? = null,

        @SerializedName("idHomeTeam")
        var homeId: String? = null,

        @SerializedName("idAwayTeam")
        var awayId: String? = null
)