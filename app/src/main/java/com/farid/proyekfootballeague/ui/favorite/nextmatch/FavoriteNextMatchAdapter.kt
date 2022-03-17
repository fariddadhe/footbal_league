package com.farid.proyekfootballeague.ui.favorite.nextmatch

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farid.proyekfootballeague.R
import com.farid.proyekfootballeague.data.source.local.db.FavoriteMatch
import com.farid.proyekfootballeague.ui.detailmatch.DetailMatchActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_match.view.*

class FavoriteNextMatchAdapter(
    private val context: Context,
    private val match: List<FavoriteMatch>
) : RecyclerView.Adapter<FavoriteNextMatchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_match, parent, false))

    override fun getItemCount(): Int = match.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindFavorite(match[position], context)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindFavorite(match: FavoriteMatch, context: Context) {
            itemView.homeTeam.text = match.homeTeam
            itemView.homeScore.text = "?"
            itemView.awayTeam.text = match.awayTeam
            itemView.awayScore.text = "?"
            val date = match.dateEvent
            val time = match.time
            itemView.dateMatch.text = "${date} | ${time}"

            itemView.setOnClickListener {
                val intent = Intent(context, DetailMatchActivity::class.java)
                intent.putExtra(DetailMatchActivity.extra_parcel, match)
                context.startActivity(intent)
            }
        }

    }

}