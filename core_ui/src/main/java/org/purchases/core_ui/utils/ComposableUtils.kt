package org.purchases.core_ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun floatResource(id: Int): Float = LocalContext.current.resources.getString(id).toFloat()
