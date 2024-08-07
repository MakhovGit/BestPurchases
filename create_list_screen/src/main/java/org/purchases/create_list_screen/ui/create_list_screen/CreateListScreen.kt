package org.purchases.create_list_screen.ui.create_list_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import org.purchases.core.model.info.ListWithPurchasesInfo
import org.purchases.core.model.info.PurchaseInfo
import org.purchases.core.utils.EMPTY
import org.purchases.core_ui.ui.theme.LocalTitleColor
import org.purchases.core_ui.ui.theme.ScreenBackgroundColor
import org.purchases.core_ui.ui.theme.TextFieldBackgroundColor
import org.purchases.create_list_screen.R
import org.purchases.core_ui.R as core_ui_R


@SuppressLint("RestrictedApi")
@Composable
fun CreateListScreen(
    viewModel: CreateListScreenViewModel = koinViewModel(),
    navController: NavController,
) {
    var listTitle by remember { mutableStateOf(String.EMPTY) }
    var listItem by remember { mutableStateOf(String.EMPTY) }
    val purchases = SnapshotStateList<PurchaseInfo>()
    Scaffold(
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Surface(
                    color = ScreenBackgroundColor,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(id = core_ui_R.dimen.border_padding))
                    ) {
                        val textFieldShape =
                            RoundedCornerShape(dimensionResource(id = core_ui_R.dimen.rounded_corner_shape_text_field))
                        val boxShape =
                            RoundedCornerShape(dimensionResource(id = core_ui_R.dimen.rounded_corner_shape_card))
                        val listNameDefault =
                            stringResource(id = R.string.list_name_default)
                        Text(
                            text = stringResource(id = R.string.title),
                            color = LocalTitleColor,
                            textAlign = TextAlign.Start,
                            fontSize = dimensionResource(id = core_ui_R.dimen.main_title_font_size).value.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = dimensionResource(id = core_ui_R.dimen.main_title_bottom_padding))
                        )
                        Spacer(modifier = Modifier.height(dimensionResource(id = core_ui_R.dimen.spacer_20)))
                        TextField(
                            value = listTitle,
                            onValueChange = { newValue -> listTitle = newValue },
                            singleLine = true,
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(core_ui_R.drawable.baseline_list_alt_24),
                                    contentDescription = stringResource(id = R.string.icon_content_description)
                                )
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = TextFieldBackgroundColor,
                                unfocusedContainerColor = TextFieldBackgroundColor,
                                focusedIndicatorColor = TextFieldBackgroundColor,
                                unfocusedIndicatorColor = TextFieldBackgroundColor
                            ),
                            shape = textFieldShape,
                            placeholder = {
                                Text(text = stringResource(id = R.string.list_name_placeholder))
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(
                                    elevation = dimensionResource(id = core_ui_R.dimen.text_field_shadow_elevation),
                                    shape = textFieldShape
                                )
                        )
                        Spacer(modifier = Modifier.height(dimensionResource(id = core_ui_R.dimen.spacer_40)))
                        Surface(
                            shape = boxShape,
                            color = TextFieldBackgroundColor,
                            modifier = Modifier
                                .weight(org.purchases.core_ui.utils.floatResource(id = core_ui_R.dimen.max_weight))
                                .shadow(
                                    elevation = dimensionResource(id = core_ui_R.dimen.card_shadow_elevation),
                                    shape = boxShape
                                )
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                TextField(
                                    value = listItem,
                                    onValueChange = { newValue -> listItem = newValue },
                                    singleLine = false,
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(R.drawable.baseline_mail_outline_24),
                                            contentDescription = stringResource(id = R.string.icon_content_description)
                                        )
                                    },
                                    trailingIcon = {
                                        Icon(
                                            painter = painterResource(R.drawable.baseline_add_circle_24),
                                            contentDescription = stringResource(id = R.string.icon_content_description),
                                            modifier = Modifier
                                                .size(dimensionResource(id = core_ui_R.dimen.text_field_icon_size))
                                                .clickable {
                                                    if (listItem
                                                            .trim()
                                                            .isNotEmpty()
                                                    ) {
                                                        purchases.add(
                                                            PurchaseInfo(
                                                                description = listItem,
                                                                checked = false
                                                            )
                                                        )
                                                    }
                                                    listItem = String.EMPTY
                                                }
                                        )
                                    },
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = TextFieldBackgroundColor,
                                        unfocusedContainerColor = TextFieldBackgroundColor,
                                        focusedIndicatorColor = TextFieldBackgroundColor,
                                        unfocusedIndicatorColor = TextFieldBackgroundColor
                                    ),
                                    placeholder = {
                                        Text(text = stringResource(id = R.string.purchase_name_placeholder))
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                )
                                HorizontalDivider(
                                    modifier = Modifier.padding(
                                        horizontal = dimensionResource(
                                            id = core_ui_R.dimen.divider_horizontal_padding
                                        )
                                    )
                                )
                                LazyColumn(
                                    contentPadding = PaddingValues(dimensionResource(id = core_ui_R.dimen.lazy_column_content_padding)),
                                    verticalArrangement = Arrangement.spacedBy(
                                        dimensionResource(id = R.dimen.lazy_column_space_between_elements)
                                    ),
                                    modifier = Modifier.weight(
                                        org.purchases.core_ui.utils.floatResource(
                                            id = core_ui_R.dimen.max_weight
                                        )
                                    )
                                ) {
                                    purchases.forEach {
                                        item {
                                            org.purchases.core_ui.ui._elements.PurchaseRecordOnCreate(
                                                description = it.description
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(dimensionResource(id = core_ui_R.dimen.spacer_20)))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = core_ui_R.dimen.button_row_space_between)),
                        ) {
                            org.purchases.core_ui.ui._elements.SimpleButton(
                                text = stringResource(id = R.string.cancel_button),
                                isCancelButton = true,
                                onClick = { navController.navigateUp() },
                                modifier = Modifier.weight(
                                    org.purchases.core_ui.utils.floatResource(
                                        id = core_ui_R.dimen.half_weight
                                    )
                                )
                            )
                            org.purchases.core_ui.ui._elements.SimpleButton(
                                text = stringResource(id = R.string.create_button),
                                onClick = {
                                    val finalListTitle =
                                        listTitle.takeIf { it.trim().isNotBlank() }
                                            ?: listNameDefault
                                    viewModel.handleEvent(
                                        CreateListScreenContract.Event.CreateNewListButtonPressedEvent(
                                            ListWithPurchasesInfo(
                                                name = finalListTitle,
                                                purchasesNotChecked = purchases
                                            )
                                        )
                                    )
                                    navController.navigateUp()
                                },
                                modifier = Modifier.weight(
                                    org.purchases.core_ui.utils.floatResource(
                                        id = core_ui_R.dimen.half_weight
                                    )
                                )
                            )
                        }
                    }
                }
            }
        }
    )
}