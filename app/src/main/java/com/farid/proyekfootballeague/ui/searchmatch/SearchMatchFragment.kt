package com.farid.proyekfootballeague.ui.searchmatch


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.farid.proyekfootballeague.R
import com.farid.proyekfootballeague.data.source.remote.response.EventEntity
import com.farid.proyekfootballeague.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_search_match.*

/**
 * A simple [Fragment] subclass.
 */
class SearchMatchFragment : Fragment() {

    private var viewModel: SearchMatchViewModel? = null
    private var listSearch: ArrayList<EventEntity>? = arrayListOf()
    private val resultSearch: String? = "state_search"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the activity_detail_league for this fragment
        return inflater.inflate(R.layout.fragment_search_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = obtainViewModel(requireActivity())

        showLoading(true)
        viewModel?.defaultSearch?.observe(this, Observer { list ->
            listSearch = list as ArrayList<EventEntity>?
            setAdapter()
        })

        if (savedInstanceState != null) {
            listSearch = savedInstanceState.getParcelableArrayList(resultSearch)
            setAdapter()
        }

        setHasOptionsMenu(true)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(resultSearch, listSearch)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()

        inflater.inflate(R.menu.main_menu, menu)

        val itemFavorite = menu.findItem(R.id.favorite)
        itemFavorite.isVisible = false

        val itemSearch = menu.findItem(R.id.search)

        val searchView: SearchView = itemSearch.actionView as SearchView

        searchView.queryHint = "Search Match"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                showLoading(true)
                searchMatch(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.toString().isNotEmpty()) {
                    showLoading(true)
                    searchMatch(newText.toString())
                }
                return true
            }

        })
    }

    private fun searchMatch(query: String) {
        viewModel?.query = query

        viewModel?.search?.observe(this, Observer { list ->
            if (list.isNullOrEmpty()) {
                showLoading(false)
                Toast.makeText(context, "data tidak ditemukan", Toast.LENGTH_SHORT).show()
            } else {
                listSearch = list as ArrayList<EventEntity>?
                setAdapter()
            }
        })
    }

    private fun setAdapter() {
        showLoading(false)
        if (listSearch != null) {
            rv_search_match.adapter = SearchMatchAdapter(requireContext(), listSearch!!)
            rv_search_match.layoutManager = LinearLayoutManager(context)
        }

    }

    companion object {
        fun newInstance(): SearchMatchFragment {
            return SearchMatchFragment()
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): SearchMatchViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)

        return ViewModelProviders.of(activity, factory).get(SearchMatchViewModel::class.java)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.INVISIBLE
        }
    }
}
