package com.example.cricradio2.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.cricradio.scoreModel.ScoreCardResult
import com.example.cricradio.venueModel.VenueCardResult
import com.example.cricradio2.R
import com.example.cricradio2.ui.theme.CardGray
import com.example.cricradio2.ui.theme.LightBlue
import com.example.cricradio2.ui.theme.TopCardBlue
import com.example.cricradio2.ui.theme.Yellowish
import com.example.cricradio2.viewmodel.CricRadioViewmodel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


@Composable
fun HomeScreen(cricRadioViewmodel: CricRadioViewmodel = hiltViewModel()) {

    val scoreDetails by cricRadioViewmodel.scorecard.collectAsState()
    val venueDetails by cricRadioViewmodel.venueResult.collectAsState()
    
    val StartingDate = venueDetails?.responseData?.result?.start_date?.let {
        cricRadioViewmodel.formatDate(
        timestamp = it.timestamp
    )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(top = 30.dp)
            .padding(all = 12.dp)
            .verticalScroll(rememberScrollState())
    ) {
            scoreDetails?.let {
                TopScoreCard(
                    scoreDetails = it.responseData.result
                )
            }
        Spacer(modifier = Modifier.height(16.dp))
        venueDetails?.let {
            VenueDetails(
                venueCardResult = it.responseData.result,
                startingDate = StartingDate!!
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        venueDetails?.let {
            TossCard(
                venueCardResult = it.responseData.result,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        venueDetails?.let {
            UmpireCard(
                venueCardResult = it.responseData.result,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        venueDetails?.let {
            WeatherCard(
                venueCardResult = it.responseData.result,
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        venueDetails?.let {
            VenueStats(
                venueCardResult = it.responseData.result,
            )
        }
    }
}

@Composable
fun TopScoreCard(scoreDetails: ScoreCardResult) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
        colors = CardDefaults.cardColors(TopCardBlue)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        AsyncImage(
                            model = scoreDetails.teams.a.logo,
                            contentDescription = "flag",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                        )
                        Text(
                            text = scoreDetails.teams.a.shortName,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                        if(scoreDetails.settingObj.currentTeam.equals("a")){
                            Icon(
                                painter = painterResource(R.drawable.bat),
                                contentDescription = "icon",
                                tint = Yellowish
                            )
                        }
                    }
                    if(scoreDetails.settingObj.currentTeam == "a"){
                        Text(
                            text = "${scoreDetails.teams.a.a_1_score.runs}/${scoreDetails.teams.a.a_1_score.wickets}",
                            fontSize = 22.sp,
                            color = Yellowish,
                            fontWeight = FontWeight.Bold
                        )
                    }else{
                        Text(
                            text = "${scoreDetails.teams.a.a_1_score.runs}/${scoreDetails.teams.a.a_1_score.wickets}",
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    }

                    Text(
                        text = "${scoreDetails.teams.a.a_1_score.overs}",
                        fontSize = 16.sp,
                        color = Color.LightGray
                    )
                }

                Text(
                    text = scoreDetails.lastCommentary.primaryText,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        AsyncImage(
                            model = scoreDetails.teams.b.logo,
                            contentDescription = "flag B",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                        )
                        Text(
                            text = scoreDetails.teams.b.shortName,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                        if(scoreDetails.settingObj.currentTeam.equals("b")){
                            Icon(
                                painter = painterResource(R.drawable.bat),
                                contentDescription = "icon",
                                tint = Yellowish
                            )
                        }

                    }
                    if(scoreDetails.settingObj.currentTeam.equals("b") ){
                        Text(
                            text = "${scoreDetails.teams.b.b_1_score.runs}/${scoreDetails.teams.b.b_1_score.wickets}",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Yellowish
                        )
                        Text(
                            text = scoreDetails.teams.b.b_1_score.overs,
                            fontSize = 16.sp,
                            color = Color.LightGray
                        )
                    }
                }
            }

            Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color.LightGray)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)){
                    Text(
                        text = buildAnnotatedString {
                            append("CRR: ")
                            withStyle(style = SpanStyle(color = LightBlue, fontWeight = FontWeight.Bold)) {
                                append(scoreDetails.now.run_rate)
                            }
                        },
                        fontSize = 14.sp,
                        color = Color.White
                    )
                    if(scoreDetails.now.req_run_rate!=""){
                        Text(
                            text = buildAnnotatedString {
                                append("RRR: ")
                                withStyle(style = SpanStyle(color = LightBlue, fontWeight = FontWeight.Bold)) {
                                    append(scoreDetails.now.req_run_rate)
                                }
                            },
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }
                }

                Text(
                    text = scoreDetails.announcement1,
                    fontSize = 14.sp,
                    color = LightBlue
                )
            }
        }
    }
}

@Composable
fun VenueDetails(venueCardResult: VenueCardResult, startingDate:String) {
    
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = venueCardResult.venueDetails.photo,
                contentDescription = "venue_image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(180.dp)
            )
        }
        Spacer(Modifier.height(8.dp))

        Text(
            text = "${venueCardResult.venueDetails.venue_info.name}, ${venueCardResult.venueDetails.venue_info.location}",
            color = LightBlue,
            fontSize = 15.sp,
            textDecoration = TextDecoration.Underline
        )
        Spacer(Modifier.height(8.dp))
        Text(
            "${venueCardResult.related_name} ${venueCardResult.season.name}",
            color = Color.LightGray,
            fontSize = 14.sp
        )
        Spacer(Modifier.height(4.dp))
        Text(startingDate,color= Color.White, fontSize = 14.sp)
    }
}

