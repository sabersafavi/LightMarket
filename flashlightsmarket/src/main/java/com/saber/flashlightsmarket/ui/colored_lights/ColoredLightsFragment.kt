package com.saber.flashlightsmarket.ui.colored_lights

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.saber.flashlightsmarket.R
import com.saber.flashlightsmarket.base.BaseFragment
import com.saber.flashlightsmarket.custom.LoadingDialog
import com.saber.flashlightsmarket.databinding.FragmentColoredLightsBinding
import com.saber.flashlightsmarket.databinding.FragmentSosAlertsBinding
import com.saber.flashlightsmarket.model.LightApp
import com.saber.flashlightsmarket.model.Response
import com.saber.flashlightsmarket.ui.AppsAdapter
import com.saber.flashlightsmarket.ui.LightsAppViewModel
import com.saber.flashlightsmarket.utils.EndlessRecyclerOnScrollListener
import com.saber.flashlightsmarket.utils.enums.LightType
import com.saber.flashlightsmarket.utils.getErrorMessage
import com.saber.flashlightsmarket.utils.toast
import kotlinx.android.synthetic.main.fragment_sos_alerts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ColoredLightsFragment : BaseFragment<FragmentSosAlertsBinding>(),
    AppsAdapter.OnLightAppClickedListener {

    private val viewModel by viewModel<LightsAppViewModel>()
    private val lightsAdapter by lazy { AppsAdapter(this) }
    private val loadingDialog by lazy {
        LoadingDialog(requireActivity()).apply {
            lifecycle.addObserver(this)
        }
    }

    private val endlessRecyclerOnScrollListener by lazy {
        object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore(current_page: Int) {
                viewModel.getLightApps(LightType.ColoredLight)
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_sos_alerts

    override fun onAppClicked(app: LightApp) {
        var packageName = app.packageName
        context?.let {
            val launchIntent =
                it.packageManager.getLaunchIntentForPackage(packageName)
            if (launchIntent != null) {
                startActivity(launchIntent) //null pointer check in case package name was not found
            } else {
                // Bring user to the market or let them choose an app?
                var intent = Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.data = Uri.parse("market://details?id=$packageName");
                startActivity(intent);
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getDataBinding().appAdapter = lightsAdapter
        getDataBinding().viewModel = viewModel

        viewModel.items.observe(viewLifecycleOwner, { response ->

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
        //
        viewModel.getLightApps(LightType.ColoredLight)
    }
}