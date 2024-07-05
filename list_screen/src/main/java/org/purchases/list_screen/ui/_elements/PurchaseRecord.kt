package org.purchases.list_screen.ui._elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextDecoration
import org.purchases.core.model.info.PurchaseInfo
import org.purchases.core_ui.ui.theme.ScreenBackgroundColor
import org.purchases.core_ui.utils.floatResource
import org.purchases.list_screen.ui.list_screen.ListScreenContract
import org.purchases.list_screen.ui.list_screen.ListScreenViewModel
import org.purchases.core_ui.R as core_ui_R

@Composable
fun PurchaseRecord(purchaseInfo: PurchaseInfo, viewModel: ListScreenViewModel) {
    var isCheckedState by remember { mutableStateOf(purchaseInfo.checked) }
    Surface(
        color = if (isCheckedState) ScreenBackgroundColor else MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                isCheckedState = isCheckedState.not()
                viewModel.handleEvent(
                    ListScreenContract.Event.TogglePurchaseEvent(
                        purchaseId = purchaseInfo.id
                    )
                )
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isCheckedState,
                onCheckedChange = {
                    isCheckedState = isCheckedState.not()
                    viewModel.handleEvent(
                        ListScreenContract.Event.TogglePurchaseEvent(
                            purchaseId = purchaseInfo.id
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = core_ui_R.dimen.spacer_20)))
            Text(
                text = purchaseInfo.description,
                textDecoration = if (isCheckedState) TextDecoration.LineThrough else null,
                modifier = Modifier.weight(floatResource(id = core_ui_R.dimen.max_weight))
            )
        }
    }
}