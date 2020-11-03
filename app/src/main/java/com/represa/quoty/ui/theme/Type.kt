package com.represa.quoty.ui.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.represa.quoty.R

// Set of Material typography styles to start with
val typography = Typography(
        body1 = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
        )
        /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

private val Montserrat = fontFamily(
        font(R.font.montserrat_regular),
        font(R.font.montserrat_medium, FontWeight.W500),
        font(R.font.montserrat_semi_bold, FontWeight.W600)
)

val QuotyTypography = Typography(
        h4 = TextStyle(
                fontFamily = Montserrat,
                fontWeight = FontWeight.W600,
                fontSize = 30.sp
        ),
        h5 = TextStyle(
                fontFamily = Montserrat,
                fontWeight = FontWeight.W600,
                fontSize = 24.sp
        ),
        h6 = TextStyle(
                fontFamily = Montserrat,
                fontWeight = FontWeight.W600,
                fontSize = 20.sp
        ),
        subtitle1 = TextStyle(
                fontFamily = Montserrat,
                fontWeight = FontWeight.W600,
                fontSize = 16.sp
        ),
        subtitle2 = TextStyle(
                fontFamily = Montserrat,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp
        ),
        body1 = TextStyle(
                fontFamily = Montserrat,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
        ),
        body2 = TextStyle(
                fontFamily = Montserrat,
                fontSize = 14.sp
        ),
        button = TextStyle(
                fontFamily = Montserrat,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp
        ),
        caption = TextStyle(
                fontFamily = Montserrat,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
        ),
        overline = TextStyle(
                fontFamily = Montserrat,
                fontWeight = FontWeight.W500,
                fontSize = 12.sp
        )
)
/*
val QuotyShapes = Shapes(
        small = CutCornerShape(topLeft = 0.dp),
        medium = CutCornerShape(topLeft = 0.dp),
        large = RoundedCornerShape(0.dp)
)*/