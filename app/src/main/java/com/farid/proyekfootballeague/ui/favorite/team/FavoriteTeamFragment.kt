package com.farid.proyekfootballeague.ui.favorite.team


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.farid.proyekfootballeague.R
import com.farid.proyekfootballeague.data.source.local.db.FavoriteTeam
import com.farid.proyekfootballeague.data.source.remote.response.TeamsEntity
import com.farid.proyekfootballeague.ui.favorite.previousmatch.FavoritePreviousMatchViewModel
import com.farid.proyekfootballeague.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite_team.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTeamFragment : Fragment() {

    private var viewModel: FavoriteTeamViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = obtainViewModel(requireActivity())

        progress_bar.visibility = View.VISIBLE

        viewModel?.table = FavoriteTeam.TABLE_FAVORITE_TEAM
        viewModel?.context = requireContext()
    }

    override fun onResume() {
        super.onResume()
        getFavorite()
    }

    private fun getFavorite(){
        viewModel?.team?.observe(this, Observer { list ->
            rv_favorite_team.adapter = FavoriteTeamAdapter(requireContext(), list)
            progress_bar.visibility = View.GONE
            rv_favorite_team.layoutManager = GridLayoutManager(requireContext(), 2)
        })
    }

    private fun obtainViewModel(activity: FragmentActivity): FavoriteTeamViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory)
            .get(FavoriteTeamViewModel::class.java)
    }

}
