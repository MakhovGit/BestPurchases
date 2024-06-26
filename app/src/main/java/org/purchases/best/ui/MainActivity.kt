package org.purchases.best.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.purchases.best.ui.navigation.AppNavigation
import org.purchases.best.ui.theme.BestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BestTheme {
                AppNavigation()
            }
        }
    }
}
