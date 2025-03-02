package com.example.cricradio2.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cricradio.repository.Repository
import com.example.cricradio.scoreModel.ScoreResult
import com.example.cricradio.venueModel.VenueResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CricRadioViewmodel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _scorecard = MutableStateFlow<ScoreResult?>(null)
    val scorecard: StateFlow<ScoreResult?> = _scorecard

    private val _venuecard = MutableStateFlow<VenueResult?>(null)
    val venueResult: StateFlow<VenueResult?> = _venuecard

    init {
        fetchScoreDetails()
        fetchVenueDetails()
    }
    fun  fetchScoreDetails(){
        viewModelScope.launch {
            try {
                _scorecard.value = repository.getScoreCard("SA_vs_SL_2024-12-05_1732276435.300452")
            }catch (e:Exception){
                _scorecard.value = null
                Log.d("ScoreDetails", e.message.toString())
            }
        }
    }

    fun fetchVenueDetails(){
        viewModelScope.launch {
            try {
                _venuecard.value = repository.getVenueCard("SA_vs_SL_2024-12-05_1732276435.300452")
            }catch (e:Exception){
                _venuecard.value = null
                Log.d("VenueDetails", e.message.toString())
            }
        }
    }

    fun formatDate(timestamp: Int): String {
        val instant = Instant.ofEpochSecond(timestamp.toLong())
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, EEEE h:mm a", Locale.ENGLISH)
        return instant.atZone(ZoneId.of("GMT")).format(formatter)
    }
}