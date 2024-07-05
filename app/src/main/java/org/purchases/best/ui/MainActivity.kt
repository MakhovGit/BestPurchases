package org.purchases.best.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.purchases.core_ui.ui.theme.BestTheme
import org.purchases.navigation.ui.navigation.AppNavigation

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
