package com.androiddev.astrohelpme.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.data.local.AppPreference
import com.androiddev.astrohelpme.databinding.ActivityDashboardBinding
import com.androiddev.astrohelpme.ui.fragment.DashboardFragment
import com.androiddev.astrohelpme.ui.fragment.match_making.MatchMakingRequestFragment
import com.androiddev.astrohelpme.ui.fragment.register_astrologer.RegisterAstrologerFragment
import com.androiddev.astrohelpme.ui.fragment.show_kundli.ShowKundliDataFragment
import com.androiddev.astrohelpme.utils.helper.LocaleHelper
import com.androiddev.astrohelpme.utils.helper.LocaleHelper.setLocale
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashboardActivity @Inject constructor() : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var drawerLayout: DrawerLayout

    @Inject
    lateinit var appPreference: AppPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize DrawerLayout
        drawerLayout = binding.drawerLayout
        if (savedInstanceState == null) {
            loadFragment(DashboardFragment())
        }
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> loadFragment(DashboardFragment())
                R.id.nav_astrologer -> loadFragment(ShowKundliDataFragment())
                R.id.nav_registerastrologer -> loadFragment(RegisterAstrologerFragment())
                R.id.nav_hindiLanguage -> changeLanguageHindi()
                R.id.nav_englishLanguage -> changeLanguageEnglish()
                R.id.nav_logout -> logout()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    }

    override fun attachBaseContext(newBase: Context?) {
        val sharedPreferences = newBase?.getSharedPreferences("settings", Context.MODE_PRIVATE)
        val savedLanguage = sharedPreferences?.getString("language", "en") ?: "en" // Default to "en"
        super.attachBaseContext(setLocale(newBase, savedLanguage) ?: newBase)
    }

    private fun changeLanguageHindi() {
        saveLanguagePreference("hi") // Save the preference
        setLocale(this, "hi")
        restartApp(this)

    }

    private fun changeLanguageEnglish() {
        saveLanguagePreference("en") // Save the preference
        setLocale(this, "en")
        restartApp(this)
    }
    private fun saveLanguagePreference(language: String) {
        val sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("language", language).apply()
    }

    private fun restartApp(dashboardActivity: DashboardActivity) {
        val intent = Intent(dashboardActivity, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        dashboardActivity.startActivity(intent)
        finish()
    }
    fun  openNavigationDrawer(){
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .addToBackStack(null)
            .commit()
    }


    private fun logout() {
        val intent = Intent(this, AuthActivity::class.java)
        appPreference.setLoggedIn(false)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)

        when (currentFragment) {
            is ShowKundliDataFragment -> {
                supportFragmentManager.popBackStack()
            }

            is MatchMakingRequestFragment -> {
                supportFragmentManager.popBackStack()
            }
            is DashboardFragment -> {
                showExitConfirmationDialog()
            }

            else -> {
                super.onBackPressed()
            }
        }
    }


    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Exit App")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes") { _, _ ->
                finish() // Close the activity
            }
            .setNegativeButton("No", null)
            .show()
    }

}
