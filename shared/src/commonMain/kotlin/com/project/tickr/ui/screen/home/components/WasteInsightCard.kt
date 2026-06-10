package com.project.tickr.ui.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.TrendingDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.tickr.domain.model.WasteTrend
import com.project.tickr.ui.theme.TickrCornerRadius
import com.project.tickr.ui.theme.TickrTheme

@Composable
fun WasteInsightCard(
    wasteTrend: WasteTrend?,
    modifier: Modifier = Modifier,
) {
    val colors = TickrTheme.colors
    val typography = TickrTheme.typography
    val spacing = TickrTheme.spacing

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(TickrCornerRadius.card))
            .background(colors.surface)
            .border(
                width = 1.dp,
                color = colors.textSecondary.copy(alpha = 0.12f),
                shape = RoundedCornerShape(TickrCornerRadius.card),
            )
            .padding(spacing.cardInner)
            .semantics {
                contentDescription = if (wasteTrend != null)
                    "Insight: sampah ${if (wasteTrend.isImproving) "turun" else "naik"} ${wasteTrend.deltaPercent}%"
                else "Insight: belum cukup data"
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.md),
    ) {
        // Ikon lingkaran
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(if (wasteTrend != null) colors.safeContainer else colors.surface),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = if (wasteTrend != null) Icons.Outlined.TrendingDown else Icons.Outlined.Info,
                contentDescription = null,
                tint = if (wasteTrend != null) colors.safe else colors.textSecondary,
                modifier = Modifier.size(22.dp),
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            if (wasteTrend != null) {
                val pctText = "${wasteTrend.deltaPercent}%"
                val dirText = if (wasteTrend.isImproving) "turun" else "naik"
                Text(
                    text = buildAnnotatedString {
                        append("Bagus sekali! Bulan ini tingkat makanan terbuangmu ") // TODO(user): pakai stringResource
                        withStyle(SpanStyle(color = colors.safe, fontWeight = FontWeight.Bold)) {
                            append("$dirText $pctText")
                        }
                        append(".")
                    },
                    style = typography.body,
                    color = colors.textPrimary,
                )
                Spacer(Modifier.size(spacing.xs))
                Text(
                    text = "Terus pertahankan kebiasaan baik ini.", // TODO(user): pakai stringResource
                    style = typography.caption,
                    color = colors.textSecondary,
                )
            } else {
                Text(
                    text = "Catat & habiskan barang untuk melihat tren penghematanmu.", // TODO(user): pakai stringResource
                    style = typography.body,
                    color = colors.textSecondary,
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFAFAFA)
@Composable
private fun WasteInsightCardPreview() {
    TickrTheme {
        Column(Modifier.padding(16.dp)) {
            WasteInsightCard(wasteTrend = WasteTrend(12, true))
            Spacer(Modifier.size(12.dp))
            WasteInsightCard(wasteTrend = null)
        }
    }
}
