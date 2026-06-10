package com.project.tickr.ui.screen.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.project.tickr.domain.model.CategoryGroup
import com.project.tickr.domain.model.CategorySlice
import com.project.tickr.domain.model.ExpiringItem
import com.project.tickr.domain.model.Urgency
import com.project.tickr.domain.model.WasteTrend
import com.project.tickr.presentation.home.HomeAction
import com.project.tickr.presentation.home.HomeUiState
import com.project.tickr.ui.screen.home.components.ConsumptionDonutCard
import com.project.tickr.ui.screen.home.components.ExpiringSection
import com.project.tickr.ui.screen.home.components.GreetingHeader
import com.project.tickr.ui.screen.home.components.HomeEmptyState
import com.project.tickr.ui.screen.home.components.TickrTopBar
import com.project.tickr.ui.screen.home.components.WasteInsightCard
import com.project.tickr.ui.theme.TickrTheme
import com.project.tickr.ui.theme.TickrTheme as Theme

@Composable
fun HomeScreen(
    state: HomeUiState,
    onAction: (HomeAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = Theme.spacing

    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        item {
            TickrTopBar(
                nowEpochMillis = state.nowEpochMillis,
                displayDate = state.displayDate,
                displayTime = state.displayTime,
                onNotificationClick = { onAction(HomeAction.OpenNotifications) },
            )
        }
        item {
            GreetingHeader(
                userName = state.userName,
                modifier = Modifier.padding(horizontal = spacing.screen),
            )
            Spacer(Modifier.height(spacing.md))
        }
        item {
            WasteInsightCard(
                wasteTrend = state.wasteTrend,
                modifier = Modifier.padding(horizontal = spacing.screen),
            )
            Spacer(Modifier.height(spacing.lg))
        }

        if (state.isEmpty) {
            item {
                HomeEmptyState(
                    onAddClick = { onAction(HomeAction.OpenAddItem) },
                    modifier = Modifier.padding(horizontal = spacing.screen),
                )
            }
        } else {
            item {
                ConsumptionDonutCard(
                    slices = state.slices,
                    totalItems = state.totalItems,
                    onSeeAll = { onAction(HomeAction.SeeAllCategories) },
                    modifier = Modifier.padding(horizontal = spacing.screen),
                )
                Spacer(Modifier.height(spacing.lg))
            }
            item {
                ExpiringSection(
                    groups = state.expiringGroups,
                    criticalCount = state.criticalCount,
                    onItemClick = { id -> onAction(HomeAction.ClickItem(id)) },
                    modifier = Modifier.padding(horizontal = spacing.screen),
                )
            }
        }
        item { Spacer(Modifier.height(spacing.xxl)) }
    }
}

// ─── Previews ────────────────────────────────────────────────────────────────

@Preview(name = "Home — Isi (Light)", showBackground = true, backgroundColor = 0xFFFAFAFA)
@Composable
private fun HomeScreenPreviewFilled() {
    TickrTheme {
        HomeScreen(
            state = HomeUiState(
                userName = "Sarah",
                nowEpochMillis = 1749470828000L,
                wasteTrend = WasteTrend(deltaPercent = 12, isImproving = true),
                slices = listOf(
                    CategorySlice(1, "Makanan & Minuman", "#0D6759", 8, 50),
                    CategorySlice(2, "Kecantikan", "#FA9A08", 5, 31),
                    CategorySlice(3, "Obat & Vitamin", "#5EC9B7", 3, 19),
                ),
                totalItems = 16,
                expiringGroups = listOf(
                    CategoryGroup(
                        "Makanan & Minuman", "#0D6759",
                        listOf(
                            ExpiringItem(1, "Susu UHT", 1, "Makanan & Minuman", "#0D6759", "2026-06-11", null, 2, "Pcs", 1, Urgency.CRITICAL),
                            ExpiringItem(2, "Yogurt", 1, "Makanan & Minuman", "#0D6759", "2026-06-15", null, 1, "Pcs", 5, Urgency.WARNING),
                        )
                    )
                ),
                criticalCount = 1,
            ),
            onAction = {},
        )
    }
}

@Preview(name = "Home — Kosong (Light)", showBackground = true, backgroundColor = 0xFFFAFAFA)
@Composable
private fun HomeScreenPreviewEmpty() {
    TickrTheme {
        HomeScreen(
            state = HomeUiState(userName = "Sarah", nowEpochMillis = 1749470828000L),
            onAction = {},
        )
    }
}

@Preview(name = "Home — Kosong (Dark)", showBackground = true, backgroundColor = 0xFF101713)
@Composable
private fun HomeScreenPreviewEmptyDark() {
    TickrTheme(darkTheme = true) {
        HomeScreen(
            state = HomeUiState(userName = "Sarah", nowEpochMillis = 1749470828000L),
            onAction = {},
        )
    }
}
