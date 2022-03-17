package com.farid.proyekfootballeague.ui.favorite.previousmatch


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.farid.proyekfootballeague.R
import com.farid.proyekfootballeague.data.source.local.db.FavoriteMatch
import com.farid.proyekfootballeague.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite_previous_match.*

/**
 * A simple [Fragment] subclass.
 */
class FavoritePreviousMatchFragment : Fragment() {

    private var viewModel: FavoritePreviousMatchViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_previous_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = obtainViewModel(requireActivity())

        progress_bar.visibility = View.VISIBLE

        viewModel?.table = FavoriteMatch.TABLE_FAVORITE_MATCH
        viewModel?.context = requireContext()
        viewModel?.event = "previous"


    }

    override fun onResume() {
        super.onResume()
        getFavorite()
    }

    private fun getFavorite() {
        viewModel?.match?.observe(this, Observer { list ->
            rv_favorite_previousmatch.adapter = FavoritePreviousMatchAdapter(requireContext(), list)
            progress_bar.visibility = View.GONE
            rv_favorite_previousmatch.layoutManager = LinearLayoutManager(context)
        })
    }

    private fun obtainViewModel(activity: FragmentActivity): FavoritePreviousMatchViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory)
            .get(FavoritePreviousMatchViewModel::class.java)
    }


}
