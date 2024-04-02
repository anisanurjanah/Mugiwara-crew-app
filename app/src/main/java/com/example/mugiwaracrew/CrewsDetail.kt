package com.example.mugiwaracrew

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mugiwaracrew.databinding.ActivityCrewsDetailBinding

class CrewsDetail : AppCompatActivity() {
    private lateinit var binding: ActivityCrewsDetailBinding

    companion object {
        const val EXTRA_CREW = "extra_crew"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataCrew = intent.getParcelableExtra<Crew>(EXTRA_CREW)
        val tvLink = dataCrew?.link

        if (dataCrew != null) {
            Glide.with(this)
                .load(dataCrew.photo)
                .into(binding.ivDetailPhoto)
            binding.tvDetailName.text = dataCrew.name
            binding.tvDetailPosition.text = dataCrew.position
            binding.tvDetailFrom.text = dataCrew.from
            binding.tvDetailDescription.text = dataCrew.description
        }

        val btnShareDetail: Button = findViewById(R.id.bt_share)
        btnShareDetail.setOnClickListener {
            val detailName = Uri.parse(binding.tvDetailName.text.toString())
            val detailPosition = Uri.parse(binding.tvDetailPosition.text.toString())

            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "$detailName is a $detailPosition of Mugiwara Pirate\n" + "\n" +
                        "Click here to see detail $tvLink")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}
