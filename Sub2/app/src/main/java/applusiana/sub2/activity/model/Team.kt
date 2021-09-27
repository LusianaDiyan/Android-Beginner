package applusiana.sub2.activity.model

import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("TeamBadge")
        var teamBadge: String? = null
)