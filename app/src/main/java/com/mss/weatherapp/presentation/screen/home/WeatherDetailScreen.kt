package com.mss.weatherapp.presentation.screen.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mss.weatherapp.R
import com.mss.weatherapp.core.util.DateUtil
import com.mss.weatherapp.domain.models.WeatherData
import com.mss.weatherapp.presentation.component.components.CenterContentTopAppBar
import com.mss.weatherapp.presentation.component.components.HourlyWeatherStatView
import com.mss.weatherapp.presentation.component.components.LargeVerticalSpacer
import com.mss.weatherapp.presentation.component.components.MediumVerticalSpacer
import com.mss.weatherapp.presentation.component.components.SimpleHorizontalLine
import com.mss.weatherapp.presentation.component.components.SimpleHumidity
import com.mss.weatherapp.presentation.component.components.SimpleHumidityStat
import com.mss.weatherapp.presentation.component.components.SimpleRain
import com.mss.weatherapp.presentation.component.components.SimpleRainStat
import com.mss.weatherapp.presentation.component.components.SimpleTemperature
import com.mss.weatherapp.presentation.component.components.SimpleWeatherCondition
import com.mss.weatherapp.presentation.component.components.SimpleWindStat
import com.mss.weatherapp.presentation.component.components.SmallVerticalSpacer
import com.mss.weatherapp.presentation.component.components.TinyHorizontalSpacer
import com.mss.weatherapp.presentation.component.components.TinyVerticalSpacer
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDetailScreen(
    weatherDataState: State<List<WeatherData.Daily>?>,
    paddingValues: PaddingValues,
    onAppBarStartIconTapped: () -> Unit,
    onAppBarEndIconTapped: () -> Unit,
    appbarStartIcon: ImageVector,
    appbarEndIcon: ImageVector? = null
) {
    Scaffold(
        modifier = Modifier.padding(paddingValues)
    ) {
        Screen(
            weatherDataState = weatherDataState,
            paddingValues = it,
            onAppBarEndIconTapped = onAppBarEndIconTapped,
            onAppbarStartIconTapped = onAppBarStartIconTapped,
            appbarStartIcon = appbarStartIcon,
            appbarEndIcon = appbarEndIcon

        )
    }
}

@Composable
fun Screen(
    weatherDataState: State<List<WeatherData.Daily>?>,
    paddingValues: PaddingValues,
    onAppBarEndIconTapped: () -> Unit,
    onAppbarStartIconTapped: () -> Unit,
    appbarStartIcon: ImageVector,
    appbarEndIcon: ImageVector?
) {
    Column(modifier = Modifier.padding(paddingValues)) {
        CenterContentTopAppBar(
            title = { Text(weatherDataState.value?.firstOrNull()?.location ?: "", color = Color.Gray) },
            startIcon = appbarStartIcon,
            onStartIconClicked = onAppbarStartIconTapped,
            endIcon = appbarEndIcon,
            onEndIconClicked = onAppBarEndIconTapped
        )
        Content(
            weatherDataState = weatherDataState,
        )

    }
}

@Composable
fun Content(
    weatherDataState: State<List<WeatherData.Daily>?>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SmallVerticalSpacer()
        //Date
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_calendar,),
                contentDescription = stringResource(
                    id = R.string.content_description_date
                ),
                tint = Color.Gray
            )
            TinyHorizontalSpacer()
            Text(
                DateUtil.getFormattedDateMonth(
                    weatherDataState.value?.firstOrNull()?.localTime ?: ""
                ),
                color = Color.Gray
            )
        }
        SmallVerticalSpacer()

        //Temperature
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(start = 38.dp)
        ) {
            Text(
                ((weatherDataState.value?.firstOrNull()?.tempAverage ?: "0").toString()),
                fontSize = 80.sp
            )
            Text(stringResource(id = R.string.degree_centigrade_placeholder), fontSize = 30.sp)
        }

        SmallVerticalSpacer()
        SimpleWeatherCondition(
            iconResource = R.drawable.ic_cloudy,
            description = weatherDataState.value?.firstOrNull()?.conditionDescription ?: ""
        )

        MediumVerticalSpacer()

        Row(
            horizontalArrangement = Arrangement.Center, modifier = Modifier
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                SimpleWindStat(wind = weatherDataState.value?.firstOrNull()?.windSpeed ?: 0f)

            }
            Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                SimpleHumidityStat(humidity = weatherDataState.value?.firstOrNull()?.humidity ?: 0f)

            }
            Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                SimpleRainStat(rain = weatherDataState.value?.firstOrNull()?.precipitation ?: 0f)

            }
        }

        MediumVerticalSpacer()
        HourlyWeatherStat(
            items = weatherDataState.value?.firstOrNull()?.hourlyData ?: mutableListOf()
        )
        LargeVerticalSpacer()
        DailyWeatherForecast(weatherDataState.value ?: emptyList())
        MediumVerticalSpacer()
    }

}


@Composable
fun HourlyWeatherStat(items: List<WeatherData.Hourly>) {
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        items.forEach {
            TinyHorizontalSpacer()
            HourlyWeatherStatView(
                title = DateUtil.getFormattedTime(it.time),
                icon = { SimpleRain() },
                stat = "${it.temp}"
            )
            TinyHorizontalSpacer()
        }
    }
}

@Composable
fun DailyWeatherForecast(items: List<WeatherData.Daily?>) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .width(100.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_calendar),
                    contentDescription = stringResource(
                        id = R.string.content_description_none
                    ),
                    tint = Color.Gray
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SimpleTemperature()
                SimpleHumidity()
                SimpleRain()
            }

        }
        val ctx = LocalContext.current
        SimpleHorizontalLine()
        Column() {
            items.forEach {
                it?.let {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .width(100.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                DateUtil.getFormattedDateMonthShort(it.date),
                                modifier = Modifier.clickable {
                                    showToast(it, ctx)
                                }
                            )

                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                String.format(
                                    stringResource(id = R.string.quantity_centigrade),
                                    it.tempAverage
                                )
                            )
                            Text(
                                String.format(
                                    stringResource(id = R.string.quantity_percent),
                                    it.humidity
                                )
                            )
                            Text(
                                String.format(
                                    stringResource(id = R.string.quantity_percent),
                                    it.precipitation
                                )
                            )
                        }

                    }
                }
                TinyVerticalSpacer()

            }

        }
    }
}

fun showToast(it: WeatherData.Daily, context: Context) {
    //Day's High and Low temperature is null, displaying those value on click of Dates
    val msg = "Date: ${it.date} \n High: ${it.tempMaximum} \n Low: ${it.tempMinimum}"
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}
