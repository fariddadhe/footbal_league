package com.farid.proyekfootballeague.ui.detail.team

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farid.proyekfootballeague.R
import com.farid.proyekfootballeague.data.source.remote.response.TeamsEntity
import com.farid.proyekfootballeague.ui.detail.detailteam.DetailTeamActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_team.view.*

class TeamAdapter(private val context: Context, private val team: List<TeamsEntity>) :
    RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_team, parent, false))

    override fun getItemCount(): Int = team.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTeam(team[position], context)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindTeam(team: TeamsEntity, context: Context){
            Glide.with(context)
                .load(team.strTeamBadge)
                .into(itemView.imgTeamLogo)
            itemView.tvTeamName.text = team.strTeam

            itemView.setOnClickListener {
                val intent = Intent(context, DetailTeamActivity::class.java)
                intent.putExtra(DetailTeamActivity.extra_team, team)
                context.startActivity(intent)
            }
        }
    }
}