package com.mss.weatherapp.presentation.component.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.mss.weatherapp.R

@Composable
fun TinyVerticalSpacer(){
    Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.element_spacing_tiny)))
}

@Composable
fun SmallVerticalSpacer(){
    Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.element_spacing_small)))

}

@Composable
fun MediumVerticalSpacer(){
    Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.element_spacing_medium)))

}

@Composable
fun LargeVerticalSpacer(){
    Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.element_spacing_large)))

}

@Composable
fun XLargeVerticalSpacer(){
    Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.element_spacing_xlarge)))

}

@Composable
fun TinyHorizontalSpacer(){
    Spacer(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.element_spacing_tiny)))
}

@Composable
fun SmallHorizontalSpacer(){
    Spacer(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.element_spacing_small)))

}

@Composable
fun MediumHorizontalSpacer(){
    Spacer(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.element_spacing_medium)))

}

@Composable
fun LargeHorizontalSpacer(){
    Spacer(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.element_spacing_large)))

}

@Composable
fun XLargeHorizontalSpacer(){
    Spacer(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.element_spacing_xlarge)))

}

@Composable
fun VerticalPartition() {
    Box(modifier = Modifier.width(1.dp).height(40.dp).background(color = Color.Gray))
}