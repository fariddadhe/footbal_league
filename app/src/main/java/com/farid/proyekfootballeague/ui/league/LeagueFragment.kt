package com.farid.proyekfootballeague.ui.league


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.farid.proyekfootballeague.R
import com.farid.proyekfootballeague.ui.favorite.FavoriteMainActivity
import com.farid.proyekfootballeague.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_league.*

/**
 * A simple [Fragment] subclass.
 */
class LeagueFragment : Fragment() {

    private var viewModel: LeagueViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the activity_detail_league for this fragment
        return inflater.inflate(R.layout.fragment_league, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = obtainViewModel(requireActivity())

        viewModel?.league?.observe(this, Observer { list ->
            rv_league.adapter = LeagueAdapter(requireContext(), list)
            rv_league.layoutManager = GridLayoutManager(requireContext(), 2)
        })

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()

        inflater.inflate(R.menu.main_menu, menu)

        val itemSearch = menu.findItem(R.id.search)
        itemSearch.isVisible = false

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.favorite) {
            val intent = Intent(context, FavoriteMainActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun obtainViewModel(activity: FragmentActivity): LeagueViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)

        return ViewModelProviders.of(activity, factory).get(LeagueViewModel::class.java)
    }

    companion object {
        fun newInstance(): LeagueFragment {
            return LeagueFragment()
        }
    }
}
