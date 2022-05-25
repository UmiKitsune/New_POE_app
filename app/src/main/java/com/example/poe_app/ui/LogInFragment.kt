package com.example.poe_app.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.poe_app.R
import com.example.poe_app.databinding.FragmentLoginBinding
import com.example.poe_app.presentation.LogInViewModel
import com.example.poe_app.presentation.utils.MyStates
import kotlin.properties.Delegates.notNull


class LogInFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var preferences: SharedPreferences
    private var userName by notNull<String>()
    private var language by notNull<String>()
    private val viewModel: LogInViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity?)?.supportActionBar?.hide()
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        (requireActivity() as AppCompatActivity).supportActionBar?.subtitle = ""

        preferences = requireActivity().getSharedPreferences(POE_APP_PREFERENCES, Context.MODE_PRIVATE)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, ::checkFields)

        binding.loginBtn.setOnClickListener {
            language = if (binding.languageSwitch.isChecked){
                "www"
            } else {
                "ru"
            }
            userName = binding.loginUsername.text.toString().trim()

            preferences.edit()
                .putString(PREF_USERNAME_KEY, userName)
                .putString(PREF_LANGUAGE_KEY, language)
                .apply()

            viewModel.onLoginBtnOnClick(userName)
        }
    }


    private fun checkFields(state: MyStates) {
        when (state) {
            is MyStates.EmptyField -> emptyField()
            is MyStates.FilledField -> fillField()
        }
    }

    private fun emptyField() {
        binding.loginText.text = getString(R.string.empty_name_error)
    }

    private fun fillField() {
        val action = LogInFragmentDirections.actionLogInFragmentToMainFragment(
            userName = userName,
            language = language
        )
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.findItem(R.id.reload).isVisible = false
        menu.findItem(R.id.deleteCompleted).isVisible = false
        super.onCreateOptionsMenu(menu, inflater)
    }

}