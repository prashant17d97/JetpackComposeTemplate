package com.prashant.composetemplate.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontStyle.Companion.Normal
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.prashant.composetemplate.R

class Type(fontFamilyName: String) {

    private val googleFontFamily = FontFamily(
        Font(
            googleFont = GoogleFont(name = fontFamilyName),
            fontProvider = GoogleFont.Provider(
                providerAuthority = "com.google.android.gms.fonts",
                providerPackage = "com.google.android.gms",
                certificates = R.array.com_google_android_gms_fonts_certs
            )
        )
    )


    // Set of Material typography styles to start with
    private val typographyNormal = Typography(
        defaultFontFamily = googleFontFamily,
        h1 = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 96.sp,
            fontStyle = Normal
        ),
        h2 = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 60.sp,
            fontStyle = Normal
        ),
        h3 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 48.sp,
            fontStyle = Normal
        ),
        h4 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 34.sp,
            fontStyle = Normal
        ),
        h5 = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            fontStyle = Normal
        ),
        h6 = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            fontStyle = Normal
        ),
        subtitle1 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            fontStyle = Normal
        ),
        subtitle2 = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            fontStyle = Normal
        ),
        body1 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            fontStyle = Normal
        ),
        body2 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            fontStyle = Normal
        ),
        button = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            fontStyle = Normal
        ),
        caption = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            fontStyle = Normal
        ),
        overline = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            fontStyle = Normal
        )
    )
    private val typographyItalic = Typography(
        defaultFontFamily = googleFontFamily,
        h1 = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 96.sp,
            fontStyle = Italic
        ),
        h2 = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 60.sp,
            fontStyle = Italic
        ),
        h3 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 48.sp,
            fontStyle = Italic
        ),
        h4 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 34.sp,
            fontStyle = Italic
        ),
        h5 = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            fontStyle = Italic
        ),
        h6 = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            fontStyle = Italic
        ),
        subtitle1 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            fontStyle = Italic
        ),
        subtitle2 = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            fontStyle = Italic
        ),
        body1 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            fontStyle = Italic
        ),
        body2 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            fontStyle = Italic
        ),
        button = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            fontStyle = Italic
        ),
        caption = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            fontStyle = Italic
        ),
        overline = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            fontStyle = Italic
        )
    )

    fun Typography(fontStyle: FontStyle = Normal) =
        typographyNormal.takeIf { fontStyle == Normal } ?: typographyItalic
}