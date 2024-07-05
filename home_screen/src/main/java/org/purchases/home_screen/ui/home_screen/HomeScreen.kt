package org.purchases.home_screen.ui.home_screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel
import org.purchases.core.model.info.ListInfo
import org.purchases.core.settings.LIST_ID
import org.purchases.core_ui.ui._elements.IconButton
import org.purchases.core_ui.ui._elements.MainProgressIndicator
import org.purchases.core_ui.ui.theme.LocalTitleColor
import org.purchases.core_ui.ui.theme.ScreenBackgroundColor
import org.purchases.core_ui.utils.floatResource
import org.purchases.home_screen.R
import org.purchases.home_screen.ui._elements.HomeScreenCard
import org.purchases.core_ui.R as core_ui_R

@SuppressLint("RestrictedApi")
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = koinViewModel(),
    navigateToListScreen: (bundle: Bundle) -> Unit,
    navigateToCreateListScreen: () -> Unit
) {
    var initialEventSent by remember { mutableStateOf(false) }
    if (initialEventSent.not()) {
        initialEventSent = true
        viewModel.handleEvent(HomeScreenContract.Event.RequestListsEvent)
    }
    fun onCardClick(list: ListInfo) {
        val bundle = Bundle()
        bundle.putLong(LIST_ID, list.id)
        navigateToListScreen(bundle)
    }

    fun deleteList(list: ListInfo) {
        viewModel.handleEvent(HomeScreenContract.Event.DeleteListButtonPressedEvent(list.id))
    }
    Scaffold(
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Surface(
                    color = ScreenBackgroundColor,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(id = core_ui_R.dimen.border_padding))
                    ) {
                        Text(
                            text = stringResource(id = R.string.title),
                            color = LocalTitleColor,
                            textAlign = TextAlign.Start,
                            fontSize = dimensionResource(id = core_ui_R.dimen.main_title_font_size).value.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = dimensionResource(id = core_ui_R.dimen.main_title_bottom_padding))
                        )
                        if (viewModel.screenState.isLoading) {
                            val modifier =
                                Modifier.weight(floatResource(id = core_ui_R.dimen.max_weight))
                            MainProgressIndicator(modifier = modifier)
                        } else {
                            if (viewModel.screenState.data.isNotEmpty()) {
                                LazyColumn(
                                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = core_ui_R.dimen.lazy_column_space_between_elements)),
                                    contentPadding = PaddingValues(vertical = dimensionResource(id = core_ui_R.dimen.lazy_column_content_padding)),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(floatResource(id = core_ui_R.dimen.max_weight))
                                ) {
                                    viewModel.screenState.data.forEach {
                                        item {
                                            HomeScreenCard(
                                                list = it,
                                                onCardClick = { onCardClick(it) },
                                                onDeleteListMenuItemClick = { deleteList(it) }
                                            )
                                        }
                                    }
                                }
                            } else {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(floatResource(id = core_ui_R.dimen.max_weight))
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.no_lists),
                                        color = Color.Gray,
                                        textAlign = TextAlign.Center,
                                        fontSize = dimensionResource(id = core_ui_R.dimen.no_elements_message_font_size).value.sp,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = dimensionResource(id = core_ui_R.dimen.no_elements_message_vertical_padding))
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(dimensionResource(id = core_ui_R.dimen.spacer_20)))
                        Box(
                            contentAlignment = Alignment.CenterEnd,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            IconButton(
                                text = stringResource(id = R.string.new_list_button),
                                onClick = navigateToCreateListScreen
                            )
                        }
                    }
                }
            }
        }
    )
}