@Composable
fun TossCard(venueCardResult: VenueCardResult) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardGray),
        border = BorderStroke(1.dp, color = Color.Gray)
    ) {
        if(venueCardResult.toss.won.equals("a")) {
            Text(
                "${venueCardResult.teams.a.shortName} won the toss and chose to ${venueCardResult.toss.decision} first",
                color = Yellowish,
                fontSize = 14.sp,
                modifier = Modifier.padding(10.dp)
            )
        }else{
            Text(
                "${venueCardResult.teams.b.shortName} won the toss and chose to ${venueCardResult.toss.decision} first",
                color = Yellowish,
                modifier = Modifier.padding(10.dp)
            )
        }

    }
}

@Composable
fun UmpireCard(venueCardResult: VenueCardResult) {
    Text(text = "Umpires", color = Color.White)
    Spacer(Modifier.height(4.dp))
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardGray)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Umpire", color = Color.Gray, fontSize = 14.sp)
                    Text(venueCardResult.firstUmpire, color = Color.White, fontSize = 14.sp)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("Umpire", color = Color.Gray, fontSize = 14.sp)
                    Text(venueCardResult.secoundUmpire, color = Color.White, fontSize = 14.sp)
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = Color.Gray)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Third/TV Umpire", color = Color.Gray, fontSize = 14.sp)
                    Text(venueCardResult.thirdUmpire, color = Color.White, fontSize = 14.sp)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("Referee", color = Color.Gray, fontSize = 14.sp)
                    Text(venueCardResult.matchReferee, color = Color.White, fontSize = 14.sp)
                }
            }


        }
    }

}

@Composable
fun WeatherCard(venueCardResult: VenueCardResult) {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val dateTime = LocalDateTime.parse(venueCardResult.weather.last_updated, inputFormatter)
    val outputFormatter = DateTimeFormatter.ofPattern("dd MMM, hh:mm a",Locale.ENGLISH)
    Text(text = "Weather", color = Color.White)
    Spacer(Modifier.height(4.dp))
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardGray)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = venueCardResult.weather.condition.icon,
                contentDescription = "weather",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(venueCardResult.weather.location, color = Color.LightGray, fontSize = 13.sp)
                Text("${venueCardResult.weather.temp_c} °C", color = Yellowish, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Text(venueCardResult.weather.condition.text, color = LightBlue, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
            VerticalDivider(
                modifier = Modifier
                    .height(90.dp)
                    .padding(horizontal = 16.dp),
                color = Color.Gray
            )
            Column(horizontalAlignment = Alignment.Start) {
                Text("Last Updated", color = Color.Gray, fontSize = 14.sp)
                Text(dateTime.format(outputFormatter), color = Color.White, fontSize = 13.sp)
            }
        }
    }
}

@Composable
fun VenueStats(venueCardResult: VenueCardResult) {
    Text("Venue Stats", color = Color.White)
    Spacer(modifier = Modifier.height(8.dp))
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Black),
        border = BorderStroke(1.dp,Color.DarkGray)
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            Stats("Matches Played", venueCardResult.venueStats.matchesPlayed.toString())
            Stats("Lowest Defended", venueCardResult.venueStats.lowestDefended.toString())
            Stats("Highest Chased", venueCardResult.venueStats.highestChased.toString())
            Stats("Win Bat First", venueCardResult.venueStats.batFirstWins.toString())
            Stats("Win Bowl First", venueCardResult.venueStats.ballFirstWins.toString())
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CardGray)
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(5f)) {
                    Text("", color = Color.White, fontSize = 14.sp)

                }
                Column(modifier = Modifier.weight(2f)) {
                    Text("1st Inn", color = Color.White, fontSize = 11.sp)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("2nd Inn", color = Color.White, fontSize = 11.sp)

                }
            }

            InningStat("Avg Score", venueCardResult.venueStats.battingFirst.averageScore.toString(), venueCardResult.venueStats.battingSecond.averageScore.toString())
            InningStat("Highest Score", venueCardResult.venueStats.battingFirst.highestScore.toString(), venueCardResult.venueStats.battingSecond.averageScore.toString())
            InningStat("Lowest Score", venueCardResult.venueStats.battingFirst.lowestScore.toString(), venueCardResult.venueStats.battingSecond.averageScore.toString())
            InningStat("Pace Wickets", venueCardResult.venueStats.battingFirst.paceWickets.toString(), venueCardResult.venueStats.battingSecond.averageScore.toString())
            InningStat("Spin Wickets", venueCardResult.venueStats.battingFirst.spinWickets.toString(), venueCardResult.venueStats.battingSecond.averageScore.toString())
        }
    }
}

@Composable
fun Stats(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(7f)){
            Text(label, color = Color.White, fontSize = 14.sp)
        }
        Column(modifier = Modifier.weight(1f)){
            Text(value, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
    Divider(color = Color.DarkGray)
}

@Composable
fun InningStat(label: String, Inn1: String, Inn2: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(5f)) {
            Text(label, color = Color.White, fontSize = 14.sp)

        }
        Column(modifier = Modifier.weight(2f)) {
            Text(Inn1, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(Inn2, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)

        }
    }
    Divider(color = Color.DarkGray)
}

