package applusiana.sub2.activity

import applusiana.sub2.activity.model.MatchTeam
import applusiana.sub2.activity.model.Team
import com.google.gson.annotations.SerializedName

data class TeamResponse(
        @field:SerializedName("events")
        val events: List<MatchTeam>? = null,

        @field:SerializedName("teams")
        val teamId: List<Team>? = null
)
