package com.example.cricradio.repository

import android.util.Log
import com.example.cricradio.scoreModel.ScoreCardResult
import com.example.cricradio.scoreModel.ScoreResult
import com.example.cricradio.venueModel.VenueResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(val client: HttpClient):ApiService {

    override suspend fun getScoreCard(key: String): ScoreResult {
        val response: HttpResponse = client.get("http://3.6.243.12:5001/api/v2/match/mini-match-card") {
            parameter("key", key)
        }

        return response.body()
    }

    override suspend fun getVenueCard(key: String): VenueResult {
        val response: HttpResponse =
            client.get("http://3.6.243.12:5001/api/v2/match/venue-info") {
                parameter("key", key)
            }
        return response.body()
    }

}