package com.example.naufalalf.submision2.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.example.naufalalf.submision2.Key.KEY
import com.example.naufalalf.submision2.R
import com.example.naufalalf.submision2.R.id.home
import com.example.naufalalf.submision2.api.ApiRepository
import com.example.naufalalf.submision2.model.MatchTeam
import com.example.naufalalf.submision2.model.Team
import com.example.naufalalf.submision2.presenter.Detail
import com.example.naufalalf.submision2.presenter.MatchDetail
import com.example.naufalalf.submision2.view.DetailView
import com.example.naufalalf.submision2.view.MainView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class DetailActivity:AppCompatActivity(),DetailView,MainView {

    private lateinit var homeId: String
    private lateinit var awayId: String
    private lateinit var eventId: String
    //home
    private lateinit var txtHomeName: TextView
    private lateinit var txtHomeScore: TextView
    private lateinit var imgHome: ImageView
    //away
    private lateinit var txtAwayName: TextView
    private lateinit var txtAwayScore: TextView
    private lateinit var imgAway: ImageView

    private lateinit var txtMatchDate: TextView
    private lateinit var progressBar: ProgressBar

    private lateinit var detail: Detail
    private lateinit var detailMatch: MatchDetail

    private lateinit var team: Team
    private lateinit var team2: Team
    private lateinit var match: MatchTeam

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var homeName: String
    private lateinit var awayName: String
    private lateinit var homeScore: String
    private lateinit var awayScore: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            verticalLayout {
                padding = dip(16)
                backgroundColor=Color.WHITE
                progressBar = progressBar {
                }.lparams{
                    gravity = Gravity.CENTER
                }
                verticalLayout {
                    txtMatchDate = textView {
                        id = R.id.match_date
                        textColor=0x00ff00.opaque
                    }.lparams {
                        width = wrapContent
                        height = wrapContent
                        gravity = Gravity.CENTER
                    }

                    linearLayout {
                        padding = dip(16)
                        orientation = LinearLayout.HORIZONTAL
                        txtHomeScore = textView {
                            id = R.id.home_score_match
                            textSize = 20f
                            gravity = Gravity.CENTER
                        }.lparams {
                            weight = 1f
                            rightMargin = 16
                        }

                        textView {
                            text = getString(R.string.vs)
                            gravity = Gravity.CENTER
                            textColor=Color.RED
                        }.lparams {
                            weight = 1f
                        }

                        txtAwayScore = textView {
                            id = R.id.away_score_match
                            textSize = 20f
                            gravity = Gravity.CENTER
                        }.lparams {
                            weight = 1f
                            leftMargin = 16
                        }
                    }.lparams {
                        gravity = Gravity.CENTER
                    }

                //atas
                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        // kiri
                        linearLayout {
                            orientation = LinearLayout.VERTICAL

                            imgHome = imageView {
                                id = R.id.img_home
                            }.lparams {
                                width = dip(150)
                                height = dip(150)
                            }

                            txtHomeName = textView {
                                id = R.id.home_name
                                textSize = 15f
                                textColor=Color.BLACK
                            }.lparams {
                                gravity = Gravity.CENTER
                            }
                        }.lparams {
                            weight = 1f
                        }

                        //kanan
                        verticalLayout {
                            imgAway = imageView {
                                id = R.id.img_away
                            }.lparams {
                                width = dip(150)
                                height = dip(150)
                            }

                            txtAwayName = textView {
                                id = R.id.away_name
                                textSize = 15f
                                textColor=Color.BLACK
                            }.lparams {
                                gravity = Gravity.CENTER
                            }
                        }.lparams {
                            weight = 1f
                        }
                    }

                }
            }
        val i = intent

        homeId = i.getStringExtra(KEY.HOME_ID_KEY)
        awayId = i.getStringExtra(KEY.AWAY_ID_KEY)
        eventId = i.getStringExtra(KEY.EVENT_ID_KEY)

        val request = ApiRepository()
        val gson = Gson()

        detail = Detail(this, request, gson)
        detailMatch = MatchDetail(this, request, gson)

        detail.getBadgeList(homeId, awayId)
        detailMatch.getDetailMatch(eventId)
    }
    private fun View.visible(){
        visibility = View.VISIBLE
    }

    private fun View.invisible(){
        visibility = View.GONE
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun showTeamsList(data: List<Team>?, data2: List<Team>?) {
        team = Team(data?.get(0)?.teamBadge)
        team2 = Team(data2?.get(0)?.teamBadge)
        Picasso.with(this).load(data?.get(0)?.teamBadge).into(imgHome)
        Picasso.with(this).load(data2?.get(0)?.teamBadge).into(imgAway)
    }

    override fun showMatchList(data: List<MatchTeam>?) {
        match = MatchTeam(data?.get(0)?.eventId,
                data?.get(0)?.homeName,
                data?.get(0)?.awayName,
                data?.get(0)?.homeScore,
                data?.get(0)?.awayScore,
                data?.get(0)?.dateEvent,
                data?.get(0)?.teamBadge,
                data?.get(0)?.homeId,
                data?.get(0)?.awayId)

        homeName = data?.get(0)?.homeName.toString()
        awayName = data?.get(0)?.awayName.toString()
        homeScore = data?.get(0)?.homeScore.toString()
        awayScore = data?.get(0)?.awayScore.toString()

        txtHomeName.text = data?.get(0)?.homeName
        txtHomeScore.text = data?.get(0)?.homeScore


        txtAwayName.text = data?.get(0)?.awayName
        txtAwayScore.text = data?.get(0)?.awayScore

        txtMatchDate.text = data?.get(0)?.dateEvent
    }
}