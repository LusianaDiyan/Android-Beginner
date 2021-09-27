package com.example.naufalalf.submision2

import com.example.naufalalf.submision2.model.MatchTeam
import com.example.naufalalf.submision2.model.Team
import com.google.gson.annotations.SerializedName

data class TeamResponse(
        @field:SerializedName("events")
        val events: List<MatchTeam>? = null,

        @field:SerializedName("teams")
        val teamId: List<Team>? = null
)
