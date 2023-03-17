package com.prashant.composetemplate

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle.Companion.Normal
import androidx.navigation.compose.rememberNavController
import com.prashant.composetemplate.interfaces.UiConfiguration
import com.prashant.composetemplate.navigation.NavGraph
import com.prashant.composetemplate.theme.ComposeTemplateTheme
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference

@AndroidEntryPoint
class MainActivity : ComponentActivity(), UiConfiguration {

    private val mutableState = mutableStateOf(false)

    companion object {
        lateinit var weakReference: WeakReference<Context>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weakReference = WeakReference(this)
        setContent {
            val navHostController = rememberNavController()

            ComposeTemplateTheme(fontStyle = Normal, darkTheme = mutableState.value) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        NavGraph(navHostController = navHostController)
//                        CircularProgressAnimated()
                    }
                }
            }

        }
    }

    @Composable
    private fun CircularProgressAnimated() {
        val progressValue = 0.75f
        val infiniteTransition = rememberInfiniteTransition()

        val progressAnimationValue by infiniteTransition.animateFloat(
            initialValue = 0.0f,
            targetValue = progressValue, animationSpec = infiniteRepeatable(animation = tween(900))
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
        ) {
            CircularProgressIndicator(progress = progressAnimationValue)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        weakReference = WeakReference(null)
    }

    override fun onResume() {
        super.onResume()
        weakReference = WeakReference(this)

    }

    override fun darkTheme(boolean: Boolean) {
        mutableState.value = boolean
        Log.e("Mainactivity", "darkTheme:$boolean")
    }

}
