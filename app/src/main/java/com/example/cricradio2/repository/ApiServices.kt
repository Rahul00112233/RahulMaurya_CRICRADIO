package com.example.cricradio.repository

import com.example.cricradio.scoreModel.ScoreCardResult
import com.example.cricradio.scoreModel.ScoreResult
import com.example.cricradio.venueModel.VenueResult

interface ApiService{
    suspend fun getScoreCard(key:String):ScoreResult
    suspend fun getVenueCard(key: String):VenueResult
}

