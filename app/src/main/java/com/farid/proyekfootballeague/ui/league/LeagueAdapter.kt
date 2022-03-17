package com.farid.proyekfootballeague.ui.league

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farid.proyekfootballeague.R
import com.farid.proyekfootballeague.data.source.remote.response.Leagues
import com.farid.proyekfootballeague.ui.detail.DetailLeagueActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_leagues.view.*

class LeagueAdapter(private val context: Context, private val league: List<Leagues>) :
    RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_leagues, parent, false))


    override fun getItemCount(): Int = league.size

    override fun onBindViewHolder(holder: LeagueAdapter.ViewHolder, position: Int) {
        holder.bindLeague(league[position], context)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindLeague(leagues: Leagues, context: Context) {
            itemView.tvLeagueName.text = leagues.name
            leagues.logo.let {
                Glide.with(itemView.context)
                    .load(it)
                    .into(itemView.imgLeagueLogo)
            }
            itemView.setOnClickListener {
                val intent = Intent(context, DetailLeagueActivity::class.java)
                intent.putExtra(DetailLeagueActivity.extra_id, leagues)
                context.startActivity(intent)
            }
        }
    }

}