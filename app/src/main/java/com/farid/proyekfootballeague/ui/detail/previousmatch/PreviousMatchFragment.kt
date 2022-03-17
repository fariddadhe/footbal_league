package com.farid.proyekfootballeague.ui.detail.previousmatch


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
import com.farid.proyekfootballeague.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_previous_match.*

/**
 * A simple [Fragment] subclass.
 */
class PreviousMatchFragment : Fragment() {

    private var viewModel: PreviousMatchViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the activity_detail_league for this fragment
        return inflater.inflate(R.layout.fragment_previous_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = obtainViewModel(requireActivity())

        progress_bar.visibility = View.VISIBLE

        viewModel?.id = idLeague

        viewModel?.match?.observe(this, Observer { list ->
            rv_previousmatch.adapter = PreviousMatchAdapter(requireContext(), list)
            progress_bar.visibility = View.GONE
            rv_previousmatch.layoutManager = LinearLayoutManager(context)
        })
    }

    companion object {
        fun newInstance(): PreviousMatchFragment {
            return PreviousMatchFragment()
        }

        var idLeague = ""
    }

    private fun obtainViewModel(activity: FragmentActivity): PreviousMatchViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(PreviousMatchViewModel::class.java)
    }


}
