package com.morostami.archsample.ui.widgets
//
//import android.content.Context
//import android.os.Build
//import android.os.Bundle
//import android.view.View
//import android.view.ViewGroup
//import android.widget.LinearLayout
//import androidx.appcompat.app.AppCompatDelegate
//import androidx.core.content.ContextCompat
//import androidx.core.view.children
//import androidx.lifecycle.lifecycleScope
//import com.morostami.archsample.R
//import com.morostami.archsample.utils.dpToPx
//import com.roundtableapps.cloudrake.R
//import com.roundtableapps.cloudrake.common.base.BaseBottomSheetFragment
//import com.roundtableapps.cloudrake.common.custom.CustomToggleView
//import com.roundtableapps.cloudrake.common.extensions.dpToPx
//import com.roundtableapps.cloudrake.common.viewBinding
//import com.roundtableapps.cloudrake.databinding.FragmentSettingsThemeBinding
//import com.roundtableapps.cloudrake.ui.setting.SettingViewModel
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//import org.koin.androidx.viewmodel.ext.sharedViewModel

//class ThemeSettingBottomsheet : BaseBottomSheetFragment<SettingViewModel>(mExpandState = false) {
//
////    override val viewModel: SettingViewModel by sharedViewModel()
//    override val viewId: Int = R.layout.fragment_settings_theme
//    private val binding by viewBinding(FragmentSettingsThemeBinding::bind)
//
//    val onOptionItemClickedListener: (Int) -> Unit = { themeMode ->
//        binding.llOptionsLayout.children.forEach { view ->
////            (view as? CustomToggleView)?.isChecked = view.id == themeMode
//        }
//        dismissAndChangeTheme(mode = themeMode)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setupViews()
//        setObservables()
//    }
//
//    private fun setupViews() {
//        val themeMode: Int = viewModel.themeMode
//
//        val themeOptions: ArrayList<Pair<Int, String>> = arrayListOf(
//                Pair(
//                        AppCompatDelegate.MODE_NIGHT_NO,
//                        getThemeState(AppCompatDelegate.MODE_NIGHT_NO)
//                ),
//                Pair(
//                        AppCompatDelegate.MODE_NIGHT_YES,
//                        getThemeState(AppCompatDelegate.MODE_NIGHT_YES)
//                )
//
//        )
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            themeOptions.add(0, Pair(
//                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM,
//                    getThemeState(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
//            ))
//        }
//
//        themeOptions.forEachIndexed { index, triple ->
//            val option: CustomToggleView = createOptionItem(requireContext(), triple.second, index, triple.first)
//            binding.llOptionsLayout.addView(option, index)
//        }
//        updateOptionsView(themeMode)
//    }
//
//    private fun setObservables() {
//        viewModel.themeModeObservable.observe(viewLifecycleOwner, { theme ->
//            theme.let { mode ->
//                updateOptionsView(mode)
//            }
//        })
//    }
//
//    private fun updateOptionsView(mode: Int) {
//        binding.llOptionsLayout.children.forEach { view ->
//            (view as? CustomToggleView)?.isChecked = view.id != mode
//        }
//    }
//
//    private fun dismissAndChangeTheme(mode: Int) {
//        dismiss()
//        GlobalScope.launch(Dispatchers.Main) {
//            delay(100)
//            viewModel.changeTheme(mode)
//        }
//    }
//
//    private fun getThemeState(mode: Int) : String {
//        return when(mode) {
//            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> {
//                getString(R.string.use_system_theme)
//            }
//
//            AppCompatDelegate.MODE_NIGHT_NO -> {
//                getString(R.string.light)
//            }
//
//            AppCompatDelegate.MODE_NIGHT_YES -> {
//                getString(R.string.dark)
//            }
//
//            else -> {
//                getString(R.string.use_system_theme)
//            }
//        }
//    }
//
//    private fun createOptionItem(context: Context, title: String, position: Int, themeMode: Int) : CustomToggleView {
//        val optionView: CustomToggleView = CustomToggleView(context).apply {
//            id = themeMode
//            startTitle = title
//            showEndTitle = false
//            selectedIconRes = R.drawable.ic_checked_circle
//            deselectedIconRes = R.drawable.ic_empty_circle
//            showEndTitle = false
//            showEndImage = true
//            isChecked = false
//            isSelected = false
//            imgEndIcon?.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_empty_circle))
//
//            layoutParams = LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    dpToPx(55.0f).toInt()
//            )
//            this.setOnClickListener {
//                isChecked = true
//                onOptionItemClickedListener.invoke(themeMode)
//            }
//        }
//        return optionView
//    }
//}