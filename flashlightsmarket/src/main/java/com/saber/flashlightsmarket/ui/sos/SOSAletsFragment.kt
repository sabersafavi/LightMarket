package com.saber.flashlightsmarket.ui.sos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.utils.EndlessRecyclerOnScrollListener
import com.saber.flashlightsmarket.R
import com.saber.flashlightsmarket.base.BaseFragment
import com.saber.flashlightsmarket.custom.LoadingDialog
import com.saber.flashlightsmarket.databinding.FragmentSosAlertsBinding
import com.saber.flashlightsmarket.model.LightApp
import com.saber.flashlightsmarket.model.Response
import com.saber.flashlightsmarket.ui.AppsAdapter
import com.saber.flashlightsmarket.utils.getErrorMessage
import com.saber.flashlightsmarket.utils.toast
import kotlinx.android.synthetic.main.fragment_sos_alerts.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.HttpException

class SOSAletsFragment : BaseFragment<FragmentSosAlertsBinding>(),
    AppsAdapter.OnLightAppClickedListener {

    private val sosAlertViewModel by viewModel<SOSAlertViewModel>()
    private val lightsAdapter by lazy { AppsAdapter(this) }
    private val loadingDialog by lazy {
        LoadingDialog(requireActivity()).apply {
            lifecycle.addObserver(this)
        }
    }

    private val endlessRecyclerOnScrollListener by lazy {
        object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore(current_page: Int) {
                sosAlertViewModel.getSOSAlerts()
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_sos_alerts

    override fun onAppClicked(app: LightApp) {
        app.name.toast(requireActivity())
//        if (findNavController().currentDestination?.id == R.id.pullRequestsFragment) {
//            findNavController().navigate(
//                PullRequestsFragmentDirections.actionPullRequestsFragmentToWebViewFragment(
//                    pullRequest.html_url
//                )
//            )
//        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getDataBinding().appAdapter = lightsAdapter
        getDataBinding().viewModel = sosAlertViewModel

        sosAlertViewModel.items.observe(viewLifecycleOwner, Observer { response ->

            when (response) {
                is Response.Loading -> {
                    if (!pullRequestsRefresh.isRefreshing) {
                        loadingDialog.toggle(response.status)
                    }
                }
                is Response.Success -> {
                    lightsAdapter.addItems(response.data)
                    pullRequestsRefresh.isRefreshing = false
                    loadingDialog.toggle(false)
                }

                is Response.Error -> {
                    response.throwable?.getErrorMessage(requireContext())?.toast(requireContext())
                    response.data?.let { pullRequestsList ->
                        lightsAdapter.addItems(pullRequestsList)
                    }
                    pullRequestsRefresh.isRefreshing = false
                    loadingDialog.toggle(false)
                }

            }

        })


        rvItems.addOnScrollListener(endlessRecyclerOnScrollListener)

        setHasOptionsMenu(true)
    }

}