package com.example.naufalalf.submision2.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.example.naufalalf.submision2.api.ApiRepository
import com.example.naufalalf.submision2.Key.KEY
import com.example.naufalalf.submision2.R
import com.example.naufalalf.submision2.R.array.utama
import com.example.naufalalf.submision2.adapter.ItemAdapter
import com.example.naufalalf.submision2.model.MatchTeam
import com.example.naufalalf.submision2.presenter.Main
import com.example.naufalalf.submision2.presenter.NextMatch
import com.example.naufalalf.submision2.view.MainView
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MainActivity : AppCompatActivity(), MainView {

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<MatchTeam>?) {
        swipeRefresh.isRefreshing = false
        events.clear()
        data?.let {
            events.addAll(data)
            adapter.notifyDataSetChanged()
        } ?: toast(getString(R.string.no_data))
    }

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var listMatch: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var adapter: ItemAdapter

    private lateinit var past: Main
    private lateinit var next: NextMatch
    private var events: MutableList<MatchTeam> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            lparams(width = matchParent, height = wrapContent)
            backgroundColor= Color.WHITE

            spinner = spinner{
                id = R.id.spinner
            }.lparams{
                width= matchParent
                topMargin = dip(16)
                rightMargin = dip(16)
                leftMargin = dip(16)

            }
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listMatch = recyclerView {
                        id = R.id.list_match
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
            val request = ApiRepository()
            val gson = Gson()
            past = Main(this, request, gson)
            next = NextMatch(this,request,gson)

            val spinnerItems = resources.getStringArray(utama)
            val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
            spinner.adapter = spinnerAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if (spinner.selectedItem == getString(R.string.past_match)) {
                        adapter = ItemAdapter(events){
                            startActivity<DetailActivity>(
                                    KEY.HOME_ID_KEY to it.homeId,
                                    KEY.AWAY_ID_KEY to it.awayId,
                                    KEY.EVENT_ID_KEY to it.eventId)
                        }

                        listMatch.adapter = adapter

                        past.getMatchList(getString(R.string.league_id))

                        swipeRefresh.onRefresh {
                            past.getMatchList(getString(R.string.league_id))
                        }
                    }else{
                        adapter = ItemAdapter(events) {
                            startActivity<DetailActivity>(
                                    KEY.HOME_ID_KEY to it.homeId,
                                    KEY.AWAY_ID_KEY to it.awayId,
                                    KEY.EVENT_ID_KEY to it.eventId)
                        }
                        listMatch.adapter = adapter
                        next.getMatchList(getString(R.string.league_id))
                        swipeRefresh.onRefresh {
                            next.getMatchList(getString(R.string.league_id))
                        }
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        private fun View.visible() {
            visibility = View.VISIBLE
        }
        private fun View.invisible() {
            visibility = View.INVISIBLE
        }
    }
