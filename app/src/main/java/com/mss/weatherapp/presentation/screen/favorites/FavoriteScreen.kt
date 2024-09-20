package com.mss.weatherapp.presentation.screen.favorites

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mss.weatherapp.R
import com.mss.weatherapp.domain.models.FavoriteLocationModel
import com.mss.weatherapp.presentation.component.components.CenterContentTopAppBar
import com.mss.weatherapp.presentation.component.components.SimpleHumidity
import com.mss.weatherapp.presentation.component.components.SimpleRain
import com.mss.weatherapp.presentation.component.components.SimpleTemperature
import com.mss.weatherapp.presentation.component.components.SimpleWeatherStat
import com.mss.weatherapp.presentation.component.components.SmallVerticalSpacer
import com.mss.weatherapp.presentation.component.components.TinyHorizontalSpacer
import com.mss.weatherapp.presentation.component.components.XLargeVerticalSpacer
import com.mss.weatherapp.presentation.screen.home.model.WeatherData
import java.util.Locale

@Composable
fun FavoriteScreen(
    paddingValues: PaddingValues,
    onBackIconClicked: () -> Unit,
    dateState: State<String?>,
    favoriteLocationDataList: State<List<FavoriteLocationModel>?>,
    onFavoriteItemTapped: (favoriteLocationModel: FavoriteLocationModel) -> Unit
) {
    Column(modifier = Modifier.padding(paddingValues)) {
        CenterContentTopAppBar(
            title = { Text(stringResource(id = R.string.title_favorites), color = Color.Gray) },
            startIcon = ImageVector.vectorResource(id = R.drawable.ic_back),
            onStartIconClicked = onBackIconClicked
        )
        SmallVerticalSpacer()
        Content(
            dateState = dateState,
            favoriteLocationDataList = favoriteLocationDataList,
            onFavoriteItemTapped = onFavoriteItemTapped
        )

    }
}

@Composable
@Preview
private fun FavoriteScreenPreview() {
    FavoriteScreen(
        onBackIconClicked = {},
        dateState = remember {
            mutableStateOf("Jan 14th")
        },
        favoriteLocationDataList = remember {
            mutableStateOf(
                listOf(
                    FavoriteLocationModel(
                        0,
                        "Tokyo",
                        region = "Asia",
                        country = "Japan",
                        isDefault = true,
                        weatherData = WeatherData(20f, 21f, 24f, 33f)
                    ),
                    FavoriteLocationModel(
                        0,
                        "Pokhara",
                        region = "Asia",
                        country = "Nepal",
                        isDefault = false,
                        weatherData = WeatherData(30f, 51f, 44f, 32f)
                    ),
                    FavoriteLocationModel(
                        0,
                        "Jakarta",
                        region = "Asia",
                        country = "Indonesia",
                        isDefault = false,
                        weatherData = WeatherData(20f, 21f, 14f, 3f)
                    ),
                    FavoriteLocationModel(
                        0,
                        "Bangkok",
                        region = "Asia",
                        country = "Thailand",
                        isDefault = false,
                        weatherData = WeatherData(10f, 61f, 34f, 43f)
                    )
                )
            )

        },
        onFavoriteItemTapped = {},
        paddingValues = PaddingValues(4.dp)
    )
}


@Composable
private fun Content(
    dateState: State<String?>,
    favoriteLocationDataList: State<List<FavoriteLocationModel>?>,
    onFavoriteItemTapped: (favoriteLocationModel: FavoriteLocationModel) -> Unit
) {
    Column() {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_calendar),
                contentDescription = stringResource(
                    id = R.string.content_description_date
                ),
                tint = Color.Gray
            )
            TinyHorizontalSpacer()
            Text(dateState.value ?: "", color = Color.Gray)
        }

        XLargeVerticalSpacer()

        favoriteLocationDataList.value?.forEach {
            Column() {
                FavoriteLocationItem(data = it, onFavoriteItemTapped = onFavoriteItemTapped)
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FavoriteLocationItem(
    data: FavoriteLocationModel,
    onFavoriteItemTapped: (favoriteLocationModel: FavoriteLocationModel) -> Unit
) {
    Surface(onClick = { onFavoriteItemTapped(data) }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
                .border(width = 2.dp, Color.Transparent),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.width(150.dp)) {
                Text(data.getDisplayName())
            }

            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SimpleWeatherStat(
                    stat = String.format(
                        Locale.ENGLISH,
                        stringResource(id = R.string.quantity_centigrade),
                        data.weatherData?.temperature ?: 0f
                    ),
                    icon = { SimpleTemperature() },
                    title = null
                )
                SimpleWeatherStat(
                    stat = String.format(
                        Locale.ENGLISH,
                        stringResource(id = R.string.quantity_percent),
                        data.weatherData?.humidity ?: 0f
                    ),
                    icon = { SimpleHumidity() },
                    title = null
                )
                SimpleWeatherStat(
                    stat = String.format(
                        Locale.ENGLISH,
                        stringResource(id = R.string.quantity_percent),
                        data.weatherData?.precipitation ?: 0f
                    ),
                    icon = { SimpleRain() },
                    title = null
                )
            }

        }
    }

}