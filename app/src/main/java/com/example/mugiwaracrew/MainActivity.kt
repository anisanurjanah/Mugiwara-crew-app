package com.example.mugiwaracrew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mugiwaracrew.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Crew>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvCrews.setHasFixedSize(true)

        list.addAll(getListCrews())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_about, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.about -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }

    private fun getListCrews(): ArrayList<Crew> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataPosition = resources.getStringArray(R.array.data_position)
        val dataFrom = resources.getStringArray(R.array.data_from)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataLink = resources.getStringArray(R.array.data_link)
        val listCrew = ArrayList<Crew>()
        for (i in dataName.indices) {
            val crew = Crew(dataName[i], dataPosition[i], dataFrom[i], dataDescription[i], dataPhoto[i], dataLink[i])
            listCrew.add(crew)
        }
        return listCrew
    }

    private fun showRecyclerList() {
        binding.rvCrews.layoutManager = LinearLayoutManager(this)
        val listCrewAdapter = ListCrewAdapter(list)
        binding.rvCrews.adapter = listCrewAdapter

        listCrewAdapter.setOnItemClickCallback(object : ListCrewAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Crew) {
                val detailCrews = Intent(this@MainActivity, CrewsDetail::class.java)
                detailCrews.putExtra(CrewsDetail.EXTRA_CREW, data)
                startActivity(detailCrews)
            }
        })
    }
}