package com.sobkisu.store.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.sobkisu.store.R
import com.sobkisu.store.utils.CreditsAndLicenceDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initialSetUP()
    }

    private fun initialSetUP() {
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.licenceAndAgreements -> {
                CreditsAndLicenceDialog().initLicenceDialog(this)!!.show()
                true
            }
            R.id.credits -> {
                CreditsAndLicenceDialog().intiCreditsLialog(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.id_nav_manage_product -> {
                startActivity(Intent(this, ManageProductActivity::class.java))
            }

            R.id.id_nav_product_information -> {
                startActivity(Intent(this, ProductInfoActivity::class.java))
            }
            R.id.id_nav_sale_details -> {
                startActivity(Intent(this, BuySellDetailsActivity::class.java))
            }
            R.id.id_nav_cash_details -> {
                startActivity(Intent(this, CashDetailsActivity::class.java))
            }

        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
