package org.purchases.core_ui.ui._elements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import org.purchases.core_ui.R
import org.purchases.core_ui.utils.floatResource

@Composable
fun PurchaseRecordOnCreate(description: String) {
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = false,
                onCheckedChange = null
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.spacer_20)))
            Text(
                text = description,
                modifier = Modifier.weight(floatResource(id = R.dimen.max_weight))
            )
        }
    }
}