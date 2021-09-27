package com.example.naufalalf.submision2.adapter
import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.naufalalf.submision2.model.MatchTeam
import com.example.naufalalf.submision2.R
import com.example.naufalalf.submision2.R.id.*
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.sdk25.listeners.onClick

class ItemAdapter (private val event: List<MatchTeam>, private val listener: (MatchTeam) -> Unit)
    : RecyclerView.Adapter<ItemAdapter.MatchTeamViewHolder>(){

    override fun onBindViewHolder(holder: MatchTeamViewHolder, position: Int) {
        holder.bindItems(event[position], listener)
    }

    override fun getItemCount(): Int = event.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchTeamViewHolder {
        return MatchTeamViewHolder(MatchUI().createView(AnkoContext.create(parent.context, parent)))
    }


    class MatchUI : AnkoComponent<ViewGroup>{
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
                cardView{
                    id = R.id.cv_item
                    backgroundColor=Color.LTGRAY
                    lparams(width = matchParent, height = wrapContent){
                        topMargin = dip(16)
                        rightMargin = dip(16)
                        leftMargin = dip(16)
                    }
                    verticalLayout {
                        padding = dip(16)

                        textView {
                            id = R.id.match_date
                            gravity = Gravity.CENTER
                            textColor= 0x00ff00.opaque
                        }.lparams {
                            width = matchParent
                        }

                            linearLayout {
                                lparams(width = matchParent, height = wrapContent)
                                orientation = LinearLayout.HORIZONTAL

                                textView {
                                    id = R.id.home_name
                                    gravity = Gravity.CENTER
                                    textColor= Color.BLACK
                                }.lparams {
                                    width = matchParent
                                    weight = 1f
                                }

                                textView {
                                    id = R.id.score_match
                                    gravity = Gravity.CENTER
                                    textColor=Color.BLACK
                                }.lparams {
                                    width = matchParent
                                    weight = 1f
                                }

                                textView {
                                    id = R.id.away_name
                                    gravity = Gravity.CENTER
                                    textColor=Color.BLACK
                                }.lparams {
                                    width = matchParent
                                    weight = 1f
                                }
                            }
                        }
                    }
                }
            }
        }

    class MatchTeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val homeName: TextView = view.find(home_name)
        private val awayName: TextView = view.find(away_name)
        private val score: TextView = view.find(score_match)
        private val matchDate: TextView = view.find(match_date)
        val cv: CardView = view.find(cv_item)

        fun bindItems(event: MatchTeam, listener: (MatchTeam) -> Unit){
            homeName.text = event.homeName
            awayName.text = event.awayName
            if(event.homeScore != null){
                score.text = event.homeScore + " VS " + event.awayScore
            }else{
                score.text = "    VS    "
            }
            matchDate.text = event.dateEvent
            cv.onClick{
                listener(event)
            }
        }
    }
}