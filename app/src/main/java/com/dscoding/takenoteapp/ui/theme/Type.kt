package com.dscoding.takenoteapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dscoding.takenoteapp.R

val montserratFontFamily = FontFamily(
    Font(R.font.montserrat),
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_light, FontWeight.Light),
    Font(R.font.montserrat_medium, FontWeight.Medium)
)

fun Typography(fontFamily: FontFamily) = Typography().copy(
    headlineMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = fontFamily,
        fontSize = 24.sp,
        letterSpacing = 0.15.sp
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.Medium,
        fontFamily = fontFamily,
        fontSize = 20.sp,
        letterSpacing = 0.15.sp
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = fontFamily,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = fontFamily,
        fontSize = 14.sp,
        letterSpacing = 0.15.sp
    )
)
