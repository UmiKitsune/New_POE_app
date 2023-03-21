package com.example.poe_app.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.poe_app.R
import com.example.poe_app.databinding.FragmentMainBinding
import com.example.poe_app.presentation.MainFragmentViewModel
import com.example.poe_app.presentation.ViewModelFactory
import com.example.poe_app.presentation.utils.MyStates
import com.example.poe_app.ui.adapter.MissionAdapter
import kotlin.properties.Delegates

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: MissionAdapter
    private lateinit var preferences: SharedPreferences
    private val args: MainFragmentArgs by navArgs()
    private val viewModel: MainFragmentViewModel by viewModels { ViewModelFactory(requireContext()) }
    private var count by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        setHasOptionsMenu(true)
        binding = FragmentMainBinding.inflate(inflater, container, false)
        adapter = MissionAdapter()

        viewModel.sendUserName(args.userName, args.language)

        viewModel.countCompleted.observe(viewLifecycleOwner, {
            count = it ?: 0
            (requireActivity() as AppCompatActivity).supportActionBar?.subtitle = "${getString(R.string.subtitle_txt)} $count / 40"
        })

        viewModel.dataFromDB.observe(viewLifecycleOwner, {
            if (it.isEmpty() && count != 40) {
                viewModel.sendUserName(args.userName, args.language)
            } else {
                adapter.missions = it
            }
        })

        binding.recyclerView.adapter = adapter

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        viewModel.state.observe(viewLifecycleOwner, {
            when(it) {
                is MyStates.Loading ->{
                    menu.findItem(R.id.reload).isVisible = false
                }
                is MyStates.Success ->{
                    menu.findItem(R.id.reload).isVisible = true
                }

            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.reload -> {
                viewModel.upLoad(args.userName, args.language)
            }
            R.id.deleteCompleted -> {
                if(count == 40) {
                    adapter.missions = emptyList()
                } else {
                    viewModel.deleteCompleted()
                }
            }
        }
        return super.onOptionsItemSelected(item)

    }

}
