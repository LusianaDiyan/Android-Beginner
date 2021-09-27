package com.example.naufalalf.submision2.api

import android.net.Uri
import com.example.naufalalf.submision2.BuildConfig

object TheSportDBApi {
    fun getNextMatch(nextLeague: String?): String{
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnextleague.php?id=" + nextLeague
    }

    fun getPastMatch(pastLeague: String?): String{
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php?id=" + pastLeague
    }

    fun getAwayBadge(detailLeague: String?): String{
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=" + detailLeague
    }

    fun getHomeBadge(detailLeague: String?): String{
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=" + detailLeague
    }

    fun getDetailMatch(detailMatch: String?): String{
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupevent.php?id=" + detailMatch
    }
}