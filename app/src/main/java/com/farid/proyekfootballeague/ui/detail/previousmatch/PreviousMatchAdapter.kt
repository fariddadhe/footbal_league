package com.farid.proyekfootballeague.ui.detail.previousmatch

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farid.proyekfootballeague.R
import com.farid.proyekfootballeague.data.source.remote.response.EventEntity
import com.farid.proyekfootballeague.ui.detailmatch.DetailMatchActivity
import com.farid.proyekfootballeague.utils.getDate
import com.farid.proyekfootballeague.utils.getTime
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_match.view.*

class PreviousMatchAdapter(private val context: Context, private val match: List<EventEntity>) :
    RecyclerView.Adapter<PreviousMatchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_match, parent, false))

    override fun getItemCount(): Int = match.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMatch(match[position], context)
    }


    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindMatch(match: EventEntity, context: Context) {
            itemView.homeTeam.text = match.strHomeTeam
            itemView.homeScore.text = match.intHomeScore.toString()
            itemView.awayTeam.text = match.strAwayTeam
            itemView.awayScore.text = match.intAwayScore.toString()
            val date = getDate(match.dateEvent)
            val time = getTime(match.strTime)
            itemView.dateMatch.text = "${date} | ${time}"

            itemView.setOnClickListener {
                val intent = Intent(context, DetailMatchActivity::class.java)
                intent.putExtra(DetailMatchActivity.extra_event, match.idEvent.toString())
                context.startActivity(intent)
            }
        }

    }

}